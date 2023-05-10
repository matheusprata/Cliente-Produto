package br.com.bonfimvariedades.clienteproduto.pagamento.application.api;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PagamentoRequest {
    TipoPagamento tipoPagamento;
    BigDecimal valorPago;
}