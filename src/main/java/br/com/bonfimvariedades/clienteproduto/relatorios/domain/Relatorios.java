package br.com.bonfimvariedades.clienteproduto.relatorios.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class Relatorios {
    LocalDate data;
    List<Cliente> clientes;
    List<Pedido> pedidos;

    public Relatorios(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}
