package br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraRequest;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CompraRequest {
    @NotNull(message = "Campo Id Fornecedor Obrigatório!")
    UUID idFornecedor;
    @NotNull(message = "Campo Id Funcionario Obrigatório!")
    UUID idFuncionario;
    @NotNull(message = "Campo Id Estoque Obrigatório!")
    UUID idEstoque;
    @NotNull(message = "Campo Numero Pedido Obrigatório!")
    private String numeroPedido;
    @NotBlank(message = "Campo Obrigatório!")
    private LocalDate dataEmissaoPedido;
    private LocalDate previsaoEntrega;

    private String formaPagamento;
    private BigDecimal valorTotalPedido;
}
