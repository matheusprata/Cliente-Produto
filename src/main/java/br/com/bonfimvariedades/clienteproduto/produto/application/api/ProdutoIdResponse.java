package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class ProdutoIdResponse {
    UUID idproduto;
}