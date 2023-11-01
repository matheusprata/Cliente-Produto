package br.com.bonfimvariedades.clienteproduto.pedido.application.api.request;

import br.com.bonfimvariedades.clienteproduto.pedido.annotation.TipoPagamentoEntradaConstraint;
import br.com.bonfimvariedades.clienteproduto.pedido.annotation.ValidSolicitacaoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@ValidSolicitacaoRequest
public class SolicitacaoRequest {
    @NotNull(message = "Funcionario Obrigatório!")
    UUID idFuncionario;
    @NotNull(message = "Necessario o id do produto")
    UUID idProduto;
    @NotNull(message = "O tipo de pagamento não pode ser nulo")
    TipoPagamento tipoPagamento;
    @DecimalMin(message = "O valor de entrada não pode ser negativo", value = "0.0")
    BigDecimal valorEntrada;
    int desconto;
    @NotNull(message = "Campo Obrigatório!")
    @Min(value = 1, message = "O valor mínimo é 1")
    @Max(value = 12, message = "O valor máximo é 12")
    int quantidadeParcelas;
    @TipoPagamentoEntradaConstraint(message = "O tipo de pagamento de entrada deve ser DINHEIRO, CARTAO_DEBITO ou PIX")
    String tipoPagamentoEntrada;
    String observacao;
}