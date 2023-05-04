package br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.SolicitacaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Valid {
    public static void validaSolicitacao(SolicitacaoRequest request, Produto produto) {
        validarTipoPagamentoETotalParcelas(request.getTipoPagamento(), request.getQuantidadeParcelas());
        validaEntrada(request.getValorEntrada(), produto.getValorProduto(), request.getDesconto());
    }

    public static void validaAlteracaoPedido(Pedido pedido, PedidoAlteracaoRequest request) {
        validarTipoPagamentoETotalParcelas(request.getTipoPagamento(), request.getQuantidadeParcelas());
        validaEntrada(request.getValorEntrada(), pedido.getProduto().getValorProduto(), request.getDesconto());
    }

    public static void validarTipoPagamentoETotalParcelas(TipoPagamento tipoPagamento, int quantidadeParcelas) {
        if (tipoPagamento == TipoPagamento.DINHEIRO || tipoPagamento == TipoPagamento.CARTAO_DEBITO ||
                tipoPagamento == TipoPagamento.PIX || tipoPagamento == TipoPagamento.BOLETO) {
            if (quantidadeParcelas != 1) {
                throw APIException
                        .build(HttpStatus.BAD_REQUEST,"Quantidade de parcelas inválida para o tipo de pagamento escolhido.");
            }
        } else if (tipoPagamento == TipoPagamento.CARTAO_CREDITO) {
            if (quantidadeParcelas < 1) {
                throw APIException
                        .build(HttpStatus.BAD_REQUEST,"Quantidade de parcelas inválida para o tipo de pagamento escolhido.");
            }
        }
    }

    public  static void validaEntrada(BigDecimal valorEntrada, BigDecimal valorProduto, int desconto){
        BigDecimal valorFinal = calcularValorFinal(desconto, valorProduto);
        if(valorEntrada.compareTo(valorProduto) > 0){
            throw APIException
                    .build(HttpStatus.BAD_REQUEST,"Valor entrada R$: "+valorEntrada + " maior que o valor contratado, " +
                            "Valor Serviço R$: " + valorProduto + " - desconto de " + desconto+"% igual a R$: " + valorFinal);
        }
    }

    public static BigDecimal calcularValorFinal(int desconto, BigDecimal valorProduto) {
        final int DESCONTO_MAXIMO = 100;
        final int DESCONTO_MINIMO = 0;

        if (desconto < DESCONTO_MINIMO || desconto > DESCONTO_MAXIMO) {
            throw APIException.build(HttpStatus.BAD_REQUEST,"O desconto deve ser um valor entre 0 e 100");
        }
        if (valorProduto.compareTo(BigDecimal.ZERO) <= 0) {
            throw APIException.build(HttpStatus.BAD_REQUEST,"O valor do serviço deve ser maior que zero");
        }
        BigDecimal valorDescontado = valorProduto.multiply(new BigDecimal(desconto)).divide(BigDecimal.valueOf(100),
                2, RoundingMode.HALF_UP);
        return valorProduto.subtract(valorDescontado);
    }
}