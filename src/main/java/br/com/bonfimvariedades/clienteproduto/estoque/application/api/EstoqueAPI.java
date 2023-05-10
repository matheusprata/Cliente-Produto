package br.com.bonfimvariedades.clienteproduto.estoque.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("/v1/estoque")
public interface EstoqueAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    EstoqueIdResponse saveEstoque(@Valid @RequestBody EstoqueRequest request);

    @GetMapping("/estoque/{idEstoque}")
    @ResponseStatus(code = HttpStatus.OK)
    EstoqueResponse getOneEstoque(@PathVariable UUID idEstoque);

    @GetMapping("/produto/{idProduto}")
    @ResponseStatus(code = HttpStatus.OK)
    List<EstoqueListResponse> getAllEstoqueByProduto(@PathVariable UUID idProduto);

    @PatchMapping(value = "/estoque/{idEstoque}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void patchAlteraEstoque(@PathVariable UUID idEstoque,
                            @Valid @RequestBody EstoqueAlteracaoRequest estoqueAlteracaoRequest);
}
