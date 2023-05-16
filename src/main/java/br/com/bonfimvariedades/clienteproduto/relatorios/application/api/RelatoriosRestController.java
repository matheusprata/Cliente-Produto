package br.com.bonfimvariedades.clienteproduto.relatorios.application.api;

import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioEstoquesDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioFuncionariosDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosDisponivelResponse;
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
    public List<RelatorioPedidosDisponivelResponse> getAllPedidos() {
        log.info("[inicia] - RelatoriosRestController - getAllPedidosAtivas");
        List<RelatorioPedidosDisponivelResponse> response = relatoriosService.getAllPedidosDisponiveis();
        log.info("[finaliza] - RelatoriosRestController - getAllPedidosAtivas");
        return response;
    }

    @Override
    public List<RelatorioEstoquesDisponivelResponse> getAllEstoques() {
        log.info("[inicia] - RelatoriosRestController - getAllEstoques");
        List<RelatorioEstoquesDisponivelResponse> response = relatoriosService.getAllEstoquesDisponiveis();
        log.info("[finaliza] - RelatoriosRestController - getAllEstoques");
        return response;
    }

    @Override
    public List<RelatorioFuncionariosDisponivelResponse> getAllFuncionarios() {
        log.info("[inicia] - RelatoriosRestController - getAllFuncionarios");
        List<RelatorioFuncionariosDisponivelResponse> response = relatoriosService.getAllFuncionariosDisponiveis();
        log.info("[finaliza] - RelatoriosRestController - getAllFuncionarios");
        return response;
    }
}