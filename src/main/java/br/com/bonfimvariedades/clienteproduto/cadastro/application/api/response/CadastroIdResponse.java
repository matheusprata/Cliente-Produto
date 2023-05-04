package br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class CadastroIdResponse {
    UUID idCadastro;
}
