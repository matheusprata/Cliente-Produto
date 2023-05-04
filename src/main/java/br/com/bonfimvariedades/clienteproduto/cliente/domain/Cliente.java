package br.com.bonfimvariedades.clienteproduto.cliente.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.application.api.ClienteRequest;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.ClienteGroupSequenceProvider;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaJuridica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@GroupSequenceProvider(value = ClienteGroupSequenceProvider.class)
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID idCliente;
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa = TipoPessoa.FISICA;
	@NotNull(message = "Campo Nome Obrigatório!")
	private String nomeCompleto;
	@Email
	@Column(unique = true)
	@NotNull(message = "Campo Email Obrigatório!")
	private String email;
	private String celular;
	private String telefone;
	@NotNull(message = "Campo Obrigatório!")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@NotNull(message = "Campo Obrigatório!")
	private LocalDate dataNascimento;
	@NotNull(message = "Campo Obrigatório!")
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@NotNull(message = "Cpf/Cnpj Obrigatório!")
	@CPF(groups = PessoaFisica.class)
	@CNPJ(groups = PessoaJuridica.class)
	@Column(name = "cpf", unique = true, updatable = false)
	private String cpf;
	private Boolean aceitaTermos = true;
	private LocalDate dataDoCadastro;
	private LocalDate dataUltimaAlteracao;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnore
	List<Matricula> matriculas;

	public Cliente(ClienteRequest clienteRequest) {
		this.nomeCompleto = clienteRequest.getNomeCompleto().toUpperCase();
		this.email = clienteRequest.getEmail().toUpperCase();
		this.celular = clienteRequest.getCelular();
		this.telefone = clienteRequest.getTelefone();
		this.sexo = clienteRequest.getSexo();
		this.dataNascimento = clienteRequest.getDataNascimento();
		this.estadoCivil = clienteRequest.getEstadoCivil();
		this.cpf = clienteRequest.getCpf();
	}

	public Cliente(OrcamentoRequest orcamentoRequest) {
		this.cpf = orcamentoRequest.getCpf();
		this.nomeCompleto = orcamentoRequest.getNomeCompleto().toUpperCase();
		this.telefone = orcamentoRequest.getTelefone();
	}

	public void altera(ClienteAlteracaoRequest clienteAlteracaoRequest) {
		this.nomeCompleto = clienteAlteracaoRequest.getNomeCompleto().toUpperCase();
		this.email = clienteAlteracaoRequest.getEmail().toUpperCase();
		this.celular = clienteAlteracaoRequest.getCelular();
		this.telefone = clienteAlteracaoRequest.getTelefone();
		this.sexo = clienteAlteracaoRequest.getSexo();
		this.estadoCivil = clienteAlteracaoRequest.getEstadoCivil();
		this.dataUltimaAlteracao = LocalDate.now();
	}
}