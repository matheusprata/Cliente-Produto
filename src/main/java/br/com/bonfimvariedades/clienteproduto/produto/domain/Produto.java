package br.com.bonfimvariedades.clienteproduto.produto.domain;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Status;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateStatusRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Produto{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduto;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produto")
    @JsonIgnore
    private List<Estoque> Estoques;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "produto")
    @JsonIgnore
    private List<Orcamento> orcamentos;

    @OneToOne
    @JsonIgnore
    private Pedido pedido;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "produto_fornecedor",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "fornecedor_id")
    )
    @JsonIgnore
    private List<Fornecedor> fornecedores = new ArrayList<>();

    private String nomeProduto;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private BigDecimal valorProduto;
    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIVEL;

    public Produto(ProdutoRequest request, List<Fornecedor> fornecedores) {
        this.nomeProduto = request.getNomeProduto();
        this.categoria = request.getCategoria();
        this.valorProduto = request.getValorProduto();
        this.fornecedores.addAll(fornecedores);
    }

    public void altera(ProdutoUpdateRequest alteracaoRequest) {
        if(alteracaoRequest.getNomeProduto() != null) {
            this.nomeProduto = alteracaoRequest.getNomeProduto();
        }
        if(alteracaoRequest.getCategoria() != null) {
            this.categoria = alteracaoRequest.getCategoria();
        }
        if(alteracaoRequest.getValorProduto() != null) {
           this.valorProduto = alteracaoRequest.getValorProduto();
        }
    }

    public void produtoEsgotado() {
        this.status = Status.ESGOTADO;
    }

    public void alteraStatus(ProdutoUpdateStatusRequest produtoUpdateStatusRequest) {
        this.status = produtoUpdateStatusRequest.getStatus();
    }
}