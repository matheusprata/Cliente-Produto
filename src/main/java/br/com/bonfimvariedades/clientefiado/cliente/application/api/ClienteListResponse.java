package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;
import br.com.bonfimvariedades.clientefiado.cliente.domain.EstadoCivil;
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
	private LocalDate dataNascimento;
	private EstadoCivil estadoCivil;

	public static List<ClienteListResponse> converte(List<Cliente> clientes) {
		
		return null;
	}
}
