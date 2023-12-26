package br.com.bonfimvariedades.clienteproduto.compra.application.service;

import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraIdResponse;
import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraRequest;

public interface CompraService {
    CompraIdResponse saveCompra(CompraRequest request);
}
