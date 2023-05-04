package br.com.bonfimvariedades.clienteproduto.pedido.infra;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoSpringDataJPARepository extends JpaRepository<Pedido, UUID> {
}