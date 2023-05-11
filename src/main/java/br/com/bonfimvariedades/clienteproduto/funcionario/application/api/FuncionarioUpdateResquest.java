package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class FuncionarioUpdateResquest {
    String nomeCompleto;
    LocalDate validadeCnh;
    String cargoFuncionario;
    String cnh;
    LocalDate dataReadmissao;
}