package br.com.bonfimvariedades.clienteproduto.funcionario.infra;

import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface FuncionarioSpringDataJPARepository extends JpaRepository<Funcionario, UUID> {
}