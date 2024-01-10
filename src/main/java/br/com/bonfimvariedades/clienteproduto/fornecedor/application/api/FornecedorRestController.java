package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import br.com.bonfimvariedades.clienteproduto.fornecedor.application.service.FornecedorService;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FornecedorRestController implements FornecedorApi {
    private final FornecedorService fornecedorService;

    @Override
    public FornecedorIdResponse saveFornecedor(FornecedorRequest resquest) {
        log.info("[inicia] FornecedorRestController -  saveFornecedor");
        FornecedorIdResponse idResponse = fornecedorService.saveFornecedor(resquest);
        log.info("[finaliza] FornecedorRestController -  saveFornecedor");
        return idResponse;
    }

    @Override
    public FornecedorResponse getFornecedor(UUID idFornecedor) {
        log.info("[inicia] FornecedorRestController -  getFornecedor");
        Fornecedor response = fornecedorService.getFornecedor(idFornecedor);
        log.info("[finaliza] FornecedorRestController -  getFornecedor");
        return new FornecedorResponse(response);
    }

    @Override
    public void updateFornecedor(UUID idFornecedor, FornecedorUpdateResquest updateRequest) {
        log.info("[inicia] FornecedorRestController -  updateFornecedor");
        fornecedorService.update(idFornecedor, updateRequest);
        log.info("[finaliza] FornecedorRestController -  updateFornecedor");
    }

    @Override
    public List<FornecedorResponse> getAllFornecedors() {
        log.info("[inicia] FornecedorRestController -  getAllFornecedors");
        List<FornecedorResponse> fornecedores = fornecedorService.getAllFornecedors();
        log.info("[finaliza] FornecedorRestController -  getAllFornecedors");
        return  fornecedores;
    }

    @Override
    public void inativaFornecedor(UUID idFornecedor) {
        log.info("[inicia] FornecedorRestController -  inativaFornecedor");
        fornecedorService.inativaFornecedor(idFornecedor);
        log.info("[finaliza] FornecedorRestController -  inativaFornecedor");
    }
}