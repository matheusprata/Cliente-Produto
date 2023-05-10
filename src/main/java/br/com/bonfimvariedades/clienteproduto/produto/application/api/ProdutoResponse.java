package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Value
public class ProdutoResponse {
    UUID idProduto;
    String nomeProduto;
    String categoria;
    BigDecimal valorProduto;

    public ProdutoResponse(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getNomeProduto();
        this.categoria = produto.getCategoria().toString();
        this.valorProduto = produto.getValorProduto();
    }

    public static List<ProdutoResponse> converte(List<Produto> produtos) {
        return produtos
                .stream()
                .map(ProdutoResponse::new)
                .toList();
    }
}