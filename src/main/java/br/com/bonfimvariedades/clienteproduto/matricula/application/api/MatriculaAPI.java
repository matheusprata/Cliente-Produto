package br.com.bonfimvariedades.clienteproduto.matricula.application.api;

import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaIdResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaListResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/matricula")
public interface MatriculaAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    MatriculaIdResponse saveMatricula(@Valid @RequestBody MatriculaRequest matriculaRequest);

    @PostMapping(value = "/orcamento")
    @ResponseStatus(code = HttpStatus.CREATED)
    MatriculaIdResponse saveMatriculaByOrcamento(@RequestParam String cpf);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<MatriculaListResponse> getAllMatriculas();

    @GetMapping(value = "/{idMatricula}")
    @ResponseStatus(code = HttpStatus.OK)
    MatriculaDetalhadoResponse getOneMatricula(@PathVariable UUID idMatricula);

    @DeleteMapping(value = "/{idMatricula}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteMatricula(@PathVariable UUID idMatricula);

    @PatchMapping(value = "/{idMatricula}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateMatricula(@PathVariable UUID idMatricula,
                         @Valid @RequestBody MatriculaAlteracaoRequest matriculaAlteracaoRequest);

    @PatchMapping("/finaliza-matricula/{idMatricula}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void finalizaMatricula(@PathVariable UUID idMatricula);

    @PatchMapping("/ativa-matricula/{idMatricula}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void ativaMatricula(@PathVariable UUID idMatricula);

    @PatchMapping("/cancela-matricula/{idMatricula}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void cancelaMatricula(@PathVariable UUID idMatricula);
}