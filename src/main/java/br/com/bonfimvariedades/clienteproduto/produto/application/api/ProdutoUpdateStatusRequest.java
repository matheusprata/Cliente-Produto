package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class ProdutoUpdateStatusRequest {

    @NotNull
    Status status;

    public ProdutoUpdateStatusRequest() {
        this.status = null;
    }

    public ProdutoUpdateStatusRequest(Status status) {
        this.status = status;
    }
}
