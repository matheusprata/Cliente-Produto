package br.com.bonfimvariedades.clienteproduto.cliente.application.api;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.EstadoCivil;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Sexo;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.TipoPessoa;
import lombok.Value;

@Value
public class ClienteAlteracaoRequest {
	
	@NotNull
	private String nomeCompleto;
	@NotNull
	@Email
	private String email;
	private String celular;
	private String telefone;
	private Sexo sexo;
	@NotNull(message = "Tipo pessoa é obrigatório")
	private TipoPessoa tipoPessoa;
	@NotNull
	private EstadoCivil estadoCivil;	
}
