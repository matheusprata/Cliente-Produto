package br.com.bonfimvariedades.clienteproduto.estoque.infra;

import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
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
public class EstoqueInfraRepository implements EstoqueRepository {
    private final EstoqueSpringDataJPARepository estoqueSpringDataJPARepository;

    @Override
    public Estoque salvaEstoque(Estoque estoque) {
        log.info("[inicia] EstoqueInfraRepository - salvaEstoque");
        try {
            estoqueSpringDataJPARepository.save(estoque);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Produto já cadastrado", e);
        }
        log.info("[finaliza] EstoqueInfraRepository - salvaEstoque");
        return estoque;
    }

    @Override
    public Estoque getOneEstoque(UUID idEstoque) {
        log.info("[inicia] EstoqueInfraRepository - getOneEstoque");
        Optional<Estoque> optionalestoque = estoqueSpringDataJPARepository.findById(idEstoque);
        Estoque estoque = optionalestoque
                .orElseThrow(
                        () -> APIException.build(HttpStatus.BAD_REQUEST, "Produto não encontrado no estoque")
                );
        log.info("[finaliza] EstoqueInfraRepository - getOneEstoque");
        return estoque;
    }

    @Override
    public List<Estoque> getEstoqueByIdProduto(Produto produto) {
        log.info("[inicia] EstoqueInfraRepository - getEstoqueByIdProduto");
        List<Estoque> estoques = estoqueSpringDataJPARepository.findEstoqueByProduto(produto);
        log.info("[finaliza] EstoqueInfraRepository - getEstoqueByIdProduto");
        return estoques;
    }

    @Override
    public Estoque buscaEstoqueAtravesId(UUID idEstoque) {
        log.info("[inicia] EstoqueInfraRepository - buscaEstoqueAtravesId");
        Estoque estoque = estoqueSpringDataJPARepository.findById(idEstoque)
                .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
        log.info("[finaliza] EstoqueInfraRepository - buscaEstoqueAtravesId");
        return estoque;
    }
}