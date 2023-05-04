package br.com.bonfimvariedades.clienteproduto.pedido.application.repository;


import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;

import java.util.List;
import java.util.UUID;

public interface PedidoRepository {
    Pedido savePedido(Pedido pedido);
    List<Pedido> getAllPedidos();
    Pedido getOnePedido(UUID idPedido);
    void deletePedido(Pedido pedido);
}
