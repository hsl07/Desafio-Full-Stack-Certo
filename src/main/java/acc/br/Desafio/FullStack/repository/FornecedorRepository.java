package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {
}
