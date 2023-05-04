package br.com.bonfimvariedades.clienteproduto.orcamento.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/orcamento")
public interface OrcamentoAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    OrcamentoResponse saveOrcamento(@Valid @RequestBody OrcamentoRequest orcamentoRequest);

    @GetMapping(value = "/{idOrcamento}")
    @ResponseStatus(code = HttpStatus.OK)
    OrcamentoResponse getOneOrcamento(@PathVariable Long idOrcamento);
}