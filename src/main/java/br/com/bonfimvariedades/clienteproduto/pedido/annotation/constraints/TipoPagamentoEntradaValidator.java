package br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints;

import br.com.bonfimvariedades.clienteproduto.pedido.annotation.TipoPagamentoEntradaConstraint;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class TipoPagamentoEntradaValidator implements ConstraintValidator<TipoPagamentoEntradaConstraint, String> {
    @Override
    public boolean isValid(String tipoPagamentoEntrada, ConstraintValidatorContext context) {
        if (Objects.equals(tipoPagamentoEntrada, "")) {
            return true;
        }
        return tipoPagamentoEntrada.equals(TipoPagamento.DINHEIRO.toString()) ||
                tipoPagamentoEntrada.equals(TipoPagamento.CARTAO_DEBITO.toString()) ||
                tipoPagamentoEntrada.equals(TipoPagamento.PIX.toString());
    }
}
