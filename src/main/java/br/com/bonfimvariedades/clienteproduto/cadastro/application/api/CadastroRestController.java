package br.com.bonfimvariedades.clienteproduto.cadastro.application.api;

import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroIdResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroListResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.service.CadastroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CadastroRestController implements CadastroAPI{
    private final CadastroService cadastroService;

    @Override
    public CadastroIdResponse saveCadastro(CadastroRequest cadastroRequest) {
        log.info("[inicia] CadastroRestController - saveCadastro");
        CadastroIdResponse cadastroCriado = cadastroService.saveCadastro(cadastroRequest);
        log.info("[finaliza] CadastroRestController - saveCadastro");
        return cadastroCriado;
    }

    @Override
    public CadastroIdResponse saveCadastroByOrcamento(String cpf) {
        log.info("[inicia] CadastroRestController -saveCadastroByOrcamento");
        CadastroIdResponse cadastroCriado = cadastroService.saveCadastroByOrcamento(cpf);
        log.info("[finaliza] CadastroRestController - saveCadastroByOrcamento");
        return cadastroCriado;
    }

    @Override
    public List<CadastroListResponse> getAllCadastros() {
        log.info("[inicia] CadastroRestController - getAllCadastros");
        List<CadastroListResponse> cadastros = cadastroService.getAllCadastros();
        log.info("[finaliza] CadastroRestController - getAllCadastros");
        return cadastros;
    }

    @Override
    public CadastroDetalhadoResponse getOneCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroRestController - getOneCadastro");
        log.info("idCadastro {}", idCadastro);
        CadastroDetalhadoResponse cadastroDetalhadoResponse = cadastroService.getOneCadastro(idCadastro);
        log.info("[finaliza] CadastroRestController - getOneCadastro");
        return cadastroDetalhadoResponse;
    }

    @Override
    public void deleteCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroRestController - deleteCadastro");
        cadastroService.deleteCadastro(idCadastro);
        log.info("[finaliza] CadastroRestController - deleteCadastro");
    }

    @Override
    public void updateCadastro(UUID idCadastro, CadastroAlteracaoRequest cadastroAlteracaoRequest) {
        log.info("[inicia] CadastroRestController - updateCadastro");
        cadastroService.updateCadastro(idCadastro, cadastroAlteracaoRequest);
        log.info("[finaliza] CadastroRestController - updateCadastro");
    }

    @Override
    public void finalizaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroRestController - finalizaCadastro");
        cadastroService.finalizaCadastro(idCadastro);
        log.info("[finaliza] CadastroRestController - finalizaCadastro");
    }

    @Override
    public void ativaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroRestController - ativaCadastro");
        cadastroService.ativaCadastro(idCadastro);
        log.info("[finaliza] CadastroRestController - ativaCadastro");
    }

    @Override
    public void cancelaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroRestController - cancelaCadastro");
        cadastroService.cancelaCadastro(idCadastro);
        log.info("[finaliza] CadastroRestController - cancelaCadastro");
    }
}