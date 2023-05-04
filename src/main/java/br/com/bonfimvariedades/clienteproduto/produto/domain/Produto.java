package br.com.bonfimvariedades.clienteproduto.produto.domain;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Status;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;
    private String nomeProduto;
    @Column(unique = true, updatable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorProduto;
    @Enumerated(EnumType.STRING)
    private Status status = Status.ATIVO;

    public Produto(ProdutoRequest request) {
        this.nomeProduto = request.getNomeProduto();
        this.categoria = request.getCategoria();
        this.valorProduto = request.getValorproduto();
    }

    public void altera(ProdutoUpdateRequest alteracaoRequest) {
        this.valorProduto = alteracaoRequest.getValorproduto();
    }

    public void alteraStatus() {
        this.status = Status.INATIVO;
    }
}