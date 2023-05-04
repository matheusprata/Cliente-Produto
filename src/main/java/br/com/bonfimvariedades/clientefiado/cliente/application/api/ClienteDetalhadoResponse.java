package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.util.UUID;

import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;
import lombok.Value;

@Value
public class ClienteDetalhadoResponse {
	private UUID idCliente;
	private String nomeCompleto;	
	private String tipoPessoa;
	private String cpfOuCnpj;

	public ClienteDetalhadoResponse(Cliente cliente) {
		this.idCliente = cliente.getIdCliente();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.tipoPessoa = cliente.getTipoPessoa().toString();
		this.cpfOuCnpj = cliente.getCpfOuCnpj().toString();
	}
	
}
