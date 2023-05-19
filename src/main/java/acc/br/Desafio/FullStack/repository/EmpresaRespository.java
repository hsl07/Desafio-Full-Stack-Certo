package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRespository extends JpaRepository<Empresa,Long> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM desafio.empresa e where e.cnpj_em = :cnpj")
    List<Empresa> consultaCNPJEmpresa(String cnpj);
}
