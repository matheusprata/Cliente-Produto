package br.com.bonfimvariedades.clienteproduto.produto.application.service;

import br.com.bonfimvariedades.clienteproduto.fornecedor.application.repository.FornecedorRepository;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoIdResponse;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.api.ProdutoUpdateRequest;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Collections;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class ProdutoApplicationService implements ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final FornecedorRepository fornecedorRepository;

    public ProdutoIdResponse saveProduto(ProdutoRequest request) {
        log.info("[inicia] produtoApplicationService - saveproduto");
        Fornecedor fornecedor = fornecedorRepository.getFornecedor(request.getIdFornecedor());
        List<Fornecedor> fornecedorList = Collections.singletonList(fornecedor);
        Produto produto = produtoRepository.salvaProduto(new Produto(request, fornecedorList));
        log.info("[finaliza] produtoApplicationService - saveproduto");
        return ProdutoIdResponse
                .builder()
                .idProduto(produto.getIdProduto())
                .build();
    }

    public Produto getOneProduto(UUID idProduto) {
        log.info("[inicia] produtoApplicationService - getOneproduto");
        Produto produto = produtoRepository.getOneProduto(idProduto);
        log.info("[finaliza] produtoApplicationService - getOneproduto");
        return produto;
    }

    @Override
    public List<Produto> getAllProdutos() {
        log.info("[inicia] produtoApplicationService - getAllprodutos");
        List<Produto> produtos = produtoRepository.getAllProdutos();
        log.info("[finaliza] produtoApplicationService - getAllprodutos");
        return produtos;
    }

    public void deleteProduto(UUID idproduto) {
        log.info("[inicia] produtoApplicationService - deleteproduto");
        produtoRepository.deleteProduto(produtoRepository.getOneProduto(idproduto).getIdProduto());
        log.info("[finaliza] produtoApplicationService - deleteproduto");
    }

    public void updateProduto(UUID idProduto, ProdutoUpdateRequest updateRequest) {
        log.info("[inicia] produtoApplicationService - updateproduto");
        Produto produto = produtoRepository.getOneProduto(idProduto);
        produto.altera(updateRequest);
        produtoRepository.salvaProduto(produto);
        log.info("[finaliza] produtoApplicationService - updateproduto");
    }

    public void updateProdutoStatus(UUID idProduto) {
        log.info("[inicia] produtoApplicationService - updateprodutoStatus");
        Produto produto = produtoRepository.getOneProduto(idProduto);
        produto.produtoEsgotado();
        produtoRepository.salvaProduto(produto);
        log.info("[finaliza] produtoApplicationService - updateprodutoStatus");
    }
}