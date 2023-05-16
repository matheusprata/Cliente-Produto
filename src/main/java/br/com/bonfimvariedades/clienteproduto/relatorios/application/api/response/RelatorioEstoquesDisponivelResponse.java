package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RelatorioEstoquesDisponivelResponse {
    UUID idEstoque;
    UUID idProduto;
    String nomeProduto;
    String categoria;
    String depositoProduto;
    BigDecimal quantidadeProduto;
    LocalDate dataEntrada;

    public RelatorioEstoquesDisponivelResponse(Estoque estoque){
        this.idEstoque = estoque.getIdEstoque();
        this.idProduto = estoque.getProduto().getIdProduto();
        this.nomeProduto = estoque.getProduto().getNomeProduto();
        this.categoria = estoque.getProduto().getCategoria().toString();
        this.depositoProduto = estoque.getDepositoProduto();
        this.quantidadeProduto = estoque.getQuantidadeProduto();
        this.dataEntrada = estoque.getDataEntrada();
    }

    public static List<RelatorioEstoquesDisponivelResponse> convert(List<Estoque> estoques) {
        return estoques.stream()
                .map(RelatorioEstoquesDisponivelResponse::new)
                .collect(Collectors.toList());
    }
}