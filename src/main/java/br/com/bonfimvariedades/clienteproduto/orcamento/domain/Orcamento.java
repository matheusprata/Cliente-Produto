package br.com.bonfimvariedades.clienteproduto.orcamento.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints.Valid;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrcamento;
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private LocalDate dataOrcamento = LocalDate.now();
    private BigDecimal valorEntrada;
    private int desconto;
    private BigDecimal quantidadeProdutoPedido;
    private int quantidadeParcelas;
    private String observacao;
    private LocalDate validade = dataOrcamento.plusDays(5);
    private BigDecimal valorFinal;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Orcamento(Cliente cliente, Produto produto, Funcionario funcionario, OrcamentoRequest orcamentoRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.funcionario = funcionario;
        this.tipoPagamento = orcamentoRequest.getTipoPagamento();
        this.valorEntrada = orcamentoRequest.getValorEntrada();
        this.desconto = orcamentoRequest.getDesconto();
        this.quantidadeProdutoPedido = orcamentoRequest.getQuantidadeProdutoPedido();
        this.quantidadeParcelas = orcamentoRequest.getQuantidadeParcelas();
        this.observacao = orcamentoRequest.getObservacao();
        this.valorFinal = Valid.calcularValorFinal(orcamentoRequest.getDesconto(), produto.getValorProduto(), orcamentoRequest.getQuantidadeProdutoPedido());
    }
}