package br.com.bonfimvariedades.clienteproduto.cliente.infra;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
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

	@Override
	public Optional<Cliente> findByCpf(String cpf) {
		log.info("[inicia] - ClienteInfraRepository - findByCpf");
		Optional<Cliente> optionalCliente = clienteSpringDataJPARepository.findByCpf(cpf);
		log.info("[inicia] - ClienteInfraRepository - findByCpf");
		return optionalCliente;
	}
}