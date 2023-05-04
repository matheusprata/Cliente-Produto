package br.com.bonfimvariedades.clienteproduto.pedido.application.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class PedidoRequest extends SolicitacaoRequest{
    UUID idCliente;
}