package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.DecimalMin;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class ProdutoUpdateRequest {
    String nomeProduto;
    Categoria categoria;
    @DecimalMin(message = "A quantidade não pode ser negativo", value = "0.0")
    BigDecimal valorProduto;
}