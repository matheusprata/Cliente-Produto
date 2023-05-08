package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;
@Value
@Builder
public class EstoqueIdResponse {
    UUID idEstoque;
}
