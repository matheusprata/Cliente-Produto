package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.service;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoResponse;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;

import java.util.List;
import java.util.UUID;

public interface PagamentoService {
    PagamentoResponse savePagamento(UUID idCadastro, PagamentoRequest pagamentoRequest);
    List<PagamentoResponse> getAllPagamentoByCadastro(UUID idCadastro);
    PagamentoResponse getOnePagamento(Long idPagamento);
    Pagamento savePagamentoByEntrada(Cadastro cadastro, TipoPagamento tipoPagamentoEntrada);
    void deletePagamento(Long idPagamento);
}