package br.com.bonfimvariedades.clienteproduto.produto.application.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/produto")
public interface ProdutoApi {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    ProdutoIdResponse saveProduto(@Valid @RequestBody ProdutoRequest request);

    @GetMapping("/{idProduto}")
    @ResponseStatus(code = HttpStatus.OK)
    ProdutoResponse getOneProduto(@PathVariable UUID idProduto);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<ProdutoResponse> getAllProdutos();

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deleteProduto(@PathVariable UUID idProduto);

    @PatchMapping("/update/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateProduto(@PathVariable UUID idProduto, @Valid @RequestBody ProdutoUpdateRequest updateRequest);

    @PatchMapping("/update/status/{idProduto}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updateProdutoStatus(@PathVariable UUID idProduto);
}