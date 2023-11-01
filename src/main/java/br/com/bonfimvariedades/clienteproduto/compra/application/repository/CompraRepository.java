package br.com.bonfimvariedades.clienteproduto.compra.application.repository;

import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;

public interface CompraRepository {
    Compra saveCompra(Compra compra);
}
