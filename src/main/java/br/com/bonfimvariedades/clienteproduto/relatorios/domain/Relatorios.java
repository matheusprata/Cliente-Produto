package br.com.bonfimvariedades.clienteproduto.relatorios.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
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
    List<Estoque> estoques;
    List<Pedido> pedidos;
    List<Funcionario> funcionarios;

    public Relatorios(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}