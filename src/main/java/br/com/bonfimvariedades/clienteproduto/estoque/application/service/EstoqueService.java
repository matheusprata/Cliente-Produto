package br.com.bonfimvariedades.clienteproduto.estoque.application.service;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueIdResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;

public interface EstoqueService {
    EstoqueIdResponse saveEstoque(EstoqueRequest request);

}
