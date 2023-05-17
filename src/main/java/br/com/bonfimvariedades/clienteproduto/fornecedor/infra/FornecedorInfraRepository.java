package br.com.bonfimvariedades.clienteproduto.fornecedor.infra;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.repository.FornecedorRepository;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
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
public class FornecedorInfraRepository implements FornecedorRepository {
    private final FornecedorSpringDataJPARepository fornecedorSpringDataJPARepository;

    @Override
    public Fornecedor saveFornecedor(Fornecedor fornecedor) {
        log.info("[inicia] FornecedorInfraRepository - save ");
        try{
            fornecedorSpringDataJPARepository.save(fornecedor);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Fornecedor já cadastrado", e);
        }
        log.info("[finaliza] FornecedorInfraRepository - save ");
        return fornecedor;
    }

    @Override
    public Fornecedor getFornecedor(UUID idFornecedor) {
        log.info("[inicia] FornecedorInfraRepository - getFornecedor ");
        Optional<Fornecedor> optionalFornecedor = fornecedorSpringDataJPARepository.findById(idFornecedor);
        Fornecedor fornecedor = optionalFornecedor
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Fornecedor não cadastrado!"));
        log.info("[finaliza] FornecedorInfraRepository - getFornecedor ");
        return fornecedor;
    }

    @Override
    public List<Fornecedor> getAllFornecedors() {
        log.info("[inicia] FornecedorInfraRepository - getAllFornecedors ");
        List<Fornecedor> fornecedors = fornecedorSpringDataJPARepository.findAll();
        log.info("[finaliza] FornecedorInfraRepository - getAllFornecedors ");
        return fornecedors;
    }
}