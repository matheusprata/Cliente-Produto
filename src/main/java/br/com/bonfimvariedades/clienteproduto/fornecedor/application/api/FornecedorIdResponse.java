package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import lombok.Builder;
import lombok.Value;
import java.util.UUID;

@Value
@Builder
public class FornecedorIdResponse {
    UUID idFornecedor;
}