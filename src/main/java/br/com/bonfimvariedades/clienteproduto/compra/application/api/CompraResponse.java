package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import lombok.Value;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class CompraResponse {
    UUID idCompra;
    String numeroPedido;
    LocalDate dataEmissaoPedido;
    LocalDate previsaoEntrega;
    BigDecimal valorTotalPedido;

    public CompraResponse(Compra compra){
        this.idCompra = compra.getIdCompra();
        this.numeroPedido = compra.getNumeroPedido();
        this.dataEmissaoPedido = compra.getDataEmissaoPedido();
        this.previsaoEntrega = compra.getPrevisaoEntrega();
        this.valorTotalPedido = compra.getValorTotalPedido();
    }
    public static List<CompraResponse> converte(List<Compra> compras) {
        return compras
                .stream()
                .map(CompraResponse::new)
                .toList();
    }
}
