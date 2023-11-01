package br.com.bonfimvariedades.clienteproduto.compra.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Tag(name = "Compra", description = "Compra APIs")
@RequestMapping("/v1/compra")
public interface CompraAPI {
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    CompraIdResponse saveCompra (@Valid @RequestBody CompraRequest request);
}