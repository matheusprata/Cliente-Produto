package br.com.bonfimvariedades.clienteproduto.cliente.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteListResponse;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteResponse;
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
		Cliente cliente = clienteRepository.salva(new Cliente(clienteRequest));
		log.info("[finaliza] ClienteApplicationService - criaCliente");
		return ClienteResponse.builder().idCliente(cliente.getIdCliente()).build();
	}

	@Override
	public List<ClienteListResponse> buscaTodosClientes() {
		log.info("[finaliza] ClienteApplicationService - buscaTodosClientes");
		List<Cliente> clientes = clienteRepository.buscaTodosClientes();
		log.info("[finaliza] ClienteApplicationService - buscaTodosClientes");
		return ClienteListResponse.converte(clientes);
	}

	@Override
	public ClienteDetalhadoResponse buscaClienteAtravesId(UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - buscaTodosClientes");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		log.info("[finaliza] ClienteApplicationService - buscaTodosClientes");
		return new ClienteDetalhadoResponse(cliente);
	}

	@Override
	public void deletaClienteAtravesId(UUID idCliente) {
		log.info("[inicia] ClienteApplicationService - deletaClienteAtravesId");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		clienteRepository.deletaClienteAtravesId(cliente);
		log.info("[finaliza] ClienteApplicationService - deletaClienteAtravesId");
		
	}

	@Override
	public void patchAlteraCliente(UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest) {
		log.info("[inicia] ClienteApplicationService - patchAlteraCliente");
		Cliente cliente = clienteRepository.buscaClienteAtravesId(idCliente);
		cliente.altera(clienteAlteracaoRequest);
		clienteRepository.salva(cliente);
		log.info("[finaliza] ClienteApplicationService - patchAlteraCliente");
	}

	@Override
	public Cliente getOrcamentoByCliente(OrcamentoRequest request) {
		log.info("[inicia] ClienteApplicationService - getOrcamentoByCliente");
		Optional<Cliente> clienteOptional = clienteRepository.findByCpf(request.getCpf());
		Cliente cliente = clienteOptional.orElseGet(() -> clienteRepository.salva(new Cliente(request)));
		log.info("[inicia] ClienteApplicationService - getOrcamentoByCliente");
		return cliente;
	}
}
