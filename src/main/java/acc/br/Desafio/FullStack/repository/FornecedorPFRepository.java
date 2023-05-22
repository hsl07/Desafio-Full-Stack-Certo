package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.dto.FornecedorPFDTO;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import acc.br.Desafio.FullStack.entity.FornecedorPessoaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FornecedorPFRepository extends JpaRepository<FornecedorPessoaFisica,Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor_pessoa_fisica  f where f.cpf = :cpf")
    List<FornecedorPessoaFisica> consultaCPFFornecedor(String cpf);
    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor_pessoa_fisica obj WHERE lower(obj.nomef) LIKE lower(CONCAT('%',:nome,'%'))")
    Page<FornecedorPessoaFisica> searchName(String nome, Pageable pageable);

    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor_pessoa_fisica obj WHERE obj.cpf LIKE CONCAT('%',:cpf,'%')")
    Page<FornecedorPessoaFisica> searchCPF(String cpf, Pageable pageable);
}
