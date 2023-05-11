package br.com.bonfimvariedades.clienteproduto.funcionario.infra;

import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Log4j2
public class FuncionarioInfraRepository implements FuncionarioRepository {
    private final FuncionarioSpringDataJPARepository funcionarioSpringDataJPARepository;

    @Override
    public Funcionario saveFuncionario(Funcionario funcionario) {
        log.info("[inicia] FuncionarioInfraRepository - save ");
        try{
            funcionarioSpringDataJPARepository.save(funcionario);
        }catch (DataIntegrityViolationException e){
            throw APIException.build(HttpStatus.BAD_REQUEST, "Funcionario já cadastrado", e);
        }
        log.info("[finaliza] FuncionarioInfraRepository - save ");
        return funcionario;
    }

    @Override
    public Funcionario getFuncionario(UUID idFuncionario) {
        log.info("[inicia] FuncionarioInfraRepository - getFuncionario ");
        Optional<Funcionario> optionalFuncionario = funcionarioSpringDataJPARepository.findById(idFuncionario);
        Funcionario funcionario = optionalFuncionario
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Funcionario não cadastrado!"));
        log.info("[finaliza] FuncionarioInfraRepository - getFuncionario ");
        return funcionario;
    }

    @Override
    public List<Funcionario> getAllFuncionarios() {
        log.info("[inicia] FuncionarioInfraRepository - getAllFuncionarios ");
        List<Funcionario> funcionarios = funcionarioSpringDataJPARepository.findAll();
        log.info("[finaliza] FuncionarioInfraRepository - getAllFuncionarios ");
        return funcionarios;
    }
}