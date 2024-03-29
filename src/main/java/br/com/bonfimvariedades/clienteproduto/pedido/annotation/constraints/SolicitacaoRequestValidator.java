package br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints;

import br.com.bonfimvariedades.clienteproduto.pedido.annotation.ValidSolicitacaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.SolicitacaoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;
import java.util.Objects;

public class SolicitacaoRequestValidator implements ConstraintValidator<ValidSolicitacaoRequest, SolicitacaoRequest> {

    @Override
    public boolean isValid(SolicitacaoRequest request, ConstraintValidatorContext context) {
        BigDecimal valorEntrada = request.getValorEntrada();
        String tipoPagamentoEntrada = request.getTipoPagamentoEntrada();

        if ((valorEntrada.compareTo(BigDecimal.ZERO) > 0 && tipoPagamentoEntrada.isEmpty()) ||
        valorEntrada.compareTo(BigDecimal.ZERO) == 0 && !Objects.equals(tipoPagamentoEntrada, "")) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("O tipo de pagamento de entrada deve ser " +
                            "informado quando o valor de entrada é maior do que zero")
                    .addPropertyNode("tipoPagamentoEntrada")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
