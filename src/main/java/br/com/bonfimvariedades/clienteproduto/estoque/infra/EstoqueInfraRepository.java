package br.com.bonfimvariedades.clienteproduto.estoque.infra;

import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class EstoqueInfraRepository implements EstoqueRepository {
    private final EstoqueSpringDataInfraRepository estoqueSpringDataInfraRepository;

    @Override
    public Estoque salvaEstoque(Estoque estoque) {
        log.info("[inicia] produtoInfraRepository - salvaProduto");
        try {
            estoqueSpringDataInfraRepository.save(estoque);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Produto já cadastrado", e);
        }
        log.info("[finaliza] produtoInfraRepository - salvaProduto");
        return estoque;
    }
}
