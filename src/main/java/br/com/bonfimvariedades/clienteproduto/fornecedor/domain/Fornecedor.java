package br.com.bonfimvariedades.clienteproduto.fornecedor.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorRequest;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Categoria;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFornecedor;
    private String nomeEmpresa;
    private String nomeCompleto;
    @NotBlank(message = "Campo Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    private String cpf;
    @NotNull(message = "Campo Obrigatório!")
    private String email;
    @NotNull(message = "Campo Obrigatório!")
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @Enumerated(EnumType.STRING)
    private StatusFornecedor statusFornecedor = StatusFornecedor.ATIVO;

    public Fornecedor(FornecedorRequest request) {
        this.nomeEmpresa = request.getNomeEmpresa().toUpperCase();
        this.nomeCompleto = request.getNomeCompleto().toUpperCase();
        this.cpf = request.getCpf();
        this.email = request.getEmail();
        this.telefone = request.getTelefone();
        this.categoria = request.getCategoria();
    }

    public void altera(FornecedorUpdateResquest updateRequest) {
        this.nomeCompleto = updateRequest.getNomeCompleto().toUpperCase();
        this.nomeEmpresa = updateRequest.getNomeEmpresa().toUpperCase();
        this.email = updateRequest.getEmail();
        this.telefone = updateRequest.getTelefone();
        this.categoria = updateRequest.getCategoria();
    }

    public void inativa() {
        this.statusFornecedor = StatusFornecedor.INATIVO;
    }
}