package acc.br.Desafio.FullStack.repository;

import acc.br.Desafio.FullStack.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRespository extends JpaRepository<Empresa,Long> {
}
