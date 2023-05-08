package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;


@RequestMapping("/v1/estoque")
public interface EstoqueAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EstoqueIdResponse saveEstoque(@Valid @RequestBody EstoqueRequest request);
}
