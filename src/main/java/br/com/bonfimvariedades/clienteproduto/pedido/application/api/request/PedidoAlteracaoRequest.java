package br.com.bonfimvariedades.clienteproduto.pedido.application.api.request;

import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class PedidoAlteracaoRequest {
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int desconto;
    @Min(value = 1, message = "O valor mínimo é 1")
    @Max(value = 12, message = "O valor máximo é 12")
    int quantidadeParcelas;
    String observacao;
}