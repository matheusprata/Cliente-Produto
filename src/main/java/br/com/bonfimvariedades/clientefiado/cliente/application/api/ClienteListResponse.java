package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;
import lombok.Value;

@Value
public class ClienteListResponse {
	private UUID idCliente;
	private String nomeCompleto;
	private String email;
	private String celular;
	private String sexo;
	private String tipoPessoa;
	private String cpfOuCnpj;
	private String estadoCivil;

	public ClienteListResponse(Cliente cliente){
		this.idCliente = cliente.getIdCliente();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.tipoPessoa = cliente.getTipoPessoa().toString();
		this.cpfOuCnpj = cliente.getCpfOuCnpj();
		this.celular = cliente.getCelular();
		this.email = cliente.getEmail();
		this.sexo = cliente.getSexo().toString();
		this.estadoCivil = cliente.getEstadoCivil().toString();
	}
	
	public static List<ClienteListResponse> converte(List<Cliente> clientes) {
		return clientes.stream().map(ClienteListResponse::new).collect(Collectors.toList());
	}
		
}
