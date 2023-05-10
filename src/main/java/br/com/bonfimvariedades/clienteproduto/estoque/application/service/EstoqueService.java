package br.com.bonfimvariedades.clienteproduto.estoque.application.service;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueIdResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueListResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;

import java.util.List;
import java.util.UUID;

public interface EstoqueService {
    EstoqueIdResponse saveEstoque(EstoqueRequest request);
    Estoque getOneEstoque(UUID idEstoque);
    List<EstoqueListResponse> getByIdProduto(UUID idProduto);
    void patchAlteraEstoque(UUID idEstoque, EstoqueAlteracaoRequest estoqueAlteracaoRequest);
}
