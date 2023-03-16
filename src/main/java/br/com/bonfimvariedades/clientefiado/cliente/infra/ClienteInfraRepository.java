package br.com.bonfimvariedades.clientefiado.cliente.infra;

import java.util.List;
import java.util.UUID;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.bonfimvariedades.clientefiado.cliente.application.handler.APIException;
import br.com.bonfimvariedades.clientefiado.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
@RequiredArgsConstructor
public class ClienteInfraRepository implements ClienteRepository {

	private final ClienteSpringDataJPARepository clienteSpringDataJPARepository;

	@Override
	public Cliente salva(Cliente cliente) {
		log.info("[inicia] ClienteInfraRepository - salva");
		try {
		clienteSpringDataJPARepository.save(cliente);
		}catch(DataIntegrityViolationException e){
			throw APIException.build(HttpStatus.BAD_REQUEST, "Existem dados duplicados", e);
		}
		log.info("[finaliza] ClienteInfraRepository - salva");
		return cliente;
	}

	@Override
	public List<Cliente> buscaTodosClientes() {
		log.info("[inicia] - ClienteInfraRepository - buscaTodosClientes");
		List<Cliente> todosClientes = clienteSpringDataJPARepository.findAll();
		log.info("[finaliza] - ClienteInfraRepository - buscaTodosClientes");
		return todosClientes;
	}

	@Override
	public Cliente buscaClienteAtravesId(UUID idCliente) {
		log.info("[inicia] - ClienteInfraRepository - buscaClienteAtravesId");
		Cliente cliente = clienteSpringDataJPARepository.findById(idCliente)
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
		log.info("[finaliza] - ClienteInfraRepository - buscaClienteAtravesId");
		return cliente;
	}

	@Override
	public void deletaClienteAtravesId(Cliente cliente) {
		log.info("[inicia] - ClienteInfraRepository - deletaClienteAtravesId");
		clienteSpringDataJPARepository.delete(cliente);
		log.info("[finaliza] - ClienteInfraRepository - deletaClienteAtravesId");
	}
	
	

}
