package br.com.bonfimvariedades.clienteproduto.matricula.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaRequest;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static br.com.bonfimvariedades.clienteproduto.matricula.annotation.constraints.Valid.calcularValorFinal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMatricula;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @OneToOne
    @JsonIgnore
    private Produto produto;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private BigDecimal valorEntrada;
    private int desconto;
    @Min(value = 1, message = "O valor mínimo é 1")
    @Max(value = 12, message = "O valor máximo é 12")
    private int quantidadeParcelas;
    private BigDecimal valorFinal;
    private LocalDate dataMatricula = LocalDate.now();
    private String observacao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVA;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "matricula")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Matricula(Cliente cliente, Produto produto, MatriculaRequest matriculaRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.tipoPagamento = matriculaRequest.getTipoPagamento();
        this.valorEntrada = matriculaRequest.getValorEntrada();
        this.desconto = matriculaRequest.getDesconto();
        this.quantidadeParcelas = matriculaRequest.getQuantidadeParcelas();
        this.observacao = matriculaRequest.getObservacao().toUpperCase();
        this.valorFinal = calcularValorFinal(matriculaRequest.getDesconto(), produto.getValorProduto());
    }

    public Matricula(Orcamento orcamento) {
        this.cliente = orcamento.getCliente();
        this.produto = orcamento.getProduto();
        this.tipoPagamento = orcamento.getTipoPagamento();
        this.valorEntrada = orcamento.getValorEntrada();
        this.desconto = orcamento.getDesconto();
        this.quantidadeParcelas = orcamento.getQuantidadeParcelas();
        this.observacao = orcamento.getObservacao().toUpperCase();
        this.valorFinal = orcamento.getValorFinal();
    }

    public void altera(MatriculaAlteracaoRequest matriculaAlteracaoRequest) {
        this.tipoPagamento = matriculaAlteracaoRequest.getTipoPagamento();
        this.valorEntrada = matriculaAlteracaoRequest.getValorEntrada();
        this.desconto = matriculaAlteracaoRequest.getDesconto();
        this.quantidadeParcelas = matriculaAlteracaoRequest.getQuantidadeParcelas();
        this.observacao = matriculaAlteracaoRequest.getObservacao().toUpperCase();
    }

    public void finalizaMatricula() {
        this.status = Status.INATIVA;
    }

    public void ativaMatricula() { this.status = Status.ATIVA; }

    public void cancelaMatricula() { this.status = Status.CANCELADA; }
}