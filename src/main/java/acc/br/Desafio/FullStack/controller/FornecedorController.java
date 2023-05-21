package acc.br.Desafio.FullStack.controller;

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

import java.util.Optional;

@RestController
@RequestMapping("/fornecedor")
public class FornecedorController {
    @Autowired
    FornecedorService fornecedorService;

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
        return ResponseEntity.ok().body("achou a classe");
    }

}
