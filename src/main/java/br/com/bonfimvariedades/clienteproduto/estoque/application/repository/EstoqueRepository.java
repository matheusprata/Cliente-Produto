package br.com.bonfimvariedades.clienteproduto.estoque.application.repository;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface EstoqueRepository {
    Estoque salvaEstoque(Estoque estoque);
    Estoque getOneEstoque(UUID idEstoque);
    List<Estoque> getEstoqueByIdProduto(Produto produto);
    Estoque buscaEstoqueAtravesId(UUID idEstoque);
    List<Estoque> getAllEstoque();
}