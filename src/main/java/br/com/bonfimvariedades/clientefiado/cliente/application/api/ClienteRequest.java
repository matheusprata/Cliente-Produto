package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ClienteRequest {
	private UUID idCliente;
}
