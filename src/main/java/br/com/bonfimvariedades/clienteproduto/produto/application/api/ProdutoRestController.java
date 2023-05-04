package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.application.service.ProdutoService;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class ProdutoRestController implements ProdutoApi {
    private final ProdutoService produtoService;

    @Override
    public ProdutoIdResponse saveproduto(ProdutoRequest request) {
        log.info("[inicia] produtoRestController - post");
        ProdutoIdResponse idResponse = produtoService.saveProduto(request);
        log.info("[finaliza] produtoRestController - post");
        return idResponse;
    }

    @Override
    public ProdutoResponse getOneproduto(UUID idProduto) {
        log.info("[inicia] produtoRestController - post");
        Produto produto = produtoService.getOneProduto(idProduto);
        log.info("[finaliza] produtoRestController - post");
        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> getAllProdutos() {
        log.info("[inicia] produtoRestController - getAll");
        List<Produto> produtos = produtoService.getAllProdutos();
        log.info("[finaliza] produtoRestController - getAll");
        return ProdutoResponse.converte(produtos);
    }

    @Override
    public void deleteProduto(UUID idProduto) {
        log.info("[inicia] produtoRestController - delete");
        produtoService.deleteProduto(idProduto);
        log.info("[finaliza] produtoRestController - delete");
    }

    @Override
    public void updateProduto(UUID idProduto, ProdutoUpdateRequest updateRequest) {
        log.info("[inicia] produtoRestController - update");
        produtoService.updateProduto(idProduto, updateRequest);
        log.info("[finaliza] produtoRestController - update");
    }

    @Override
    public void updateProdutoStatus(UUID idProduto) {
        log.info("[inicia] produtoRestController - updateStatus");
        produtoService.updateProdutoStatus(idProduto);
        log.info("[finaliza] produtoRestController - updateStatus");
    }
}