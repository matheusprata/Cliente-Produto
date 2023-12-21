package br.com.bonfimvariedades.clienteproduto.handler;

public class ProdutoNaoDisponivelException extends RuntimeException  {
    public ProdutoNaoDisponivelException(String message) {
        super(message);
    }
}
