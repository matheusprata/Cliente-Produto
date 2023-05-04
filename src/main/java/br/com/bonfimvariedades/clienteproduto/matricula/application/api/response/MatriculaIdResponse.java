package br.com.bonfimvariedades.clienteproduto.matricula.application.api.response;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
@Builder
public class MatriculaIdResponse {
    UUID idMatricula;
}
