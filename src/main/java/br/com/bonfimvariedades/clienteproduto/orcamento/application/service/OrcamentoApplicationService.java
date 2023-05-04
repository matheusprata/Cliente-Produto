package br.com.bonfimvariedades.clienteproduto.orcamento.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.application.service.ClienteService;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoResponse;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.repository.OrcamentoRepository;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static br.com.bonfimvariedades.clienteproduto.matricula.annotation.constraints.Valid.validaSolicitacao;

@Service
@Log4j2
@RequiredArgsConstructor
public class OrcamentoApplicationService implements OrcamentoService {
    private final OrcamentoRepository orcamentoRepository;
    private final ClienteService clienteService;
    private final ProdutoRepository produtoRepository;

    @Override
    public OrcamentoResponse saveOrcamento(OrcamentoRequest orcamentoRequest) {
        log.info("[inicia] OrcamentoApplicationService - saveOrcamento");
        Produto produto = produtoRepository.getOneProduto(orcamentoRequest.getIdProduto());
        validaSolicitacao(orcamentoRequest, produto);
        Cliente orcamentoByCliente = clienteService.getOrcamentoByCliente(orcamentoRequest);
        Orcamento orcamento = orcamentoRepository.saveOrcamento(new Orcamento(orcamentoByCliente, produto, orcamentoRequest));
        log.info("[finaliza] OrcamentoApplicationService - saveOrcamento");
        return new OrcamentoResponse(orcamento);
    }

    @Override
    public OrcamentoResponse getOneOrcamento(Long idOrcamento) {
        log.info("[inicia] OrcamentoApplicationService - getOneOrcamento");
        Orcamento orcamento = orcamentoRepository.getOneOrcamento(idOrcamento);
        log.info("[finaliza] OrcamentoApplicationService - getOneOrcamento");
        return new OrcamentoResponse(orcamento);
    }
}