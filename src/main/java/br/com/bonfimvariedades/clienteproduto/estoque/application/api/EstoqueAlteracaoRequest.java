package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import jakarta.validation.constraints.DecimalMin;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class EstoqueAlteracaoRequest {
    String depositoProduto;
    @DecimalMin(message = "A quantidade não pode ser negativo", value = "0.0")
    BigDecimal quantidadeProduto;
    LocalDate dataEntrada;
}
