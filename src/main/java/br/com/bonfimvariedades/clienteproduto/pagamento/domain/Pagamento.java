package br.com.bonfimvariedades.clienteproduto.pagamento.domain;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoRequest;
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
    private LocalDate dataPagamento = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private TipoPagamento tipoPagamento;
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "cadastro_id")
    @JsonIgnore
    private Cadastro cadastro;

    public Pagamento(PagamentoRequest pagamentoRequest, Cadastro cadastro) {
        this.tipoPagamento = pagamentoRequest.getTipoPagamento();
        this.valorPago = pagamentoRequest.getValorPago();
        this.cadastro = cadastro;
    }
    public Pagamento(Cadastro cadastro, TipoPagamento tipoPagamentoEntrada) {
        this.tipoPagamento = tipoPagamentoEntrada;
        this.valorPago = cadastro.getValorEntrada();
        this.cadastro = cadastro;
    }
}