package acc.br.Desafio.FullStack.controller;

import acc.br.Desafio.FullStack.dto.FornecedorDTO;
import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.entity.EmpresaFornecedor;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import acc.br.Desafio.FullStack.repository.EmpresaFornecedorRepository;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import acc.br.Desafio.FullStack.repository.FornecedorRepository;
import acc.br.Desafio.FullStack.service.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    FornecedorService fornecedorService;
    private static Logger logger = LoggerFactory.getLogger(FornecedorController.class);

    @PostMapping("/{id}")
    public ResponseEntity cadastraFornecedor(@RequestBody Fornecedor fornecedor, @PathVariable Long id){

       String retorno = fornecedorService.cadastraFornecedor(fornecedor, id);

       if(retorno==null){
           return ResponseEntity.badRequest().body("Algo deu errado");
       }
       if(retorno=="Cnpj existente"){
           return ResponseEntity.badRequest().body("Cnpj existente");
       }
       return ResponseEntity.ok().body(retorno);

    }
    @GetMapping
    public ResponseEntity test(){
       List<Fornecedor> fornecedors = fornecedorService.getAll();
       if(fornecedors.equals(null)){
           return ResponseEntity.badRequest().body("Consulta não execultada");
       }

       return ResponseEntity.ok().body(fornecedors.stream().map(fornecedor -> new FornecedorDTO(fornecedor)).toList());
    }


    @PutMapping("/{id}")
    public ResponseEntity updateFornecedor(@RequestBody FornecedorDTO fornecedorDTO,@PathVariable Long id) {

     String fornecedor1 = fornecedorService.updateFornecedor(fornecedorDTO,id);

        if (fornecedor1==null){
        return ResponseEntity.badRequest().body("Algo deu errado");
        }
        return ResponseEntity.ok().body(fornecedor1);
    }
    @GetMapping("/{id}")
    public ResponseEntity getFornecedorbyID(@PathVariable Long id){
       FornecedorDTO fornecedorDTO = new FornecedorDTO(fornecedorService.getFornecedorbyID(id));
       if (fornecedorDTO == null){return ResponseEntity.badRequest().body("Algo deu errado");}
       return ResponseEntity.ok().body(fornecedorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        String retorn =fornecedorService.deleteFornecedor(id);
        if(retorn.equals("Não encontrado")){
            return ResponseEntity.badRequest().body("Não encontrado");
        }
        return ResponseEntity.ok().body(retorn);

    }
}
