package br.com.bonfimvariedades.clienteproduto.pagamento.application.repository;


import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PagamentoRepository {
    List<Pagamento> getAllPagamentoByPedido(Pedido pedido);
    BigDecimal totalPago(Pedido pedido);
    Pagamento salvaPagamento(Pagamento pagamento);
    Pagamento getOnePagamento(Long idPagamento);
    void deletePagamento(Long idPagamento);
    List<Pagamento> getAllPagamentoByData(LocalDate data);
    List<Pagamento> getAllPagamentoByTipoPagamento(TipoPagamento tipoPagamento, LocalDate data);
}