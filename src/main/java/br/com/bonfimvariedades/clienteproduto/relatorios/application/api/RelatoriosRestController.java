package br.com.bonfimvariedades.clienteproduto.relatorios.application.api;

import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosAtivasResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.service.RelatoriosService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
public class RelatoriosRestController implements RelatoriosApi {
    private final RelatoriosService relatoriosService;

    @Override
    public List<RelatorioClientesResponse> getAllClientes() {
        log.info("[inicia] - RelatoriosRestController - getAllClientes");
        List<RelatorioClientesResponse> response = relatoriosService.getAllClientes();
        log.info("[finaliza] - RelatoriosRestController - getAllClientes");
        return response;
    }

    @Override
    public List<RelatorioPedidosAtivasResponse> getAllPedidos() {
        log.info("[inicia] - RelatoriosRestController - getAllPedidosAtivas");
        List<RelatorioPedidosAtivasResponse> response = relatoriosService.getAllPedidosAtivas();
        log.info("[finaliza] - RelatoriosRestController - getAllPedidosAtivas");
        return response;
    }
}
