package br.com.bonfimvariedades.clientefiado.cliente.application.service;

import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteResponse;


public interface ClienteService {

	public ClienteResponse criaCliente(ClienteRequest clienteRequest);

}
