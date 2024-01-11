package br.com.bonfimvariedades.clienteproduto.estoque.domain;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private String depositoProduto;
    private BigDecimal quantidadeProduto;
    private LocalDate dataEntrada;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Estoque(Produto produto, EstoqueRequest request){
        this.produto = produto;
        this.depositoProduto = request.getDepositoProduto();
        this.quantidadeProduto = request.getQuantidadeProduto();
        this.dataEntrada = request.getDataEntrada();
    }

    public void altera(EstoqueAlteracaoRequest estoqueAlteracaoRequest) {
        if(estoqueAlteracaoRequest.getDepositoProduto() != null) {
            this.depositoProduto = estoqueAlteracaoRequest.getDepositoProduto();
        }
        if(estoqueAlteracaoRequest.getQuantidadeProduto() != null) {
            this.quantidadeProduto = estoqueAlteracaoRequest.getQuantidadeProduto();
        }
        if(estoqueAlteracaoRequest.getDataEntrada() != null) {
            this.dataEntrada = estoqueAlteracaoRequest.getDataEntrada();
        }
    }

    public void setQuantidadeProduto(BigDecimal quantidadeProduto) {
        this.quantidadeProduto = quantidadeProduto;
    }
}