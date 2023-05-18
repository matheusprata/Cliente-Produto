package br.com.bonfimvariedades.clienteproduto.funcionario.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFuncionario;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Orcamento> orcamentos;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Pedido> pedidos;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Compra> compras;

    @NotBlank(message = "Campo Nome Obrigatório!")
    private String nomeCompleto;
    @NotBlank(message = "Campo CPF Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    @Column(unique = true, length = 14, updatable = false)
    private String cpf;
    @NotNull(message = "Campo Data Nascimento Obrigatório!")
    private LocalDate dataNascimento;
    @NotBlank(message = "Campo Obrigatório!")
    private String cargoFuncionario;
    @NotNull(message = "Campo salario Obrigatório!")
    private BigDecimal salario;
    @NotNull(message = "Campo Obrigatório!")
    @Column(unique = true, length = 11, updatable = false)
    private String cnh;
    @NotNull(message = "Campo Obrigatório!")
    private LocalDate validadeCnh;
    @NotNull(message = "Campo Obrigatório!")
    private LocalDate dataAdmissao;
    private LocalDate dataReadmissao;
    private LocalDate dataDemissao;
    @Enumerated(EnumType.STRING)
    private StatusFuncionario statusFuncionario = StatusFuncionario.ATIVO;

    public Funcionario(FuncionarioResquest request) {
        this.nomeCompleto = request.getNomeCompleto().toUpperCase();
        this.cpf = request.getCpf();
        this.dataNascimento = request.getDataNascimento();
        this.cargoFuncionario = request.getCargoFuncionario().toUpperCase();
        this.salario = request.getSalario();
        this.cnh = request.getCnh();
        this.validadeCnh = request.getValidadeCnh();
        this.dataAdmissao = request.getDataAdmissao();
    }
    public void altera(FuncionarioUpdateResquest updateRequest) {
        this.nomeCompleto = updateRequest.getNomeCompleto().toUpperCase();
        this.cargoFuncionario = updateRequest.getCargoFuncionario().toUpperCase();
        this.cnh = updateRequest.getCnh();
        this.validadeCnh = updateRequest.getValidadeCnh();
    }
    public void demitido() {
        this.statusFuncionario = statusFuncionario.DEMITIDO;
        this.dataDemissao = getDataDemissao().now();
    }
    public void ativa() {
        this.statusFuncionario = statusFuncionario.ATIVO;
        this.dataReadmissao = getDataReadmissao().now();
    }
}