package br.com.bonfimvariedades.clienteproduto.produto.application.repository;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepository {
    Produto salvaProduto(Produto produto);
    Produto getOneProduto(UUID idProduto);
    List<Produto> getAllProdutos();
    void deleteProduto(UUID idProduto);
}