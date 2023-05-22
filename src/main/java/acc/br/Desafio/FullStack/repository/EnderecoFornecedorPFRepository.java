package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.EnderecoFonecedor;
import acc.br.Desafio.FullStack.entity.EnderecoFornecedorPF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface EnderecoFornecedorPFRepository extends JpaRepository<EnderecoFornecedorPF,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.endereco_fornecedorpf en where en.fornecedor_pf_id_fornecedor = :id")
    EnderecoFornecedorPF findByIdFornecedorPF(Long id);
}
