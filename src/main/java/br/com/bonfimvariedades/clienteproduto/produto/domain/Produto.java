package br.com.bonfimvariedades.clienteproduto.produto.domain;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Status;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Produto{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;
    @NotBlank
    private String nomeProduto;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorProduto;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    public Produto(ProdutoRequest request) {
        this.nomeProduto = request.getNomeProduto();
        this.categoria = request.getCategoria();
        this.valorProduto = request.getValorProduto();
    }

    public void altera(ProdutoUpdateRequest alteracaoRequest) {
        this.valorProduto = alteracaoRequest.getValorProduto();
    }

    public void alteraStatus() {
        this.status = Status.INATIVO;
    }
}