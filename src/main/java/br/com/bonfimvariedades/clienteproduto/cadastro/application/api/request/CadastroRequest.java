package br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class CadastroRequest extends SolicitacaoRequest{
    UUID idCliente;
}