package br.com.bonfimvariedades.clienteproduto.orcamento.application.api;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.SolicitacaoRequest;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.br.CPF;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrcamentoRequest extends SolicitacaoRequest {
    @NotNull(message = "Campo Obrigatório!")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    String cpf;
    @NotNull(message = "Campo Obrigatório!")
    String nomeCompleto;
    @Pattern(regexp = "^\\(\\d{2}\\)\\d{4,5}-\\d{4}$", message = "Telefone inválido")
    String telefone;
}