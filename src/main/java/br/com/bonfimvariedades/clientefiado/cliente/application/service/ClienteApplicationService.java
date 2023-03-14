package br.com.bonfimvariedades.clientefiado.cliente.application.service;

import org.springframework.stereotype.Service;

import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteResponse;
import br.com.bonfimvariedades.clientefiado.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ClienteApplicationService implements ClienteService {

	private final ClienteRepository clienteRepository;

	@Override
	public ClienteResponse criaCliente(ClienteRequest clienteRequest) {
		log.info("[inicia] ClienteApplicationService - criaCliente");
		Cliente cliente = clienteRepository.salva(new Cliente(ClienteRequest));
		log.info("[finaliza] ClienteApplicationService - criaCliente");
		return null;
	}

}
