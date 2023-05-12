package br.com.bonfimvariedades.clienteproduto.funcionario.application.service;

import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioIdResponse;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResponse;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioUpdateResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Log4j2
public class FuncionarioApplicationService implements FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public FuncionarioIdResponse saveFuncionario(FuncionarioResquest resquest) {
        log.info("[inicia] FuncionarioApplicationService - saveFuncionario");
        Funcionario funcionario = funcionarioRepository.saveFuncionario(new Funcionario(resquest));
        log.info("[finaliza] FuncionarioApplicationService - saveFuncionario");
        return FuncionarioIdResponse.builder().idFuncionario(funcionario.getIdFuncionario()).build();
    }

    @Override
    public FuncionarioResponse getFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioApplicationService - getFuncionario");
        Funcionario funcionario = funcionarioRepository.getFuncionario(idFuncionario);
        log.info("[finaliza] FuncionarioApplicationService - getFuncionario");
        return new FuncionarioResponse(funcionario);
    }

    @Override
    public void update(UUID idFuncionario, FuncionarioUpdateResquest updateRequest) {
        log.info("[inicia] FuncionarioApplicationService - update");
        Funcionario funcionario = funcionarioRepository.getFuncionario(idFuncionario);
        funcionario.altera(updateRequest);
        funcionarioRepository.saveFuncionario(funcionario);
        log.info("[finaliza] FuncionarioApplicationService - update");
    }

    @Override
    public List<FuncionarioResponse> getAllFuncionarios() {
        log.info("[inicia] FuncionarioApplicationService - getAllFuncionarios");
        List<Funcionario> funcionarios = funcionarioRepository.getAllFuncionarios();
        log.info("[finaliza] FuncionarioApplicationService - getAllFuncionarios");
        return FuncionarioResponse.converte(funcionarios);
    }

    @Override
    public void demitidoFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioApplicationService - DemitidoFuncionario");
        Funcionario funcionario = funcionarioRepository.getFuncionario(idFuncionario);
        funcionario.demitido();
        funcionarioRepository.saveFuncionario(funcionario);
        log.info("[finaliza] FuncionarioApplicationService - DemitidoFuncionario");
    }

    @Override
    public void ativaFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioApplicationService - ativaFuncionario");
        Funcionario funcionario = funcionarioRepository.getFuncionario(idFuncionario);
        funcionario.ativa();
        funcionarioRepository.saveFuncionario(funcionario);
        log.info("[finaliza] FuncionarioApplicationService - ativaFuncionario");
    }
}