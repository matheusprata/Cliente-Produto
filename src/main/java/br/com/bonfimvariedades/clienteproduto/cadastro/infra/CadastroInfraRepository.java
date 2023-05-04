package br.com.bonfimvariedades.clienteproduto.cadastro.infra;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.repository.CadastroRepository;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class CadastroInfraRepository implements CadastroRepository {
    private final CadastroSpringDataJPARepository cadastroSpringDataJPARepository;

    @Override
    public Cadastro saveCadastro(Cadastro cadastro) {
        log.info("[inicia] CadastroInfraRepository - saveCadastro");
        cadastroSpringDataJPARepository.save(cadastro);
        log.info("[finaliza] CadastroInfraRepository - saveCadastro");
        return cadastro;
    }

    @Override
    public List<Cadastro> getAllCadastros() {
        log.info("[inicia] CadastroInfraRepository - getAllCadastros");
        List<Cadastro> todasCadastros = cadastroSpringDataJPARepository.findAll();
        log.info("[finaliza] CadastroInfraRepository - getAllCadastros");
        return todasCadastros;
    }

    @Override
    public Cadastro getOneCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroInfraRepository - getOneCadastro");
        Optional<Cadastro> cadastroOptional = cadastroSpringDataJPARepository.findById(idCadastro);
        Cadastro cadastro = cadastroOptional
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Cadastro não encontrado!"));
        log.info("[finaliza] CadastroInfraRepository - getOneCadastro");
        return cadastro;
    }

    @Override
    public void deleteCadastro(Cadastro cadastro) {
        log.info("[inicia] CadastroInfraRepository - deleteCadastro");
        cadastroSpringDataJPARepository.delete(cadastro);
        log.info("[finaliza] CadastroInfraRepository - deleteCadastro");
    }
}