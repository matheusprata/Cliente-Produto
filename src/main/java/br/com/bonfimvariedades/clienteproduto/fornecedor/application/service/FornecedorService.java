package br.com.bonfimvariedades.clienteproduto.fornecedor.application.service;

import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorIdResponse;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorResponse;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorRequest;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;

import java.util.List;
import java.util.UUID;

public interface FornecedorService {
    FornecedorIdResponse saveFornecedor(FornecedorRequest resquest);
    Fornecedor getFornecedor(UUID idFornecedor);
    void update(UUID idFornecedor, FornecedorUpdateResquest updateRequest);
    List<FornecedorResponse> getAllFornecedors();
    void inativaFornecedor(UUID idFornecedor);
}