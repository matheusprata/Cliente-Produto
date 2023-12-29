package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CompraRequest{
    @NotNull(message = "Campo Id Fornecedor Obrigatório!")
    UUID idFornecedor;
    @NotNull(message = "Campo Id Funcionario Obrigatório!")
    UUID idFuncionario;
    @NotNull(message = "Campo Id Estoque Obrigatório!")
    UUID idEstoque;
    @NotNull(message = "Campo Numero Pedido Obrigatório!")
    String numeroPedido;
    @NotNull(message = "Campo data emissão pedido Obrigatório!")
    LocalDate dataEmissaoPedido;
    @NotNull(message = "Campo quantidade compra estoque Obrigatório!")
    BigDecimal quantidadeCompraEstoque;
    LocalDate previsaoEntrega;
    @NotNull(message = "Campo tipo pagamento Obrigatório!")
    TipoPagamento tipoPagamento;
    @NotNull(message = "Campo Numero Pedido Obrigatório!")
    BigDecimal valorTotalPedido;
}