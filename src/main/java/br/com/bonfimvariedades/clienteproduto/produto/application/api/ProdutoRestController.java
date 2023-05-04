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
    public ProdutoIdResponse saveProduto(ProdutoRequest request) {
        log.info("[inicia] produtoRestController - saveProduto");
        ProdutoIdResponse idResponse = produtoService.saveProduto(request);
        log.info("[finaliza] produtoRestController - saveProduto");
        return idResponse;
    }

    @Override
    public ProdutoResponse getOneProduto(UUID idProduto) {
        log.info("[inicia] produtoRestController - getOneProduto");
        Produto produto = produtoService.getOneProduto(idProduto);
        log.info("[finaliza] produtoRestController - getOneProduto");
        return new ProdutoResponse(produto);
    }

    @Override
    public List<ProdutoResponse> getAllProdutos() {
        log.info("[inicia] produtoRestController - getAllProdutos");
        List<Produto> produtos = produtoService.getAllProdutos();
        log.info("[finaliza] produtoRestController - getAllProdutos");
        return ProdutoResponse.converte(produtos);
    }

    @Override
    public void deleteProduto(UUID idProduto) {
        log.info("[inicia] produtoRestController - deleteProduto");
        produtoService.deleteProduto(idProduto);
        log.info("[finaliza] produtoRestController - deleteProduto");
    }

    @Override
    public void updateProduto(UUID idProduto, ProdutoUpdateRequest updateRequest) {
        log.info("[inicia] produtoRestController - updateProduto");
        produtoService.updateProduto(idProduto, updateRequest);
        log.info("[finaliza] produtoRestController - updateProduto");
    }

    @Override
    public void updateProdutoStatus(UUID idProduto) {
        log.info("[inicia] produtoRestController - updateProdutoStatus");
        produtoService.updateProdutoStatus(idProduto);
        log.info("[finaliza] produtoRestController - updateProdutoStatus");
    }
}