package br.com.bonfimvariedades.clienteproduto.pagamento.application.service;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.api.PagamentoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.api.PagamentoResponse;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;

import java.util.List;
import java.util.UUID;

public interface PagamentoService {
    PagamentoResponse savePagamento(UUID idPedido, PagamentoRequest pagamentoRequest);
    List<PagamentoResponse> getAllPagamentoByPedido(UUID idPedido);
    PagamentoResponse getOnePagamento(Long idPagamento);
    Pagamento savePagamentoByEntrada(Pedido pedido, TipoPagamento tipoPagamentoEntrada);
    void deletePagamento(Long idPagamento);
}