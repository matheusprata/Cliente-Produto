package br.com.bonfimvariedades.clienteproduto.orcamento.application.service;


import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoResponse;

public interface OrcamentoService {
    OrcamentoResponse saveOrcamento(OrcamentoRequest orcamentoRequest);
    OrcamentoResponse getOneOrcamento(Long idOrcamento);
}