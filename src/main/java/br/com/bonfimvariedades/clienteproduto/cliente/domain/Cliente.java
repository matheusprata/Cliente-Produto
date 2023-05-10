package br.com.bonfimvariedades.clienteproduto.cliente.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
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
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	private EstadoCivil estadoCivil;
	@CPF(groups = PessoaFisica.class)
	@CNPJ(groups = PessoaJuridica.class)
	@Column(name = "cpf", unique = true, updatable = false)
	private String cpf;
	private Boolean aceitaTermos = true;
	private LocalDate dataDoPedido;
	private LocalDate dataUltimaAlteracao;
	private LocalDate dataCadastro;

	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cliente")
	@JsonIgnore
	List<Pedido> pedidos;

	public Cliente(ClienteRequest clienteRequest) {
		this.nomeCompleto = clienteRequest.getNomeCompleto().toUpperCase();
		this.email = clienteRequest.getEmail().toUpperCase();
		this.celular = clienteRequest.getCelular();
		this.telefone = clienteRequest.getTelefone();
		this.sexo = clienteRequest.getSexo();
		this.dataNascimento = clienteRequest.getDataNascimento();
		this.estadoCivil = clienteRequest.getEstadoCivil();
		this.cpf = clienteRequest.getCpf();
		this.dataCadastro = clienteRequest.getDataCadastro();
	}

	public Cliente(OrcamentoRequest orcamentoRequest) {
		this.cpf = orcamentoRequest.getCpf();
		this.nomeCompleto = orcamentoRequest.getNomeCompleto().toUpperCase();
		this.telefone = orcamentoRequest.getTelefone();
		this.email = orcamentoRequest.getEmail();
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