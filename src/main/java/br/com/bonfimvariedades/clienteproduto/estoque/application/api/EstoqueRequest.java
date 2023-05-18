package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.SolicitacaoRequest;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Value
public class EstoqueRequest extends SolicitacaoRequest {
    @NotNull(message = "Necessario o id do produto")
    UUID idProduto;
    @NotNull
    String depositoProduto;
    @NotNull
    @DecimalMin(message = "A quantidade não pode ser negativo", value = "0.0")
    BigDecimal quantidadeProduto;
    @NotNull
    LocalDate dataEntrada;
}
