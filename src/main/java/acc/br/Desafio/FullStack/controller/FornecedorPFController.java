package acc.br.Desafio.FullStack.controller;

import acc.br.Desafio.FullStack.dto.FornecedorPFDTO;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import acc.br.Desafio.FullStack.entity.FornecedorPessoaFisica;
import acc.br.Desafio.FullStack.service.FornecedorPFService;
import acc.br.Desafio.FullStack.service.FornecedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedorPF")
public class FornecedorPFController {
    @Autowired
    FornecedorPFService fornecedorPFService;
    private static Logger logger = LoggerFactory.getLogger(FornecedorPFController.class);
    @PostMapping("/{id}")
    public ResponseEntity cadastraFornecedor(@RequestBody FornecedorPessoaFisica fornecedor, @PathVariable Long idEmpresa){

        String retorno = fornecedorPFService.cadastraFornecedorPF(fornecedor, idEmpresa);

        if(retorno==null){
            return ResponseEntity.badRequest().body("Algo deu errado");
        }
        if(retorno.equals("cpf existente")){
            return ResponseEntity.badRequest().body("Cpf existente");
        }
        if(retorno.equals("Empresa é do Paraná")){
            return ResponseEntity.ok().body("Empresa é do Paraná,\n Não permitir cadastrar um fornecedor pessoa física menor de idade");
        }
        return ResponseEntity.ok().body(retorno);


    }
    @PutMapping("/{idFornecedor}")
    public ResponseEntity updateFornecedor(@RequestBody FornecedorPessoaFisica fornecedorPessoaFisica, @PathVariable Long idFornecedor){
      String retorn =  fornecedorPFService.updateFornecedor(fornecedorPessoaFisica,idFornecedor);
        if(retorn==null){
            return ResponseEntity.badRequest().body("Fornecedor não encontrado");
        }
        if (retorn.equals("Cpf não é valido")){
            return ResponseEntity.badRequest().body("Cpf não é valido");
        }

        return ResponseEntity.ok().body(retorn);
    }
    @GetMapping("/name")
    public Page<FornecedorPFDTO> searchNome(@RequestParam(defaultValue = "") String name, Pageable pageable){

        Page<FornecedorPFDTO> result = fornecedorPFService.searchName(name,pageable);
      /* if(result==null){
        return ResponseEntity.badRequest().body("Algo deu errado");
        }*/

        return result;

    }

    @GetMapping("/cpf")
    public Page<FornecedorPFDTO> searchCpf(@RequestParam(defaultValue = "") String cpf, Pageable pageable){
        Page<FornecedorPFDTO> result = fornecedorPFService.searchCpf(cpf,pageable);

        return result;
    }


}
