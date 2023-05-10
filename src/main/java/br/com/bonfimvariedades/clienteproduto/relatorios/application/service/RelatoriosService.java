package br.com.bonfimvariedades.clienteproduto.relatorios.application.service;

import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioEstoquesDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosDisponivelResponse;

import java.util.List;

public interface RelatoriosService {
    List<RelatorioClientesResponse> getAllClientes();
    List<RelatorioPedidosDisponivelResponse> getAllPedidosDisponiveis();
    List<RelatorioEstoquesDisponivelResponse> getAllEstoquesDisponiveis();
}