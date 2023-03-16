package br.com.bonfimvariedades.clientefiado.cliente.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteAlteracaoRequest;
import br.com.bonfimvariedades.clientefiado.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clientefiado.cliente.domain.groups.ClienteGroupSequenceProvider;
import br.com.bonfimvariedades.clientefiado.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clientefiado.cliente.domain.groups.PessoaJuridica;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@GroupSequenceProvider(value = ClienteGroupSequenceProvider.class)
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @Column(columnDefinition = "uuid", name = "idCliente", updatable = false,
	// unique = true, nullable = false)
	private UUID idCliente;
	@NotBlank
	private String nomeCompleto;
	@NotBlank
	@Email
	@Column(unique = true)
	private String email;
	@NotBlank
	private String celular;
	private String telefone;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@NotNull(message = "Tipo pessoa é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@NotBlank(message = "Cpf/Cnpj Obrigatório!")
	@CPF(groups = PessoaFisica.class)
	@CNPJ(groups = PessoaJuridica.class)
	@Column(name = "cpf_cnpj", unique = true, updatable = false)
	private String cpfOuCnpj;
	@NotNull
	private Boolean aceitaTermos;

	private LocalDateTime dataHoraDoCadastro;
	private LocalDateTime dataHoraDaUltimaAlteracao;

	public Cliente(ClienteRequest clienteRequest) {
		this.nomeCompleto = clienteRequest.getNomeCompleto();
		this.email = clienteRequest.getEmail();
		this.celular = clienteRequest.getCelular();
		this.telefone = clienteRequest.getTelefone();
		this.sexo = clienteRequest.getSexo();
		this.tipoPessoa = clienteRequest.getTipoPessoa();
		this.dataNascimento = clienteRequest.getDataNascimento();
		this.estadoCivil = clienteRequest.getEstadoCivil();
		this.cpfOuCnpj = clienteRequest.getCpfOuCnpj();
		this.aceitaTermos = clienteRequest.getAceitaTermos();
		this.dataHoraDoCadastro = LocalDateTime.now();
	}

	public void altera(ClienteAlteracaoRequest clienteAlteracaoRequest) {
		this.nomeCompleto = clienteAlteracaoRequest.getNomeCompleto();
		this.email = clienteAlteracaoRequest.getEmail();
		this.celular = clienteAlteracaoRequest.getCelular();
		this.telefone = clienteAlteracaoRequest.getTelefone();
		this.sexo = clienteAlteracaoRequest.getSexo();
		this.tipoPessoa = clienteAlteracaoRequest.getTipoPessoa();
		this.estadoCivil = clienteAlteracaoRequest.getEstadoCivil();
		this.cpfOuCnpj = clienteAlteracaoRequest.getCpfOuCnpj();
		this.dataHoraDaUltimaAlteracao = LocalDateTime.now();
		
	}
}
