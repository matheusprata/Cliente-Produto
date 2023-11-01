package br.com.bonfimvariedades.clienteproduto.compra.infra;

import br.com.bonfimvariedades.clienteproduto.compra.domain.Compra;
import br.com.bonfimvariedades.clienteproduto.compra.application.repository.CompraRepository;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Log4j2
public class CompraInfraRepository implements CompraRepository {
    private final CompraSpringDataJPARepository compraSpringDataJPARepository;

    @Override
    public Compra saveCompra(Compra compra) {
        log.info("[inicia] CompraInfraRepository - saveCompra");
        try{
            compraSpringDataJPARepository.save(compra);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Compra já Realizada", e);
        }
        log.info("[finaliza] CompraInfraRepository - saveCompra");
        return compra;
    }
}
