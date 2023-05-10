package br.com.bonfimvariedades.clienteproduto.relatorios.application.service;

import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosAtivasResponse;

import java.util.List;

public interface RelatoriosService {
    List<RelatorioClientesResponse> getAllClientes();
    List<RelatorioPedidosAtivasResponse> getAllPedidosAtivas();
}