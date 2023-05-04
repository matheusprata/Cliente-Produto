package br.com.bonfimvariedades.clienteproduto.matricula.infra;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatriculaSpringDataJPARepository extends JpaRepository<Matricula, UUID> {
}
