package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.service;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoResponse;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;

import java.util.List;
import java.util.UUID;

public interface PagamentoService {
    PagamentoResponse savePagamento(UUID idMatricula, PagamentoRequest pagamentoRequest);
    List<PagamentoResponse> getAllPagamentoByMatricula(UUID idMatricula);
    PagamentoResponse getOnePagamento(Long idPagamento);
    Pagamento savePagamentoByEntrada(Matricula matricula, TipoPagamento tipoPagamentoEntrada);
    void deletePagamento(Long idPagamento);
}