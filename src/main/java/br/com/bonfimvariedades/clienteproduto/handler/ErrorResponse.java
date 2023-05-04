package br.com.bonfimvariedades.clienteproduto.handler;

import lombok.Value;

@Value
public class ErrorResponse {
    int codigo;
    String mensagem;
}
