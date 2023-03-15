package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.util.UUID;

import lombok.Value;

@Value
public class ClienteDetalhadoResponse {
	private UUID idCliente;
	private String nomeCompleto;	
	private String tipoPessoa;
	private String cpfOuCnpj;
}
