package br.com.bonfimvariedades.clienteproduto.relatorios.application.api;

import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioEstoquesDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioFuncionariosDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosDisponivelResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Tag(name = "Relatórios", description = "Relatórios APIs")
@RequestMapping("/v1/relatorios")
public interface RelatoriosApi {

    @GetMapping("/clientes")
    @ResponseStatus(code = HttpStatus.OK)
    List<RelatorioClientesResponse> getAllClientes();

    @GetMapping("/pedidos-ativos")
    @ResponseStatus(code = HttpStatus.OK)
    List<RelatorioPedidosDisponivelResponse> getAllPedidos();

    @GetMapping("/estoques")
    @ResponseStatus(code = HttpStatus.OK)
    List<RelatorioEstoquesDisponivelResponse> getAllEstoques();

    @GetMapping("/funcionarios")
    @ResponseStatus(code = HttpStatus.OK)
    List<RelatorioFuncionariosDisponivelResponse> getAllFuncionarios();
}