package br.com.bonfimvariedades.clienteproduto.pagamento.domain;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
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
    @JoinColumn(name = "matricula_id")
    @JsonIgnore
    private Matricula matricula;

    public Pagamento(PagamentoRequest pagamentoRequest, Matricula matricula) {
        this.tipoPagamento = pagamentoRequest.getTipoPagamento();
        this.valorPago = pagamentoRequest.getValorPago();
        this.matricula = matricula;
    }
    public Pagamento(Matricula matricula, TipoPagamento tipoPagamentoEntrada) {
        this.tipoPagamento = tipoPagamentoEntrada;
        this.valorPago = matricula.getValorEntrada();
        this.matricula = matricula;
    }
}