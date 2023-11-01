package br.com.bonfimvariedades.clienteproduto.orcamento.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints.Valid;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.api.OrcamentoRequest;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Orcamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idOrcamento;

    @OneToOne
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    protected LocalDate dataOrcamento = LocalDate.now();
    private BigDecimal valorEntrada;
    private int desconto;
    @Min(value = 1, message = "O valor mínimo é 1")
    @Max(value = 12, message = "O valor máximo é 12")
    private int quantidadeParcelas;
    private String observacao;
    private LocalDate validade = dataOrcamento.plusDays(5);
    private BigDecimal valorFinal;

    public Orcamento(Cliente cliente, Produto produto, Funcionario funcionario, OrcamentoRequest orcamentoRequest) {
        this.cliente = cliente;
        this.produto = produto;
        this.funcionario = funcionario;
        this.tipoPagamento = orcamentoRequest.getTipoPagamento();
        this.valorEntrada = orcamentoRequest.getValorEntrada();
        this.desconto = orcamentoRequest.getDesconto();
        this.quantidadeParcelas = orcamentoRequest.getQuantidadeParcelas();
        this.observacao = orcamentoRequest.getObservacao();
        this.valorFinal = Valid.calcularValorFinal(orcamentoRequest.getDesconto(), produto.getValorProduto());
    }
}