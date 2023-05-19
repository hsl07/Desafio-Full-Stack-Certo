package acc.br.Desafio.FullStack.service;

import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRespository empresaRespository;

    public String salvaEmpresa(Empresa empresa){
        empresaRespository.save(empresa);
        return "As informações da empresa foram salvas corretamentes";
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

    public Empresa updateEm(Empresa empresa, Long id){
        Optional<Empresa> empresa1 = empresaRespository.findById(id);
        if(empresa1.isPresent()){
            Empresa  empresa2 = empresa1.get();
            empresa2.setCnpj(empresa.getCnpj());
            empresa2.setNomeFantasia(empresa.getNomeFantasia());
            empresa2.setCep(empresa.getCep());

            empresaRespository.save(empresa2);
                return empresa2;
        }else {
        return null;
        }

    }

}
