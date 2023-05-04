package br.com.bonfimvariedades.clienteproduto.cliente.application.api;

import java.time.LocalDate;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.EstadoCivil;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Sexo;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.TipoPessoa;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

@Value
public class ClienteRequest {
	private String nomeCompleto;
	@Email
	private String email;
	private String celular;
	private String telefone;
	private Sexo sexo;
	private LocalDate dataNascimento;
	private EstadoCivil estadoCivil;
	@NotNull(message = "CPF OU CNPJ OBRIGATORIO")
	@CPF(groups = PessoaFisica.class)
	private String cpf;
	private Boolean aceitaTermos;
}
