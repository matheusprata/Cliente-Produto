package br.com.bonfimvariedades.clienteproduto.produto.domain;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Status;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Produto{

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produto")
    @JsonIgnore
    private List<Estoque> Estoques;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produto")
    @JsonIgnore
    private List<Orcamento> orcamentos;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;
    @NotBlank
    private String nomeProduto;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorProduto;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIVEL;

    public Produto(ProdutoRequest request) {
        this.nomeProduto = request.getNomeProduto();
        this.categoria = request.getCategoria();
        this.valorProduto = request.getValorProduto();
    }

    public void altera(ProdutoUpdateRequest alteracaoRequest) {
        this.valorProduto = alteracaoRequest.getValorProduto();
    }

    public void produtoEsgotado() {
        this.status = Status.ESGOTADO;
    }
    public void produtoDisponivel() {
        this.status = Status.DISPONIVEL;
    }
    public void produtoAguardandoChegar() {
        this.status = Status.AGUARDANDO_CHEGAR;
    }
}