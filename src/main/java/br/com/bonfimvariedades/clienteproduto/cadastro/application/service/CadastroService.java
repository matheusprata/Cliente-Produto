package br.com.bonfimvariedades.clienteproduto.cadastro.application.service;


import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroIdResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroListResponse;

import java.util.List;
import java.util.UUID;

public interface CadastroService {
    CadastroIdResponse saveCadastro(CadastroRequest cadastroRequest);
    CadastroIdResponse saveCadastroByOrcamento(String cpf);
    List<CadastroListResponse> getAllCadastros();
    CadastroDetalhadoResponse getOneCadastro(UUID idCadastro);
    void deleteCadastro(UUID idCadastro);
    void updateCadastro(UUID idCadastro, CadastroAlteracaoRequest cadastroAlteracaoRequest);
    void finalizaCadastro(UUID idCadastro);
    void ativaCadastro(UUID idCadastro);
    void cancelaCadastro(UUID idCadastro);
}