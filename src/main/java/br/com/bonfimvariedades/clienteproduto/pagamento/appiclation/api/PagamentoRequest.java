package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PagamentoRequest {
    TipoPagamento tipoPagamento;
    BigDecimal valorPago;
}