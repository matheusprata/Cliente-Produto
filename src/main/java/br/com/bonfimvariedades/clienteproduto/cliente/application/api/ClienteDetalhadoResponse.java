package br.com.bonfimvariedades.clienteproduto.cliente.application.api;

import java.time.LocalDate;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.TipoPessoa;
import lombok.Value;

@Value
public class ClienteDetalhadoResponse {
	private UUID idCliente;
	private String nomeCompleto;
	private String email;
	private String celular;
	private String sexo;
	private String tipoPessoa;
	private String cpf;
	private String estadoCivil;
	private LocalDate dataCadastro;

	public ClienteDetalhadoResponse(Cliente cliente){
		this.idCliente = cliente.getIdCliente();
		this.nomeCompleto = cliente.getNomeCompleto();
		this.tipoPessoa = cliente.getTipoPessoa().toString();
		this.cpf = cliente.getCpf();
		this.celular = cliente.getCelular();
		this.email = cliente.getEmail();
		this.sexo = cliente.getSexo().toString();
		this.estadoCivil = cliente.getEstadoCivil().toString();
		this.dataCadastro = cliente.getDataCadastro();
	}
	
}
