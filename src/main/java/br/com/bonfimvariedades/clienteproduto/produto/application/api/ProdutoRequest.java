package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class ProdutoRequest {
    @NotNull(message = "O identificador do fornecedor não pode ser nulo")
    UUID idFornecedor;
    @NotBlank(message = "O nome do produto não pode ser nulo ou em branco")
    String nomeProduto;
    @NotNull(message = "O tipo de categoria não pode ser nulo")
    Categoria categoria;
    @NotNull
    @DecimalMin(message = "A quantidade não pode ser negativo", value = "0.0")
    BigDecimal valorProduto;
}