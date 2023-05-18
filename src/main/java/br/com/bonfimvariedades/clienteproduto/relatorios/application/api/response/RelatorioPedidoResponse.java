package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class RelatorioPedidoResponse {
    private UUID idPedido;
    private TipoPagamento tipoPagamento;
    private BigDecimal valorEntrada;
    private BigDecimal valorFinal;
    private LocalDate dataPedido;
    private String status;

    public RelatorioPedidoResponse(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.valorEntrada = pedido.getValorEntrada();
        this.valorFinal = pedido.getValorFinal();
        this.dataPedido = pedido.getDataPedido();
        this.status = pedido.getStatus().toString();
    }

    public static List<RelatorioPedidoResponse> converte(List<Pedido> pedidos){
        return pedidos.stream()
                .map(RelatorioPedidoResponse::new)
                .collect(Collectors.toList());
    }
}