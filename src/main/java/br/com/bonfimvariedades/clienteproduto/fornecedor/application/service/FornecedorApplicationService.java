package br.com.bonfimvariedades.clienteproduto.fornecedor.application.service;

import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorIdResponse;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorResponse;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorRequest;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.api.FornecedorUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.fornecedor.application.repository.FornecedorRepository;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FornecedorApplicationService implements FornecedorService {
    private final FornecedorRepository fornecedorRepository;

    @Override
    public FornecedorIdResponse saveFornecedor(FornecedorRequest resquest) {
        log.info("[inicia] FornecedorApplicationService - post");
        Fornecedor fornecedor = fornecedorRepository.saveFornecedor(new Fornecedor(resquest));
        log.info("[finaliza] FornecedorApplicationService - post");
        return FornecedorIdResponse.builder().idFornecedor(fornecedor.getIdFornecedor()).build();
    }

    @Override
    public Fornecedor getFornecedor(UUID idFornecedor) {
        log.info("[inicia] FornecedorApplicationService - getFornecedor");
        Fornecedor fornecedor = fornecedorRepository.getFornecedor(idFornecedor);
        log.info("[finaliza] FornecedorApplicationService - getFornecedor");
        return fornecedor;
    }

    @Override
    public void update(UUID idFornecedor, FornecedorUpdateResquest updateRequest) {
        log.info("[inicia] FornecedorApplicationService - update");
        Fornecedor fornecedor = fornecedorRepository.getFornecedor(idFornecedor);
        fornecedor.altera(updateRequest);
        fornecedorRepository.saveFornecedor(fornecedor);
        log.info("[finaliza] FornecedorApplicationService - update");
    }

    @Override
    public List<FornecedorResponse> getAllFornecedors() {
        log.info("[inicia] FornecedorApplicationService - getAllFornecedors");
        List<Fornecedor> fornecedors = fornecedorRepository.getAllFornecedors();
        log.info("[finaliza] FornecedorApplicationService - getAllFornecedors");
        return FornecedorResponse.converte(fornecedors);
    }

    @Override
    public void inativaFornecedor(UUID idFornecedor) {
        log.info("[inicia] FornecedorApplicationService - getAllFornecedors");
        Fornecedor fornecedor = fornecedorRepository.getFornecedor(idFornecedor);
        fornecedor.inativa();
        fornecedorRepository.saveFornecedor(fornecedor);
        log.info("[finaliza] FornecedorApplicationService - getAllFornecedors");
    }
}