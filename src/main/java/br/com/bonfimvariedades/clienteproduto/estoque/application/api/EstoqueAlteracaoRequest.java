package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class EstoqueAlteracaoRequest {
    @NotNull
    String depositoProduto;
    @NotNull
    @DecimalMin(message = "A quantidade não pode ser negativo", value = "0.0")
    BigDecimal quantidadeProduto;
    @NotNull
    LocalDate dataEntrada;
}
