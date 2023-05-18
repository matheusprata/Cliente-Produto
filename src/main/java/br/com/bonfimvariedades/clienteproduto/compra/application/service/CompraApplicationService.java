package br.com.bonfimvariedades.clienteproduto.compra.application.service;

import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraIdResponse;
import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraRequest;
import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import br.com.bonfimvariedades.clienteproduto.compra.application.repository.CompraRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.repository.FornecedorRepository;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CompraApplicationService implements CompraService{
    private final CompraRepository compraRepository;
    private final FornecedorRepository fornecedorRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EstoqueRepository estoqueRepository;

    @Override
    public CompraIdResponse saveCompra(CompraRequest request) {
        log.info("[inicia] CompraApplicationService - saveCompra");
        Fornecedor fornecedor = fornecedorRepository.getFornecedor(request.getIdFornecedor());
        Funcionario funcionario = funcionarioRepository.getFuncionario(request.getIdFuncionario());
        Estoque estoque = estoqueRepository.getOneEstoque(request.getIdEstoque());
        Compra compra = compraRepository.saveCompra(new Compra(fornecedor, funcionario, estoque, request));
        log.info("[finaliza] CompraApplicationService - saveCompra");
        return CompraIdResponse.builder().idCompra(compra.getIdCompra()).build();
    }
}
