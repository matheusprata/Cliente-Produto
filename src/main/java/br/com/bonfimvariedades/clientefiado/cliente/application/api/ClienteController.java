package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import br.com.bonfimvariedades.clientefiado.cliente.application.service.ClienteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ClienteController implements ClienteApi {

	private final ClienteService clienteService;

	public ClienteResponse postCliente(ClienteRequest clienteRequest) {
		log.info("[inicia] ClienteController - postCliente");
		ClienteResponse clienteCriado = clienteService.criaCliente(clienteRequest);
		log.info("[finaliza] ClienteController - postCliente");
		return clienteCriado;
	}

	@Override
	public List<ClienteListResponse> getTodosClientes() {
		log.info("[inicia] - ClienteController - getTodosClientes");
		List<ClienteListResponse> clientes = clienteService.buscaTodosClientes();
		log.info("[inicia] - ClienteController - getTodosClientes");
		return clientes;
	}

}
