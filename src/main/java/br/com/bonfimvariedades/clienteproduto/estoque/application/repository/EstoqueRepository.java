package br.com.bonfimvariedades.clienteproduto.estoque.application.repository;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;

public interface EstoqueRepository {
    Estoque salvaEstoque(Estoque estoque);
}
