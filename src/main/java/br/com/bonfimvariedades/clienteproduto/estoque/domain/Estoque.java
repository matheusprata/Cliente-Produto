package br.com.bonfimvariedades.clienteproduto.estoque.domain;

import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idEstoque;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
  
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estoque")
    @JsonIgnore
    private List<Compra> compras;


    @NotNull
    private String depositoProduto;
    @NotNull
    private BigDecimal quantidadeProduto;
    @NotNull
    private LocalDate dataEntrada;

    public Estoque(Produto produto, EstoqueRequest request){
        this.produto = produto;
        this.depositoProduto = request.getDepositoProduto();
        this.quantidadeProduto = request.getQuantidadeProduto();
        this.dataEntrada = request.getDataEntrada();
    }

    public void altera(EstoqueAlteracaoRequest estoqueAlteracaoRequest) {
        this.produto = produto;
        this.depositoProduto = estoqueAlteracaoRequest.getDepositoProduto();
        this.quantidadeProduto = estoqueAlteracaoRequest.getQuantidadeProduto();
    }

    public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}