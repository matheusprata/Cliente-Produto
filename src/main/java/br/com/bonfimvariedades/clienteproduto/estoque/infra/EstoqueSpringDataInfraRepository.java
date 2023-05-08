package br.com.bonfimvariedades.clienteproduto.estoque.infra;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EstoqueSpringDataInfraRepository extends JpaRepository<Estoque, UUID> {
}
