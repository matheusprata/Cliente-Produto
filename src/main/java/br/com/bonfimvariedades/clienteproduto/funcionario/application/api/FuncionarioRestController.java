package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import br.com.bonfimvariedades.clienteproduto.funcionario.application.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Log4j2
public class FuncionarioRestController implements FuncionarioApi {
    private final FuncionarioService funcionarioService;

    @Override
    public FuncionarioIdResponse saveFuncionario(FuncionarioResquest request) {
        log.info("[inicia] FuncionarioRestController -  saveFuncionario");
        FuncionarioIdResponse idResponse = funcionarioService.saveFuncionario(request);
        log.info("[finaliza] FuncionarioRestController -  saveFuncionario");
        return idResponse;
    }
    @Override
    public FuncionarioResponse getFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioRestController -  getFuncionario");
        FuncionarioResponse response = funcionarioService.getFuncionario(idFuncionario);
        log.info("[finaliza] FuncionarioRestController -  getFuncionario");
        return response;
    }
    @Override
    public void updateFuncionario(UUID idFuncionario, FuncionarioUpdateResquest updateRequest) {
        log.info("[inicia] FuncionarioRestController -  updateFuncionario");
        funcionarioService.update(idFuncionario, updateRequest);
        log.info("[finaliza] FuncionarioRestController -  updateFuncionario");
    }
    @Override
    public List<FuncionarioResponse> getAllFuncionarios() {
        log.info("[inicia] FuncionarioRestController -  getAllFuncionarios");
        List<FuncionarioResponse> funcionarioes = funcionarioService.getAllFuncionarios();
        log.info("[finaliza] FuncionarioRestController -  getAllFuncionarios");
        return  funcionarioes;
    }
    @Override
    public void demiteFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioRestController -  inativaFuncionario");
        funcionarioService.demitidoFuncionario(idFuncionario);
        log.info("[finaliza] FuncionarioRestController -  inativaFuncionario");
    }
    @Override
    public void ativaFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioRestController -  ativaFuncionario");
        funcionarioService.ativaFuncionario(idFuncionario);
        log.info("[finaliza] FuncionarioRestController -  ativaFuncionario");
    }
}