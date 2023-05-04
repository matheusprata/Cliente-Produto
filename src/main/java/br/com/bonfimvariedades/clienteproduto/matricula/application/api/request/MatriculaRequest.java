package br.com.bonfimvariedades.clienteproduto.matricula.application.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class MatriculaRequest extends SolicitacaoRequest{
    UUID idCliente;
}