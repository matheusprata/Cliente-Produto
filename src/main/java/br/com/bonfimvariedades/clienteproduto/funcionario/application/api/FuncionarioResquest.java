package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
public class FuncionarioResquest {
    @NotBlank(message = "Campo Obrigatório!")
    String nomeCompleto;
    @NotBlank(message = "Campo Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    String cpf;
    @NotNull(message = "Campo Data Nascimento Obrigatório!")
    LocalDate dataNascimento;
    @NotBlank(message = "Campo Obrigatório!")
    String cargoFuncionario;
    @NotNull(message = "Campo salario Obrigatório!")
    BigDecimal salario;
    @NotNull(message = "Campo Obrigatório!")
    @Column(unique = true, length = 11, updatable = false)
    String cnh;
    @NotNull(message = "Campo Obrigatório!")
    LocalDate validadeCnh;
    @NotNull(message = "Campo Obrigatório!")
    LocalDate dataAdmissao;
}