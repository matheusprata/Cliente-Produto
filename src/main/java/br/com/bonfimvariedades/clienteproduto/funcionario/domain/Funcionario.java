package br.com.bonfimvariedades.clienteproduto.funcionario.domain;

import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String cargoFuncionario;
    private BigDecimal salario;
    private String cnh;
    private LocalDate validadeCnh;
    private LocalDate dataAdmissao;
    private LocalDate dataReadmissao;
    private LocalDate dataDemissao;
    @Enumerated(EnumType.STRING)
    private StatusFuncionario statusFuncionario = StatusFuncionario.ATIVO;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Orcamento> orcamentos;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "funcionario")
    @JsonIgnore
    private List<Pedido> pedidos;

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