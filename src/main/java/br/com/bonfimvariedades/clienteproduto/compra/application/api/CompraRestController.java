package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import br.com.bonfimvariedades.clienteproduto.compra.application.service.CompraService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
public class CompraRestController implements CompraAPI{
    private final CompraService compraService;

    @Override
    public CompraIdResponse saveCompra(CompraRequest request) {
        log.info("[inicia] CompraRestController -  saveCompra");
        CompraIdResponse idResponse = compraService.saveCompra(request);
        log.info("[finaliza] CompraRestController -  saveCompra");
        return idResponse;
    }
}
