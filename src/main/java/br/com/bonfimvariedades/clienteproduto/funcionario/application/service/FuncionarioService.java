package br.com.bonfimvariedades.clienteproduto.funcionario.application.service;

import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioIdResponse;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResponse;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioUpdateResquest;

import java.util.List;
import java.util.UUID;

public interface FuncionarioService {
    FuncionarioIdResponse saveFuncionario(FuncionarioResquest resquest);
    FuncionarioResponse getFuncionario(UUID idFuncionario);
    void update(UUID idFuncionario, FuncionarioUpdateResquest updateRequest);
    List<FuncionarioResponse> getAllFuncionarios();
    void demitidoFuncionario(UUID idFuncionario);
}
