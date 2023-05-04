package br.com.bonfimvariedades.clienteproduto.cadastro.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroRequest;
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

import static br.com.bonfimvariedades.clienteproduto.cadastro.annotation.constraints.Valid.calcularValorFinal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Cadastro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCadastro;

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
    private LocalDate dataCadastro = LocalDate.now();
    private String observacao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVA;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "cadastro")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Cadastro(Cliente cliente, Produto produto, CadastroRequest cadastroRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.tipoPagamento = cadastroRequest.getTipoPagamento();
        this.valorEntrada = cadastroRequest.getValorEntrada();
        this.desconto = cadastroRequest.getDesconto();
        this.quantidadeParcelas = cadastroRequest.getQuantidadeParcelas();
        this.observacao = cadastroRequest.getObservacao().toUpperCase();
        this.valorFinal = calcularValorFinal(cadastroRequest.getDesconto(), produto.getValorProduto());
    }

    public Cadastro(Orcamento orcamento) {
        this.cliente = orcamento.getCliente();
        this.produto = orcamento.getProduto();
        this.tipoPagamento = orcamento.getTipoPagamento();
        this.valorEntrada = orcamento.getValorEntrada();
        this.desconto = orcamento.getDesconto();
        this.quantidadeParcelas = orcamento.getQuantidadeParcelas();
        this.observacao = orcamento.getObservacao().toUpperCase();
        this.valorFinal = orcamento.getValorFinal();
    }

    public void altera(CadastroAlteracaoRequest cadastroAlteracaoRequest) {
        this.tipoPagamento = cadastroAlteracaoRequest.getTipoPagamento();
        this.valorEntrada = cadastroAlteracaoRequest.getValorEntrada();
        this.desconto = cadastroAlteracaoRequest.getDesconto();
        this.quantidadeParcelas = cadastroAlteracaoRequest.getQuantidadeParcelas();
        this.observacao = cadastroAlteracaoRequest.getObservacao().toUpperCase();
    }

    public void finalizaCadastro() {
        this.status = Status.INATIVA;
    }

    public void ativaCadastro() { this.status = Status.ATIVA; }

    public void cancelaCadastro() { this.status = Status.CANCELADA; }
}