package br.com.bonfimvariedades.clienteproduto.compra.domain;

import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraRequest;
import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraUpdateRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCompra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    @JsonIgnore
    private Funcionario funcionario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fornecedor_id")
    @JsonIgnore
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estoque_id")
    @JsonIgnore
    private Estoque estoque;

    @NotNull(message = "Campo Numero Pedido Obrigatório!")
    private String numeroPedido;
    @NotNull(message = "Campo Obrigatório!")
    private LocalDate dataEmissaoPedido;
    private BigDecimal quantidadeCompraEstoque;
    private LocalDate previsaoEntrega;
    private TipoPagamento tipoPagamento;
    private BigDecimal valorTotalPedido;

    public Compra(Fornecedor fornecedor,Funcionario funcionario, Estoque estoque, CompraRequest request){
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
        this.estoque = estoque;
        this.numeroPedido = request.getNumeroPedido();
        this.dataEmissaoPedido = request.getDataEmissaoPedido();
        this.previsaoEntrega = request.getPrevisaoEntrega();
        this.valorTotalPedido = request.getValorTotalPedido();
    }
    public void altera(CompraUpdateRequest request){
        this.numeroPedido = request.getNumeroPedido();
        this.dataEmissaoPedido = request.getDataEmissaoPedido();
        this.previsaoEntrega = request.getPrevisaoEntrega();
        this.valorTotalPedido = request.getValorTotalPedido();
    }
}