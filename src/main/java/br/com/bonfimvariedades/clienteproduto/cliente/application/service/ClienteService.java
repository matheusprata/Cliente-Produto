package br.com.bonfimvariedades.clienteproduto.cliente.application.service;

import java.util.List;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteListResponse;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteResponse;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;

public interface ClienteService {

	ClienteResponse criaCliente(ClienteRequest clienteRequest);

	List<ClienteListResponse> buscaTodosClientes();

	ClienteDetalhadoResponse buscaClienteAtravesId(UUID idCliente);

	void deletaClienteAtravesId(UUID idCliente);

	void patchAlteraCliente(UUID idCliente, ClienteAlteracaoRequest clienteAlteracaoRequest);

	Cliente getOrcamentoByCliente(OrcamentoRequest orcamentoRequest);
}
