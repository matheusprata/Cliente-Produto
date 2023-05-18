package br.com.bonfimvariedades.clienteproduto.compra.infra;

import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompraSpringDataJPARepository extends JpaRepository<Compra, UUID> {
}
