package br.com.bonfimvariedades.clienteproduto.matricula.application.api;

import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaIdResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaListResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.service.MatriculaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class MatriculaRestController implements MatriculaAPI{
    private final MatriculaService matriculaService;

    @Override
    public MatriculaIdResponse saveMatricula(MatriculaRequest matriculaRequest) {
        log.info("[inicia] MatriculaRestController - saveMatricula");
        MatriculaIdResponse matriculaCriado = matriculaService.saveMatricula(matriculaRequest);
        log.info("[finaliza] MatriculaRestController - saveMatricula");
        return matriculaCriado;
    }

    @Override
    public MatriculaIdResponse saveMatriculaByOrcamento(String cpf) {
        log.info("[inicia] MatriculaRestController -saveMatriculaByOrcamento");
        MatriculaIdResponse matriculaCriado = matriculaService.saveMatriculaByOrcamento(cpf);
        log.info("[finaliza] MatriculaRestController - saveMatriculaByOrcamento");
        return matriculaCriado;
    }

    @Override
    public List<MatriculaListResponse> getAllMatriculas() {
        log.info("[inicia] MatriculaRestController - getAllMatriculas");
        List<MatriculaListResponse> matriculas = matriculaService.getAllMatriculas();
        log.info("[finaliza] MatriculaRestController - getAllMatriculas");
        return matriculas;
    }

    @Override
    public MatriculaDetalhadoResponse getOneMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaRestController - getOneMatricula");
        log.info("idMatricula {}", idMatricula);
        MatriculaDetalhadoResponse matriculaDetalhadoResponse = matriculaService.getOneMatricula(idMatricula);
        log.info("[finaliza] MatriculaRestController - getOneMatricula");
        return matriculaDetalhadoResponse;
    }

    @Override
    public void deleteMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaRestController - deleteMatricula");
        matriculaService.deleteMatricula(idMatricula);
        log.info("[finaliza] MatriculaRestController - deleteMatricula");
    }

    @Override
    public void updateMatricula(UUID idMatricula, MatriculaAlteracaoRequest matriculaAlteracaoRequest) {
        log.info("[inicia] MatriculaRestController - updateMatricula");
        matriculaService.updateMatricula(idMatricula, matriculaAlteracaoRequest);
        log.info("[finaliza] MatriculaRestController - updateMatricula");
    }

    @Override
    public void finalizaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaRestController - finalizaMatricula");
        matriculaService.finalizaMatricula(idMatricula);
        log.info("[finaliza] MatriculaRestController - finalizaMatricula");
    }

    @Override
    public void ativaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaRestController - ativaMatricula");
        matriculaService.ativaMatricula(idMatricula);
        log.info("[finaliza] MatriculaRestController - ativaMatricula");
    }

    @Override
    public void cancelaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaRestController - cancelaMatricula");
        matriculaService.cancelaMatricula(idMatricula);
        log.info("[finaliza] MatriculaRestController - cancelaMatricula");
    }
}