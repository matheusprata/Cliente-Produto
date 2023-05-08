package br.com.bonfimvariedades.clienteproduto.estoque.domain;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Status;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @ManyToOne
    @JsonIgnore
    private Produto produto;

    @NotNull
    private String depositoProduto;
    @NotNull
    private BigDecimal quantidadeProduto;
    @NotNull
    private LocalDate dataEntrada;

    public Estoque(EstoqueRequest request){
        this.produto = getProduto();
        this.depositoProduto = request.getDepositoProduto();
        this.quantidadeProduto = request.getQuantidadeProduto();
        this.dataEntrada = request.getDataEntrada();
    }
}