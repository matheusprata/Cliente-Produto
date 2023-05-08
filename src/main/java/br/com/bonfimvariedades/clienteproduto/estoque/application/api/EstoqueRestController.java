package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import br.com.bonfimvariedades.clienteproduto.estoque.application.service.EstoqueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class EstoqueRestController implements EstoqueAPI{

    private final EstoqueService estoqueService;

    @Override
    public EstoqueIdResponse saveEstoque(EstoqueRequest request) {
        log.info("[inicia] produtoRestController - saveProduto");
        EstoqueIdResponse idResponse = estoqueService.saveEstoque(request);
        log.info("[finaliza] produtoRestController - saveProduto");
        return idResponse;
    }
}
