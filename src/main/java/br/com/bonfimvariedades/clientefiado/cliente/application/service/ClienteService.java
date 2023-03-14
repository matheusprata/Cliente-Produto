package br.com.bonfimvariedades.clientefiado.cliente.application.service;

import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ClienteService {

	public ClienteResponse criaCliente(ClienteRequest clienteRequest) {
		log.info("[inicia] ClienteService - criaCliente");
		log.info("[finaliza] ClienteService - criaCliente");
		return null;
	}

}
