package br.com.bonfimvariedades.clienteproduto.pedido.application.api.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class PedidoIdResponse {
    UUID idPedido;
}
