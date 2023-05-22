package acc.br.Desafio.FullStack.service;

import acc.br.Desafio.FullStack.consume.ApiViaCep;
import acc.br.Desafio.FullStack.dto.FornecedorDTO;
import acc.br.Desafio.FullStack.dto.FornecedorPFDTO;
import acc.br.Desafio.FullStack.entity.*;
import acc.br.Desafio.FullStack.repository.EmpresaFornecedorRepository;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.repository.EnderecoFornecedorPFRepository;
import acc.br.Desafio.FullStack.repository.FornecedorPFRepository;
import acc.br.Desafio.FullStack.utils.ValidaCNPJ;
import acc.br.Desafio.FullStack.utils.ValidaCPF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class FornecedorPFService {
    private static Logger logger = LoggerFactory.getLogger(FornecedorService.class);
    @Autowired
    EmpresaRespository empresaRespository;
    @Autowired
    EmpresaFornecedorRepository empresaFornecedorRepository;
    @Autowired
    FornecedorPFRepository fornecedorPFRepository;
    @Autowired
    EnderecoFornecedorPFRepository enderecoFornecedorPFRepository;

    public String cadastraFornecedorPF(FornecedorPessoaFisica fornecedor, Long id){

        try {
            EnderecoFornecedorPF enderecoFonecedor = null;

            ApiViaCep viaCep = new ApiViaCep();

            Optional<Empresa> empresa = empresaRespository.findById(id);

            if(empresa.isPresent()){ // verifica se a empresa Existe


                enderecoFonecedor = new EnderecoFornecedorPF(viaCep.consultaCEP(fornecedor.getCep())); // Consulta o EnderecoFonecedor pelo CEP //


                Boolean vlCPF = ValidaCPF.isCPF(fornecedor.getCpf());//Valida CPF

                if (enderecoFonecedor != null && vlCPF) {//Se o Objeto enderecoFonecedor não é null e a variavel vlCPF é verdadeira

                    String cpf = ValidaCPF.imprimeCPF(fornecedor.getCpf());

                    List<FornecedorPessoaFisica> emCPF = fornecedorPFRepository.consultaCPFFornecedor(cpf);

                    if (!emCPF.isEmpty()) { // Se o CPF não foi registrado no banco de dados, então ele pode ser cadastrado
                        return "cpf existente";
                    }
                        FornecedorPessoaFisica fornecedor1 = fornecedor;

                    if(empresa.get().getEndereco().getUf().equals("PR")){
                            String dataN = fornecedor.getDataNascimento().toString();

                            int idade = calcularIdade(dataN);

                            if(idade<18){
                                    return "Empresa é do Paraná";
                                }
                        }

                        Empresa empresa1 = new Empresa(empresa); // Cria a empresa existente em memoria

                        EmpresaFornecedor rela = new EmpresaFornecedor(empresa1, fornecedor); //Pega os 2 objetos e cria o relacionamento em memoria
                        enderecoFonecedor.setFornecedor_PF(fornecedor);
                        fornecedor1.setEndereco(enderecoFonecedor);
                        fornecedor1.setCpf(cpf);
                        fornecedorPFRepository.save(fornecedor1);//Salva o fornecedor no banco
                        empresaFornecedorRepository.save(rela); // Cria o relacionamento no Banco

                        return "Tudo certo";


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

    public String updateFornecedor(FornecedorPessoaFisica fornecedor, Long id){
     //   FornecedorPessoaFisica fornecedor = fornecedorDTO;

        Optional<FornecedorPessoaFisica> fornecedor1 = fornecedorPFRepository.findById(id);
        if (fornecedor1.isPresent()){

            FornecedorPessoaFisica fornecedor2 = fornecedor1.get();

            fornecedor2.setEmail(fornecedor.getEmail());

            fornecedor2.setNome(fornecedor.getNome());
            fornecedor2.setRG(fornecedor.getRG());
            fornecedor2.setDataNascimento(fornecedor.getDataNascimento());
            if(!fornecedor.getCpf().equals(fornecedor2.getCpf())){

                Boolean valida = ValidaCPF.isCPF(fornecedor.getCpf());
                if(!valida){
                    return "Cpf não é valido";
                }

                String cpf = ValidaCPF.imprimeCPF(fornecedor.getCpf());
                List<FornecedorPessoaFisica> emCPF = fornecedorPFRepository.consultaCPFFornecedor(cpf);

                if (!emCPF.isEmpty()) {
                    return "cpf existente";
                }

                fornecedor2.setCpf(cpf);
            }


            if(!fornecedor1.get().getCep().trim().equals(fornecedor.getCep())){
                EnderecoFornecedorPF enderecoFonecedor = null;


                ApiViaCep viaCep = new ApiViaCep();
                try {
                    enderecoFonecedor = new EnderecoFornecedorPF(viaCep.consultaCEP(fornecedor.getCep()));

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if(enderecoFonecedor!=null){
                    EnderecoFornecedorPF enderecoFonecedor1 = enderecoFornecedorPFRepository.findByIdFornecedorPF(fornecedor2.getId());

                    enderecoFonecedor1.setCep(enderecoFonecedor.getCep());
                    enderecoFonecedor1.setBairro(enderecoFonecedor.getBairro());
                    enderecoFonecedor1.setComplemento(enderecoFonecedor.getComplemento());
                    enderecoFonecedor1.setLocalidade(enderecoFonecedor.getLocalidade());
                    enderecoFonecedor1.setLogradouro(enderecoFonecedor.getLogradouro());
                    enderecoFonecedor1.setUf(enderecoFonecedor.getUf());

                    fornecedor2.setEndereco(enderecoFonecedor1);
                    fornecedor2.setCep(fornecedor.getCep());
                   enderecoFornecedorPFRepository.save(enderecoFonecedor1);
                }

            }

            fornecedorPFRepository.save(fornecedor2);
            return "Fornecedor Atualizado";

        }else {
            return null;
        }


    }

    public Page<FornecedorPFDTO> searchName(String name, Pageable pageable){
       Page<FornecedorPFDTO> fornecedor = convertToDTO(fornecedorPFRepository.searchName(name,pageable));

       return fornecedor;
    }
    public Page<FornecedorPFDTO> searchCpf(String cpf, Pageable pageable){
        Page<FornecedorPFDTO> fornecedor = convertToDTO(fornecedorPFRepository.searchCPF(cpf,pageable));

        return fornecedor;
    }


    private int calcularIdade(String dataN){

        String dataNascimentoString = dataN;

        // Formato da data
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // Converte a String para LocalDate
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, formatoData);

        // Data atual
        LocalDate dataAtual = LocalDate.now();

        // Calcula a diferença entre a data atual e a data de nascimento
        Period periodo = Period.between(dataNascimento, dataAtual);

        // Obtém a idade
        int idade = periodo.getYears();

        return idade;
    }

    private Page<FornecedorPFDTO> convertToDTO(Page<FornecedorPessoaFisica> fornecedorPFPage) {
        return fornecedorPFPage.map(this::convertToDTO);
    }

    private FornecedorPFDTO convertToDTO(FornecedorPessoaFisica fornecedorPF) {
        FornecedorPFDTO dto = new FornecedorPFDTO(fornecedorPF);
        return dto;
    }

}
