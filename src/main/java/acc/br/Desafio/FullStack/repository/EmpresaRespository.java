package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EmpresaRespository extends JpaRepository<Empresa,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.empresa e where e.cnpj_em = :cnpj")
    List<Empresa> consultaCNPJEmpresa(String cnpj);

    @Modifying
    @Query(nativeQuery = true,
            value ="DELETE FROM desafio.endereco_empresa e where e.empresa_id_empresa = :id")
    void deleteEnderecoById(Long id);
}
