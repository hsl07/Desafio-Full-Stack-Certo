package acc.br.Desafio.FullStack.controller;

import acc.br.Desafio.FullStack.dto.EmpresaDTO;
import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.service.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    private static Logger logger = LoggerFactory.getLogger(EmpresaController.class);
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public ResponseEntity postEmpresa(@RequestBody Empresa empresa){
               String retorn = empresaService.saveEmpresa(empresa);
                if(retorn==null){
                    return ResponseEntity.badRequest().body("Cep informado não existe" +
                            "\nE a empresa não foi salva");
                }
                if (retorn.equals("Cnpj existente")){
                    return ResponseEntity.badRequest().body("Cnpj já cadastrado em outra Empresa" +
                            "\nE a empresa não foi salva");
                }
               return ResponseEntity.ok().body(retorn);
    }
    @GetMapping
    public List<EmpresaDTO> getAll(){
       List<Empresa> em = empresaService.getAllEmpresa();
       List<EmpresaDTO> emDTOS = em.stream().map(empresa -> new EmpresaDTO(empresa)).toList();
       return emDTOS;

    }
    @GetMapping("/{id}")
    public ResponseEntity getID(@PathVariable Long id){
    Empresa empresa = empresaService.getById(id);
        if(empresa==null){
         return ResponseEntity.badRequest().body("Empresa não encontrada");
         }else {
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
       return ResponseEntity.ok().body(empresaDTO);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity updateEmpresa(@RequestBody EmpresaDTO empresaDTO,@PathVariable Long id) throws IOException {

        Empresa empresa = new Empresa(empresaDTO);

        Empresa empresa1 = empresaService.updateEm(empresa,id);
        EmpresaDTO empresa2 = new EmpresaDTO(empresa1);
        if(empresa2==null){
            return ResponseEntity.badRequest().body("Empresa não encontrada");
        }else {
            return ResponseEntity.ok().body(empresa2);
        }


    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmpresa(@PathVariable Long id){
        String nome = empresaService.deleteEm(id);
        if (nome==null){
            return ResponseEntity.badRequest().body("Empresa não encontrada");
        }else {
            return ResponseEntity.ok().body("Empresa ".concat(nome).concat(" deletada"));

        }
    }

}
