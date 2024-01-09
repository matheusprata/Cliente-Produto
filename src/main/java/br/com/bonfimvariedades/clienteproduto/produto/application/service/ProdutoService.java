package br.com.bonfimvariedades.clienteproduto.produto.application.service;


import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoIdResponse;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateStatusRequest;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoService {
    ProdutoIdResponse saveProduto(ProdutoRequest request);
    Produto getOneProduto(UUID idProduto);
    List<Produto> getAllProdutos();
    void deleteProduto(UUID idProduto);
    void updateProduto(UUID idProduto, ProdutoUpdateRequest updateRequest);
    void updateProdutoStatus(UUID idProduto, ProdutoUpdateStatusRequest produtoUpdateStatusRequest);
}