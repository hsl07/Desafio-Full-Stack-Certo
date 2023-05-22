package acc.br.Desafio.FullStack.service;

import acc.br.Desafio.FullStack.consume.ApiViaCep;
import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.entity.EnderecoEmpresa;
import acc.br.Desafio.FullStack.entity.EnderecoFonecedor;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.utils.ValidaCNPJ;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRespository empresaRespository;

    private static Logger logger = LoggerFactory.getLogger(EmpresaService.class);

    public String saveEmpresa(Empresa empresa){

        EnderecoEmpresa enderecoEmpresa = null;

        ApiViaCep viaCep = new ApiViaCep();

        try {
                enderecoEmpresa = new EnderecoEmpresa(viaCep.consultaCEP(empresa.getCep()));

                Boolean vlCNPJ = ValidaCNPJ.isCNPJ(empresa.getCnpj());

                if (enderecoEmpresa !=null&&vlCNPJ){  //Se o Objeto enderecoFonecedor não é null e a variavel vlCNPJ é verdadeira
                       List<Empresa> emCPNJ = empresaRespository.consultaCNPJEmpresa(empresa.getCnpj());

                       if(emCPNJ.isEmpty()) { // Se o CNPJ não foi registrado no banco de dados, então ele pode ser cadastrado

                            Empresa empresa1 = empresa;
                            enderecoEmpresa.setEmpresa(empresa);
                            empresa1.setEndereco(enderecoEmpresa);
                            empresa1.setCnpj(ValidaCNPJ.imprimeCNPJ(empresa.getCnpj()));
                            empresaRespository.save(empresa1);
                            return "As informações da empresa foram salvas corretamentes";

                       }else {
                           return "Cnpj existente";
                       }

                } else {
                    return null;
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


    }

    public List<Empresa> getAllEmpresa(){
       List<Empresa> em = empresaRespository.findAll();
       if (em.size()>0){
           return em;
       }else {
           return em;
       }

    }

    public Empresa getById(Long id){
        Optional<Empresa> em = empresaRespository.findById(id);
        if(em.isPresent()){
            Empresa empresa = em.get();
            return empresa;
        }else {
            return null;
        }

    }

    public Empresa updateEm(Empresa empresa, Long id) throws IOException {

        Optional<Empresa> empresa1 = empresaRespository.findById(id);
        EnderecoEmpresa enderecoEmpresa = null;

        if(empresa1.isPresent()){


        Empresa  empresa2 = empresa1.get();
        empresa2.setCnpj(empresa.getCnpj());


            empresa2.setNomeFantasia(empresa.getNomeFantasia());


            if(!empresa1.get().getCep().equals(empresa.getCep())){

                ApiViaCep viaCep = new ApiViaCep();
                enderecoEmpresa = new EnderecoEmpresa(viaCep.consultaCEP(empresa.getCep()));

                if(enderecoEmpresa!=null){
                    empresaRespository.deleteEnderecoById(empresa1.get().getId());
                    enderecoEmpresa.setEmpresa(empresa2);
                    empresa2.setEndereco(enderecoEmpresa);
                    empresa2.setCep(empresa.getCep());
                }

            }

            empresaRespository.save(empresa2);
            return empresa2;

        }else {
            return null;
        }

    }

    public String deleteEm(Long id){
           Optional<Empresa> empresa = empresaRespository.findById(id);
            if(empresa.isPresent()){
                String nome = empresa.get().getNomeFantasia();
                empresaRespository.deleteById(id);
                return nome;
            }else {
                return null;
            }

    }

}
