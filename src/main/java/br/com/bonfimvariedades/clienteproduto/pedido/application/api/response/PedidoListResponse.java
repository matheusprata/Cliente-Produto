package br.com.bonfimvariedades.clienteproduto.pedido.application.api.response;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class PedidoListResponse {
    UUID idPedido;
    UUID idFuncionario;
    String nomeFuncionario;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int desconto;
    int quantidadeParcelas;
    BigDecimal valorFinal;
    LocalDate dataPedido;
    String nomeCompleto;
    String observacao;
    String status;

    public static List<PedidoListResponse> converte(List<Pedido> pedidos){
        return pedidos.stream()
                .map(PedidoListResponse::new)
                .collect(Collectors.toList());
    }

    public PedidoListResponse(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.idFuncionario = pedido.getFuncionario().getIdFuncionario();
        this.nomeFuncionario = pedido.getFuncionario().getNomeCompleto();
        this.cpf = pedido.getCliente().getCpf();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.valorEntrada = pedido.getValorEntrada();
        this.desconto = pedido.getDesconto();
        this.quantidadeParcelas = pedido.getQuantidadeParcelas();
        this.valorFinal = pedido.getValorFinal();
        this.dataPedido = pedido.getDataPedido();
        this.nomeCompleto = pedido.getCliente().getNomeCompleto();
        this.observacao = pedido.getObservacao();
        this.status = pedido.getStatus().toString();
    }
}