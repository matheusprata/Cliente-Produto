package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.bonfimvariedades.clientefiado.cliente.domain.EstadoCivil;
import br.com.bonfimvariedades.clientefiado.cliente.domain.Sexo;
import br.com.bonfimvariedades.clientefiado.cliente.domain.TipoPessoa;
import lombok.Value;

@Value
public class ClienteAlteracaoRequest {
	
	@NotBlank
	private String nomeCompleto;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String celular;
	private String telefone;
	private Sexo sexo;
	@NotNull(message = "Tipo pessoa é obrigatório")
	private TipoPessoa tipoPessoa;
	@NotNull
	private EstadoCivil estadoCivil;
	@NotNull(message = "CPF OU CNPJ OBRIGATORIO")
	private String cpfOuCnpj;
	
}
