package br.com.bonfimvariedades.clientefiado.cliente.application.api;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.bonfimvariedades.clientefiado.cliente.domain.EstadoCivil;
import br.com.bonfimvariedades.clientefiado.cliente.domain.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotNull
	private LocalDate dataNascimento;
	@CPF
	private String cpf;
	@NotNull
	private Boolean aceitaTermos;
	private EstadoCivil estadoCivil;
}
