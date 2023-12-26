package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CompraUpdateRequest {
    String numeroPedido;
    LocalDate dataEmissaoPedido;
    LocalDate previsaoEntrega;
    BigDecimal valorTotalPedido;
}
