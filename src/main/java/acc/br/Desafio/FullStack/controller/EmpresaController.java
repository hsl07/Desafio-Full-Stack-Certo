package acc.br.Desafio.FullStack.controller;

import acc.br.Desafio.FullStack.dto.EmpresaDTO;
import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public ResponseEntity postEmpresa(@RequestBody Empresa empresa){
               String retorn = empresaService.salvaEmpresa(empresa);
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
    public EmpresaDTO updateEmpresa(@RequestBody Empresa empresa,@PathVariable Long id){
        EmpresaDTO empresa2 = new EmpresaDTO(empresaService.updateEm(empresa,id));
        return empresa2;
    }

}
