package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class EstoqueListResponse {
    UUID idEstoque;
    UUID idProduto;
    String nomeProduto;
    String categoria;
    String depositoProduto;
    BigDecimal quantidadeProduto;
    LocalDate dataEntrada;

    public static List<EstoqueListResponse> converte(List<Estoque>estoques){
        return estoques.stream()
                .map(EstoqueListResponse::new)
                .collect(Collectors.toList());
    }
    public EstoqueListResponse(Estoque estoque){
        this.idEstoque = estoque.getIdEstoque();
        this.idProduto = estoque.getProduto().getIdProduto();
        this.nomeProduto = estoque.getProduto().getNomeProduto();
        this.categoria = estoque.getProduto().getCategoria().toString();
        this.depositoProduto = estoque.getDepositoProduto();
        this.quantidadeProduto = estoque.getQuantidadeProduto();
        this.dataEntrada = estoque.getDataEntrada();
    }
}
