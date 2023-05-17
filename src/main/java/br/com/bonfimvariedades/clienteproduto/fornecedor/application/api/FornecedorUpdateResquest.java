package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FornecedorUpdateResquest {
    @NotNull(message = "Campo Obrigatório!")
    String nomeEmpresa;
    @NotNull(message = "Campo Obrigatório!")
    String nomeCompleto;
    @NotNull(message = "Campo Obrigatório!")
    private String email;
    @NotNull(message = "Campo Obrigatório!")
    private String telefone;
    private Categoria categoria;
}