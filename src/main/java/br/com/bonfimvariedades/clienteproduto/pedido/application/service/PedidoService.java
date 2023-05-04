package br.com.bonfimvariedades.clienteproduto.pedido.application.service;


import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoIdResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoListResponse;

import java.util.List;
import java.util.UUID;

public interface PedidoService {
    PedidoIdResponse savePedido(PedidoRequest pedidoRequest);
    PedidoIdResponse savePedidoByOrcamento(String cpf);
    List<PedidoListResponse> getAllPedidos();
    PedidoDetalhadoResponse getOnePedido(UUID idPedido);
    void deletePedido(UUID idPedido);
    void updatePedido(UUID idPedido, PedidoAlteracaoRequest pedidoAlteracaoRequest);
    void finalizaPedido(UUID idPedido);
    void ativaPedido(UUID idPedido);
    void cancelaPedido(UUID idPedido);
}