package br.com.bonfimvariedades.clienteproduto.fornecedor.application.repository;

import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;

import java.util.List;
import java.util.UUID;

public interface FornecedorRepository {
    Fornecedor saveFornecedor(Fornecedor fornecedor);
    Fornecedor getFornecedor(UUID idFornecedor);
    List<Fornecedor> getAllFornecedors();
}
