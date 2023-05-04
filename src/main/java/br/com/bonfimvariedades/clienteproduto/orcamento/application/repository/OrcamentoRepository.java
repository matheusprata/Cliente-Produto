package br.com.bonfimvariedades.clienteproduto.orcamento.application.repository;


import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;

public interface OrcamentoRepository {
    Orcamento saveOrcamento(Orcamento orcamento);
    Orcamento getOneOrcamento(Long idOrcamento);
    void deleteOrcamentoExpirado();
    void deleteOrcamento(Long idOrcamento);
    Orcamento getOneOrcamentoByCpf(String cpf);
}
