package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.repository;


import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PagamentoRepository {
    List<Pagamento> getAllPagamentoByCadastro(Cadastro cadastro);
    BigDecimal totalPago(Cadastro cadastro);
    Pagamento salvaPagamento(Pagamento pagamento);
    Pagamento getOnePagamento(Long idPagamento);
    void deletePagamento(Long idPagamento);
    List<Pagamento> getAllPagamentoByData(LocalDate data);
    List<Pagamento> getAllPagamentoByTipoPagamento(TipoPagamento tipoPagamento, LocalDate data);
}