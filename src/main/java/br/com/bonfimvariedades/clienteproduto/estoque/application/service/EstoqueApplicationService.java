package br.com.bonfimvariedades.clienteproduto.estoque.application.service;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueIdResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueListResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class EstoqueApplicationService implements EstoqueService {
    private final EstoqueRepository estoqueRepository;
    private final ProdutoRepository produtoRepository;
    @Override
    public EstoqueIdResponse saveEstoque(EstoqueRequest request) {
        log.info("[inicia] EstoqueApplicationService - saveEstoque");
        Produto produto = produtoRepository.getOneProduto(request.getIdProduto());
        Estoque estoque = estoqueRepository.salvaEstoque(new Estoque(produto, request));
        log.info("[finaliza] EstoqueApplicationService - saveEstoque");
        return EstoqueIdResponse
                .builder()
                .idEstoque(estoque.getIdEstoque())
                .build();
    }

    @Override
    public Estoque getOneEstoque(UUID idEstoque) {
        log.info("[inicia] EstoqueApplicationService - getOneEstoque");
        Estoque estoque = estoqueRepository.getOneEstoque(idEstoque);
        log.info("[finaliza] EstoqueApplicationService - getOneEstoque");
        return estoque;
    }

    @Override
    public List<EstoqueListResponse> getByIdProduto(UUID idProduto) {
        log.info("[inicia] EstoqueApplicationService - getByIdProduto");
        Produto produto = produtoRepository.getOneProduto(idProduto);
        List<Estoque> estoques = estoqueRepository.getEstoqueByIdProduto(produto);
        log.info("[finaliza] EstoqueApplicationService - getByIdProduto");
        return EstoqueListResponse.converte(estoques);
    }
}