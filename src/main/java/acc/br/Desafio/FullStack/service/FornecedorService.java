package acc.br.Desafio.FullStack.service;

import acc.br.Desafio.FullStack.consume.ApiViaCep;
import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.entity.EmpresaFornecedor;
import acc.br.Desafio.FullStack.entity.EnderecoFonecedor;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import acc.br.Desafio.FullStack.repository.EmpresaFornecedorRepository;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.repository.FornecedorRepository;
import acc.br.Desafio.FullStack.utils.ValidaCNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    public String cadastraFornecedor(Fornecedor fornecedor, Long id){

        try {
            EnderecoFonecedor enderecoFonecedor = null;

            ApiViaCep viaCep = new ApiViaCep();

            Optional<Empresa> empresa = empresaRespository.findById(id);

            if(empresa.isPresent()){ // verifica se a empresa Existe

                enderecoFonecedor = new EnderecoFonecedor(viaCep.consultaCEP(fornecedor.getCep())); // Consulta o EnderecoFonecedor pelo CEP //O erro está aqui

                Boolean vlCNPJ = ValidaCNPJ.isCNPJ(fornecedor.getCnpj()); //Valida CNPJ

                if (enderecoFonecedor !=null&&vlCNPJ) {//Se o Objeto enderecoFonecedor não é null e a variavel vlCNPJ é verdadeira

                    String cnpj = ValidaCNPJ.imprimeCNPJ(fornecedor.getCnpj());

                    List<Fornecedor> emCPNJ = fornecedorRepository.consultaCNPJFornecedor(cnpj);

                    if(emCPNJ.isEmpty()) { // Se o CNPJ não foi registrado no banco de dados, então ele pode ser cadastrado

                        Fornecedor fornecedor1 = fornecedor;


                            Empresa empresa1 = new Empresa(empresa); // Cria a empresa existente em memoria
                            EmpresaFornecedor rela = new EmpresaFornecedor(empresa1, fornecedor); //Pega os 2 objetos e cria o relacionamento em memoria
                            enderecoFonecedor.setFornecedor(fornecedor);
                            fornecedor1.setEndereco(enderecoFonecedor);
                            fornecedor1.setCnpj(ValidaCNPJ.imprimeCNPJ(fornecedor.getCnpj()));
                            fornecedorRepository.save(fornecedor1); //Salva o fornecedor no banco
                            empresaFornecedorRepository.save(rela); // Cria o relacionamento no Banco

                            return "Tudo certo";

                    }else {
                        return "Cnpj existente";
                    }

                }else {
                    return null;
                }



            }else {
                return null;
            }

        } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }

}
