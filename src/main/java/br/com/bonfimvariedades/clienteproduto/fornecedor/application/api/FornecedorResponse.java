package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.StatusFornecedor;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import lombok.Value;
import java.util.List;
import java.util.UUID;

@Value
public class FornecedorResponse {
    UUID idFornecedor;
    String nomeEmpresa;
    String nomeCompleto;
    String cpf;
    String email;
    String telefone;
    Categoria categoria;
    StatusFornecedor statusFornecedor;

    public FornecedorResponse(Fornecedor fornecedor) {
        this.idFornecedor = fornecedor.getIdFornecedor();
        this.nomeEmpresa = fornecedor.getNomeEmpresa();
        this.nomeCompleto = fornecedor.getNomeCompleto();
        this.cpf = fornecedor.getCpf();
        this.email = fornecedor.getEmail();
        this.telefone = fornecedor.getTelefone();
        this.categoria = fornecedor.getCategoria();
        this.statusFornecedor = fornecedor.getStatusFornecedor();
    }

    public static List<FornecedorResponse> converte(List<Fornecedor> fornecedors) {
        return fornecedors
                .stream()
                .map(FornecedorResponse::new)
                .toList();
    }
}