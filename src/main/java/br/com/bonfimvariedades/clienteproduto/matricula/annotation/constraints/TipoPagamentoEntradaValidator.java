package br.com.bonfimvariedades.clienteproduto.matricula.annotation.constraints;

import br.com.bonfimvariedades.clienteproduto.matricula.annotation.TipoPagamentoEntradaConstraint;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
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
