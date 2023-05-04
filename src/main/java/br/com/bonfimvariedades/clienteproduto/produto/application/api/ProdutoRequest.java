package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProdutoRequest {
    @NotBlank
    String nomeProduto;
    Categoria categoria;
    BigDecimal valorproduto;
}