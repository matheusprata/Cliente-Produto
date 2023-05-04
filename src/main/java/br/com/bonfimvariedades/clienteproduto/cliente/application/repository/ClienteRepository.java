package br.com.bonfimvariedades.clienteproduto.cliente.application.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;

public interface ClienteRepository {

	Cliente salva(Cliente cliente);

	List<Cliente> buscaTodosClientes();

	Cliente buscaClienteAtravesId(UUID idCliente);

	void deletaClienteAtravesId(Cliente cliente);

	Optional<Cliente> findByCpf(String cpf);
}
