package br.com.bonfimvariedades.clienteproduto.pedido.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
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

import static br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints.Valid.calcularValorFinal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPedido;

    @OneToOne(fetch = FetchType.LAZY)
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
    private LocalDate dataPedido = LocalDate.now();
    private String observacao;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVA;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pedido")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Pedido(Cliente cliente, Produto produto, PedidoRequest pedidoRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.tipoPagamento = pedidoRequest.getTipoPagamento();
        this.valorEntrada = pedidoRequest.getValorEntrada();
        this.desconto = pedidoRequest.getDesconto();
        this.quantidadeParcelas = pedidoRequest.getQuantidadeParcelas();
        this.observacao = pedidoRequest.getObservacao().toUpperCase();
        this.valorFinal = calcularValorFinal(pedidoRequest.getDesconto(), produto.getValorProduto());
    }

    public Pedido(Orcamento orcamento) {
        this.cliente = orcamento.getCliente();
        this.produto = orcamento.getProduto();
        this.tipoPagamento = orcamento.getTipoPagamento();
        this.valorEntrada = orcamento.getValorEntrada();
        this.desconto = orcamento.getDesconto();
        this.quantidadeParcelas = orcamento.getQuantidadeParcelas();
        this.observacao = orcamento.getObservacao().toUpperCase();
        this.valorFinal = orcamento.getValorFinal();
    }

    public void altera(PedidoAlteracaoRequest pedidoAlteracaoRequest) {
        this.tipoPagamento = pedidoAlteracaoRequest.getTipoPagamento();
        this.valorEntrada = pedidoAlteracaoRequest.getValorEntrada();
        this.desconto = pedidoAlteracaoRequest.getDesconto();
        this.quantidadeParcelas = pedidoAlteracaoRequest.getQuantidadeParcelas();
        this.observacao = pedidoAlteracaoRequest.getObservacao().toUpperCase();
    }

    public void finalizaPedido() {
        this.status = Status.INATIVA;
    }

    public void ativaPedido() { this.status = Status.ATIVA; }

    public void cancelaPedido() { this.status = Status.CANCELADA; }
}