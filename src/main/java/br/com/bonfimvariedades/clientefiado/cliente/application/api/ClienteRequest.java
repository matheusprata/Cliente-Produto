package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.bonfimvariedades.clientefiado.cliente.domain.EstadoCivil;
import br.com.bonfimvariedades.clientefiado.cliente.domain.Sexo;
import br.com.bonfimvariedades.clientefiado.cliente.domain.TipoPessoa;
import lombok.Value;

@Value
public class ClienteRequest {
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
	private LocalDate dataNascimento;
	private EstadoCivil estadoCivil;
	@NotNull(message = "CPF OU CNPJ OBRIGATORIO")
	private String cpfOuCnpj;
	@NotNull
	private Boolean aceitaTermos;
	
}
