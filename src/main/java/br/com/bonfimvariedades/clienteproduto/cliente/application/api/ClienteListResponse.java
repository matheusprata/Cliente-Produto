package br.com.bonfimvariedades.clienteproduto.cliente.application.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.TipoPessoa;
import lombok.Value;

@Value
public class ClienteListResponse {
	private UUID idCliente;
	private String nomeCompleto;
	private String tipoPessoa;
	private String cpf;

	public ClienteListResponse(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.tipoPessoa = cliente.getTipoPessoa().toString();
		this.cpf = cliente.getCpf().toString();
	}
	
	public static List<ClienteListResponse> converte(List<Cliente> clientes) {
		return clientes.stream().map(ClienteListResponse::new).collect(Collectors.toList());
	}
		
}
