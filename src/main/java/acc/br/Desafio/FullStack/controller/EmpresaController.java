package acc.br.Desafio.FullStack.controller;

import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.repository.EmpresaRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {


    @PostMapping()
    public ResponseEntity postEmpresa(@RequestBody Empresa empresa){

        return ResponseEntity.ok().body("Tudo certo");
    }

}
