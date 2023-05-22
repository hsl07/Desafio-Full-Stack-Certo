package acc.br.Desafio.FullStack.service;

import acc.br.Desafio.FullStack.consume.ApiViaCep;
import acc.br.Desafio.FullStack.dto.FornecedorDTO;
import acc.br.Desafio.FullStack.dto.FornecedorPFDTO;
import acc.br.Desafio.FullStack.entity.*;
import acc.br.Desafio.FullStack.repository.EmpresaFornecedorRepository;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.repository.EnderecoFornecedorRepository;
import acc.br.Desafio.FullStack.repository.FornecedorRepository;
import acc.br.Desafio.FullStack.utils.ValidaCNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {
    private static Logger logger = LoggerFactory.getLogger(FornecedorService.class);
    @Autowired
    FornecedorRepository fornecedorRepository;

    @Autowired
    EmpresaRespository empresaRespository;
    @Autowired
    EmpresaFornecedorRepository empresaFornecedorRepository;
    @Autowired
    EnderecoFornecedorRepository enderecoFornecedorRepository;

    public String cadastraFornecedor(Fornecedor fornecedor, Long id){

        try {
            EnderecoFonecedor enderecoFonecedor = null;

            ApiViaCep viaCep = new ApiViaCep();

            Optional<Empresa> empresa = empresaRespository.findById(id);

            if(empresa.isPresent()){ // verifica se a empresa Existe


                    enderecoFonecedor = new EnderecoFonecedor(viaCep.consultaCEP(fornecedor.getCep())); // Consulta o EnderecoFonecedor pelo CEP //O erro está aqui

                    Boolean vlCNPJ = ValidaCNPJ.isCNPJ(fornecedor.getCnpj()); //Valida CNPJ

                    if (enderecoFonecedor != null && vlCNPJ) {//Se o Objeto enderecoFonecedor não é null e a variavel vlCNPJ é verdadeira

                        String cnpj = ValidaCNPJ.imprimeCNPJ(fornecedor.getCnpj());

                        List<Fornecedor> emCPNJ = fornecedorRepository.consultaCNPJFornecedor(cnpj);

                        if (emCPNJ.isEmpty()) { // Se o CNPJ não foi registrado no banco de dados, então ele pode ser cadastrado

                            Fornecedor fornecedor1 = fornecedor;


                            Empresa empresa1 = new Empresa(empresa); // Cria a empresa existente em memoria
                            EmpresaFornecedor rela = new EmpresaFornecedor(empresa1, fornecedor); //Pega os 2 objetos e cria o relacionamento em memoria
                            enderecoFonecedor.setFornecedor(fornecedor);
                            fornecedor1.setEndereco(enderecoFonecedor);
                            fornecedor1.setCnpj(ValidaCNPJ.imprimeCNPJ(fornecedor.getCnpj()));
                            fornecedorRepository.save(fornecedor1); //Salva o fornecedor no banco
                            empresaFornecedorRepository.save(rela); // Cria o relacionamento no Banco

                            return "Tudo certo";

                        } else {
                            return "Cnpj existente";
                        }

                    } else {
                        return null;
                    }



            }else {
                return null;
            }

        } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

    public String updateFornecedor(FornecedorDTO fornecedorDTO, Long id){
       Fornecedor fornecedor = new Fornecedor(fornecedorDTO);

        Optional<Fornecedor> fornecedor1 = fornecedorRepository.findById(id);
       if (fornecedor1.isPresent()){




           Fornecedor fornecedor2 = fornecedor1.get();

           fornecedor2.setEmail(fornecedor.getEmail());

           fornecedor2.setNome(fornecedor.getNome());



           if(!fornecedor1.get().getCep().trim().equals(fornecedor.getCep())){
               EnderecoFonecedor enderecoFonecedor = null;


               ApiViaCep viaCep = new ApiViaCep();
               try {
                   enderecoFonecedor = new EnderecoFonecedor(viaCep.consultaCEP(fornecedor.getCep()));
               } catch (IOException e) {
                   throw new RuntimeException(e);
               }

               if(enderecoFonecedor!=null){
                 EnderecoFonecedor enderecoFonecedor1 = enderecoFornecedorRepository.findByIdFornecedor(fornecedor2.getId());

                 enderecoFonecedor1.setCep(enderecoFonecedor.getCep());
                 enderecoFonecedor1.setBairro(enderecoFonecedor.getBairro());
                 enderecoFonecedor1.setComplemento(enderecoFonecedor.getComplemento());
                 enderecoFonecedor1.setLocalidade(enderecoFonecedor.getLocalidade());
                 enderecoFonecedor1.setLogradouro(enderecoFonecedor.getLogradouro());
                 enderecoFonecedor1.setUf(enderecoFonecedor.getUf());

                  fornecedor2.setEndereco(enderecoFonecedor1);
                  fornecedor2.setCep(fornecedor.getCep());
                  enderecoFornecedorRepository.save(enderecoFonecedor1);
              }

           }

          fornecedorRepository.save(fornecedor2);
           return "Fornecedor Atualizado";

       }else {
           return null;
       }


    }


    public Fornecedor getFornecedorbyID(Long id){
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
            if (fornecedor.equals(null)){
                return null;
            }
            Fornecedor fornecedor1 = new Fornecedor(fornecedor);
            return fornecedor1;
    }
    public List<Fornecedor> getAll(){

       List<Fornecedor>  fornecedors = fornecedorRepository.findAll();
      if (fornecedors.isEmpty()){
          return null;
      }
       return fornecedors;
    }

    public String deleteFornecedor(Long id){
       Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
       if (fornecedor.isPresent()){
           fornecedorRepository.deleteById(id);
           return "Deletado com sucesso";
       }
       return "Não encontrado";
    }

    public Page<FornecedorDTO> buscaCNPJ(String cnpj, Pageable pageable){
      Page<FornecedorDTO> fornecedorDTO = convertToDTO(fornecedorRepository.searchCnpj(cnpj,pageable));
    return fornecedorDTO;
    }

    public Page<FornecedorDTO> searchName(String name, Pageable pageable){
        Page<FornecedorDTO> fornecedorDTO = convertToDTO(fornecedorRepository.searchNome(name,pageable));
        return fornecedorDTO;
    }
    private Page<FornecedorDTO> convertToDTO(Page<Fornecedor> fornecedorPFPage) {
        return fornecedorPFPage.map(this::convertToDTO);
    }

    private FornecedorDTO convertToDTO(Fornecedor fornecedorPF) {
        FornecedorDTO dto = new FornecedorDTO(fornecedorPF);
        return dto;
    }

}
