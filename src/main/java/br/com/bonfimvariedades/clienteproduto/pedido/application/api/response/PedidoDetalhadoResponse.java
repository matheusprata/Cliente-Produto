package br.com.bonfimvariedades.clienteproduto.pedido.application.api.response;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class PedidoDetalhadoResponse {
    UUID idPedido;
    UUID idFuncionario;
    String nomeFuncionario;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int quantidadeParcelas;
    BigDecimal valorProduto;
    BigDecimal desconto;
    BigDecimal valorFinal;
    LocalDate dataPedido;
    String nomeCompleto;
    String observacao;
    String status;

    public PedidoDetalhadoResponse(Pedido pedido) {
        this.idPedido = pedido.getIdPedido();
        this.idFuncionario = pedido.getFuncionario().getIdFuncionario();
        this.nomeFuncionario = pedido.getFuncionario().getNomeCompleto();
        this.cpf = pedido.getCliente().getCpf();
        this.nomeCompleto = pedido.getCliente().getNomeCompleto();
        this.dataPedido = pedido.getDataPedido();
        this.valorProduto = pedido.getProduto().getValorProduto();
        this.valorEntrada = pedido.getValorEntrada();
        this.tipoPagamento = pedido.getTipoPagamento();
        this.quantidadeParcelas = pedido.getQuantidadeParcelas();
        this.desconto =  calculaDesconto(pedido.getDesconto(), pedido.getProduto().getValorProduto()) ;
        this.valorFinal = pedido.getValorFinal();
        this.observacao = pedido.getObservacao();
        this.status = pedido.getStatus().toString();
    }

    private BigDecimal calculaDesconto(int desconto, BigDecimal valorProduto) {
        return valorProduto.multiply(new BigDecimal(desconto)).divide(BigDecimal.valueOf(100),
                2, RoundingMode.HALF_UP);
    }
}
