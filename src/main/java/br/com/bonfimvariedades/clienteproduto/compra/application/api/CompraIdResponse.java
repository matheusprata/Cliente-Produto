package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CompraIdResponse {
    UUID idCompra;
}
