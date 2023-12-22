package br.com.bonfimvariedades.clienteproduto.pedido.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @OneToOne
    @JsonIgnore
    private Produto produto;

    @OneToOne
    @JsonIgnore
    private Estoque estoque;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull
    private BigDecimal quantidadeProdutoPedido;
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
    private Status status = Status.DISPONIVEL;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "pedido")
    @JsonIgnore
    private List<Pagamento> pagamentos;

    public Pedido(Cliente cliente, Produto produto, Funcionario funcionario, PedidoRequest pedidoRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.funcionario = funcionario;
        this.quantidadeProdutoPedido = pedidoRequest.getQuantidadeProdutoPedido();
        this.tipoPagamento = pedidoRequest.getTipoPagamento();
        this.valorEntrada = pedidoRequest.getValorEntrada();
        this.desconto = pedidoRequest.getDesconto();
        this.quantidadeParcelas = pedidoRequest.getQuantidadeParcelas();
        this.observacao = pedidoRequest.getObservacao().toUpperCase();
        this.valorFinal = calcularValorFinal(pedidoRequest.getDesconto(), produto.getValorProduto(), pedidoRequest.getQuantidadeProdutoPedido());
    }

    public Pedido(Orcamento orcamento) {
        this.cliente = orcamento.getCliente();
        this.produto = orcamento.getProduto();
        this.funcionario = orcamento.getFuncionario();
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

    public void esgotadoPedido() {this.status = Status.ESGOTADO; }

    public void disponivelPedido() { this.status = Status.DISPONIVEL; }

    public void aguardandoChegarPedido() { this.status = Status.AGUARDANDO_CHEGAR; }
}