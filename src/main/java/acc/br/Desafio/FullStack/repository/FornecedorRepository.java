package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Empresa;
import acc.br.Desafio.FullStack.entity.Fornecedor;
import acc.br.Desafio.FullStack.entity.FornecedorPessoaFisica;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface FornecedorRepository extends JpaRepository<Fornecedor,Long> {

    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor f where f.cnpj = :cnpj")
    List<Fornecedor> consultaCNPJFornecedor(String cnpj);
    @Modifying
    @Query(nativeQuery = true,
            value ="DELETE FROM desafio.endereco_fonecedor e where e.fornecedor_id_fornecedor = :id")
    void deleteEnderecoById(Long id);

    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor obj WHERE obj.cnpj LIKE CONCAT('%',:cnpj,'%')")
    Page<Fornecedor> searchCnpj(String cnpj, Pageable pageable);
    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.fornecedor obj WHERE lower(obj.nomef) LIKE lower(CONCAT('%',:nome,'%'))")
    Page<Fornecedor> searchNome(String nome, Pageable pageable);
}
