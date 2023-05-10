package br.com.bonfimvariedades.clienteproduto.estoque.domain;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
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
    @JoinColumn(name = "produto_id")
    private Produto produto;

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
}