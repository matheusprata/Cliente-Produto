package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RelatorioPedidosDisponivelResponse {
    UUID idPedido;
    String servico;
    String cpf;
    String nomeCompleto;
    TipoPagamento tipoPagamento;
    BigDecimal valorProduto;
    BigDecimal desconto;
    BigDecimal valorEntrada;
    BigDecimal valorFinal;
    LocalDate dataPedido;

    public RelatorioPedidosDisponivelResponse(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.cpf = pedido.getCliente().getCpf();
        this.nomeCompleto = pedido.getCliente().getNomeCompleto();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.valorEntrada = pedido.getValorEntrada();
        this.valorFinal = pedido.getValorFinal();
        this.dataPedido = pedido.getDataPedido();
    }

    public static List<RelatorioPedidosDisponivelResponse> convert(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(RelatorioPedidosDisponivelResponse::new)
                .collect(Collectors.toList());
    }
}