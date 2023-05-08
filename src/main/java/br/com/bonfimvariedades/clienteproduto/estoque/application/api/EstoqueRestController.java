package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import br.com.bonfimvariedades.clienteproduto.estoque.application.service.EstoqueService;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoResponse;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EstoqueRestController implements EstoqueAPI{

    private final EstoqueService estoqueService;

    @Override
    public EstoqueIdResponse saveEstoque(EstoqueRequest request) {
        log.info("[inicia] EstoqueRestController - saveEstoque");
        EstoqueIdResponse idResponse = estoqueService.saveEstoque(request);
        log.info("[finaliza] EstoqueRestController - saveEstoque");
        return idResponse;
    }

    @Override
    public EstoqueResponse getOneEstoque(UUID idEstoque) {
        log.info("[inicia] EstoqueRestController - getOneEstoque");
        Estoque estoque = estoqueService.getOneEstoque(idEstoque);
        log.info("[finaliza] EstoqueRestController - getOneEstoque");
        return new EstoqueResponse(estoque);
    }

    @Override
    public List<EstoqueListResponse> getAllEstoqueByProduto(UUID idProduto) {
        log.info("[inicia] EstoqueRestController - getAllEstoqueByProduto");
        List<EstoqueListResponse> buscaEstoqueProduto = estoqueService.getByIdProduto(idProduto);
        log.info("[finaliza] EstoqueRestController - getAllEstoqueByProduto");
        return buscaEstoqueProduto;
    }
}
