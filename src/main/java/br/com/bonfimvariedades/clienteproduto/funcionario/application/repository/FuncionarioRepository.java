package br.com.bonfimvariedades.clienteproduto.funcionario.application.repository;

import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import java.util.List;
import java.util.UUID;

public interface FuncionarioRepository {
    Funcionario saveFuncionario(Funcionario funcionario);
    Funcionario getFuncionario(UUID idFuncionario);
    List<Funcionario> getAllFuncionarios();
}