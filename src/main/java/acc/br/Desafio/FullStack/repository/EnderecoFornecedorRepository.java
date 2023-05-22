package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.EnderecoFonecedor;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface EnderecoFornecedorRepository extends JpaRepository<EnderecoFonecedor,Long> {
    @Query(nativeQuery = true,
    value = "SELECT * FROM desafio.endereco_fonecedor where fornecedor_id_fornecedor = :id")
    EnderecoFonecedor findByIdFornecedor(Long id);

}
