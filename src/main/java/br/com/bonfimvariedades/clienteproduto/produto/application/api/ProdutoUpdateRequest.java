package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProdutoUpdateRequest {
    @NotBlank
    String nomeProduto;
    BigDecimal valorproduto;
}