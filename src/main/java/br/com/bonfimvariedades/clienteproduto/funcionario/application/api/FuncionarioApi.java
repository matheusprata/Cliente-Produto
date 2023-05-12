package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;

@Tag(name = "Funcionario", description = "Funcionario APIs")
@RequestMapping("/v1/funcionario")
public interface FuncionarioApi {
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    FuncionarioIdResponse saveFuncionario(@Valid @RequestBody FuncionarioResquest resquest);

    @GetMapping("{idFuncionario}")
    @ResponseStatus(code = HttpStatus.OK)
    FuncionarioResponse getFuncionario(@PathVariable UUID idFuncionario);

    @PatchMapping("/update/{idFuncionario}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateFuncionario(@PathVariable  UUID idFuncionario, @Valid @RequestBody FuncionarioUpdateResquest updateRequest);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<FuncionarioResponse> getAllFuncionarios();

    @PatchMapping("demite/{idFuncionario}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void demiteFuncionario(@PathVariable UUID idFuncionario);

    @PatchMapping("recontratar/{idFuncionario}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void ativaFuncionario(@PathVariable UUID idFuncionario);
}