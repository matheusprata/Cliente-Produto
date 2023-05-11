package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class FuncionarioIdResponse {
    UUID idFuncionario;
}
