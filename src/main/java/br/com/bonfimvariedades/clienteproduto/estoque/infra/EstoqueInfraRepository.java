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
    private final EstoqueSpringDataInfraRepository estoqueSpringDataInfraRepository;

    @Override
    public Estoque salvaEstoque(Estoque estoque) {
        log.info("[inicia] EstoqueInfraRepository - salvaEstoque");
        try {
            estoqueSpringDataInfraRepository.save(estoque);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Produto já cadastrado", e);
        }
        log.info("[finaliza] EstoqueInfraRepository - salvaEstoque");
        return estoque;
    }

    @Override
    public Estoque getOneEstoque(UUID idEstoque) {
        log.info("[inicia] EstoqueInfraRepository - getOneEstoque");
        Optional<Estoque> optionalestoque = estoqueSpringDataInfraRepository.findById(idEstoque);
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
        List<Estoque> estoques = estoqueSpringDataInfraRepository.findEstoqueByProduto(produto);
        log.info("[finaliza] EstoqueInfraRepository - getEstoqueByIdProduto");
        return estoques;
    }
}
