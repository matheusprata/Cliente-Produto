package br.com.bonfimvariedades.clienteproduto.handler;

public class QuantidadeInsuficienteException extends RuntimeException  {
    public QuantidadeInsuficienteException(String message) {
        super(message);
    }
}
