package br.com.bonfimvariedades.clienteproduto.produto.infra;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Log4j2
public class ProdutoInfraRepository implements ProdutoRepository {
    private final ProdutoSpringDataInfraRepository produtoSpringDataInfraRepository;

    @Override
    public Produto salvaProduto(Produto produto) {
        log.info("[inicia] produtoInfraRepository - salvaProduto");
        try {
            produtoSpringDataInfraRepository.save(produto);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Produto já cadastrado", e);
        }
        log.info("[finaliza] produtoInfraRepository - salvaProduto");
        return produto;
    }

    @Override
    public Produto getOneProduto(UUID idProduto) {
        log.info("[inicia] produtoInfraRepository - getOneProduto");
        Optional<Produto> optionalproduto = produtoSpringDataInfraRepository.findById(idProduto);
        Produto produto = optionalproduto
                .orElseThrow(
                        () -> APIException.build(HttpStatus.BAD_REQUEST, "Produto não encontrado")
                );
        log.info("[finaliza] produtoInfraRepository - getOneProduto");
        return produto;
    }

    @Override
    public List<Produto> getAllProdutos() {
        log.info("[inicia] produtoInfraRepository - getAllProdutos");
        List<Produto> produtos = produtoSpringDataInfraRepository.findAll();
        log.info("[finaliza] produtoInfraRepository - getAllProdutos");
        return produtos;
    }

    @Override
    public void deleteProduto(UUID idproduto) {
        log.info("[inicia] produtoInfraRepository - deleteProduto");
        produtoSpringDataInfraRepository.deleteById(idproduto);
        log.info("[finaliza] produtoInfraRepository - deleteProduto");
    }
}