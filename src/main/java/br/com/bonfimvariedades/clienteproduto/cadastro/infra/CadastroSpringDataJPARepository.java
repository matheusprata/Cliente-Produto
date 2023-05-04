package br.com.bonfimvariedades.clienteproduto.cadastro.infra;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CadastroSpringDataJPARepository extends JpaRepository<Cadastro, UUID> {
}
