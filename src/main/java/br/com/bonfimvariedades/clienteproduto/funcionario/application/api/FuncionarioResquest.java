package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class FuncionarioResquest {
    @NotNull(message = "Campo Obrigatório!")
    String nomeCompleto;
    @NotBlank(message = "Campo Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    String cpf;
    @NotBlank(message = "Campo Obrigatório!")
    private String cargoFuncionario;
    @NotNull(message = "Campo Obrigatório!")
    String cnh;
    @NotNull(message = "Campo Obrigatório!")
    LocalDate validadeCnh;
    @NotNull(message = "Campo Obrigatório!")
    private LocalDate dataAdmissao;
}