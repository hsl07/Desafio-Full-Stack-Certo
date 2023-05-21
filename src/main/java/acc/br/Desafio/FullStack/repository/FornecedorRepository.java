package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor f where f.cnpj = :cnpj")
    List<Fornecedor> consultaCNPJFornecedor(String cnpj);
}
