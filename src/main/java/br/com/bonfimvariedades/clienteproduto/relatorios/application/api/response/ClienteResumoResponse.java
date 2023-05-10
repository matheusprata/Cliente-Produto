package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import lombok.Data;

@Data
public class ClienteResumoResponse {
    String cpf;
    String nomeCompleto;

    public ClienteResumoResponse(Cliente cliente) {
        this.cpf = cliente.getCpf();
        this.nomeCompleto = cliente.getNomeCompleto();
    }
}