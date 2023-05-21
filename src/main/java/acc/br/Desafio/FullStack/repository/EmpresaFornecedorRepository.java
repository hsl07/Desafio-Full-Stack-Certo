package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.EmpresaFornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaFornecedorRepository extends JpaRepository<EmpresaFornecedor,Long> {

 /*   @Query("INSERT INTO desafio.empresa_fornecedor (empresa_id_empresa, fornecedor_id_fornecedor)\n" +
            "VALUES (:idempresa, :idFornecedor)")
    void salvarRelacionamento(Long idempresa,Long idFornecedor);*/
}
