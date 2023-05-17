package br.com.bonfimvariedades.clienteproduto.fornecedor.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Tag(name = "Fornecedor", description = "Fornecedor APIs")
@RequestMapping("/v1/fornecedor")
public interface FornecedorApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    FornecedorIdResponse saveFornecedor(@Valid @RequestBody FornecedorRequest resquest);

    @GetMapping("{idFornecedor}")
    @ResponseStatus(code = HttpStatus.OK)
    FornecedorResponse getFornecedor(@PathVariable UUID idFornecedor);

    @PatchMapping("/update/{idFornecedor}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateFornecedor(@PathVariable  UUID idFornecedor, @Valid @RequestBody FornecedorUpdateResquest updateRequest);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<FornecedorResponse> getAllFornecedors();

    @PatchMapping("/inativa/{idFornecedor}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void inativaFornecedor(@PathVariable UUID idFornecedor);
}