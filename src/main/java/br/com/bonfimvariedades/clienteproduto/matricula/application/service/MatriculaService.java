package br.com.bonfimvariedades.clienteproduto.matricula.application.service;


import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaIdResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaListResponse;

import java.util.List;
import java.util.UUID;

public interface MatriculaService {
    MatriculaIdResponse saveMatricula(MatriculaRequest matriculaRequest);
    MatriculaIdResponse saveMatriculaByOrcamento(String cpf);
    List<MatriculaListResponse> getAllMatriculas();
    MatriculaDetalhadoResponse getOneMatricula(UUID idMatricula);
    void deleteMatricula(UUID idMatricula);
    void updateMatricula(UUID idMatricula, MatriculaAlteracaoRequest matriculaAlteracaoRequest);
    void finalizaMatricula(UUID idMatricula);
    void ativaMatricula(UUID idMatricula);
    void cancelaMatricula(UUID idMatricula);
}