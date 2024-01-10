package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.StatusFornecedor;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;
import org.hibernate.validator.constraints.br.CPF;

@Value
public class FornecedorRequest {
    @NotNull(message = "Campo Obrigatório!")
    String nomeEmpresa;
    @NotNull(message = "Campo Obrigatório!")
    String nomeCompleto;
    @NotBlank(message = "Campo Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    @Pattern(regexp = "(^\\d{3}\\x2E\\d{3}\\x2E\\d{3}\\x2D\\d{2}$)")
    String cpf;
    @NotNull(message = "Campo email Obrigatório!")
    String email;
    @NotNull(message = "Campo telefone Obrigatório!")
    String telefone;
    @NotNull(message = "Campo Categoria Obrigatório!")
    Categoria categoria;
}