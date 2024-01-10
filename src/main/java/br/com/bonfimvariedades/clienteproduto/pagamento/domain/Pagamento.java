package br.com.bonfimvariedades.clienteproduto.pagamento.domain;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.api.PagamentoRequest;
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
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPagamento;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    private LocalDate dataPagamento = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private BigDecimal valorPago;

    public Pagamento(PagamentoRequest pagamentoRequest, Pedido pedido) {
        this.tipoPagamento = pagamentoRequest.getTipoPagamento();
        this.valorPago = pagamentoRequest.getValorPago();
        this.pedido = pedido;
    }
    public Pagamento(Pedido pedido, TipoPagamento tipoPagamentoEntrada) {
        this.tipoPagamento = tipoPagamentoEntrada;
        this.valorPago = pedido.getValorEntrada();
        this.pedido = pedido;
    }
}