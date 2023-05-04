package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/pagamento")
public interface PagamentoAPI {

    @PostMapping("/{idPedido}")
    @ResponseStatus(code = HttpStatus.CREATED)
    PagamentoResponse savePagamento(@PathVariable UUID idPedido, @Valid @RequestBody PagamentoRequest pagamentoRequest);

    @GetMapping(value = "/pedido/{idPedido}")
    @ResponseStatus(code = HttpStatus.OK)
    List<PagamentoResponse> getAllPagamentoByPedido(@PathVariable UUID idPedido);

    @GetMapping("/{idPagamento}")
    @ResponseStatus(code = HttpStatus.OK)
    PagamentoResponse getOnePagamento(@PathVariable Long idPagamento);

    @DeleteMapping("/{idPagamento}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletePagamento(@PathVariable Long idPagamento);
}