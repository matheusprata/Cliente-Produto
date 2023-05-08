package br.com.bonfimvariedades.clienteproduto.pedido.application.api;

import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoIdResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoListResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/v1/pedido")
public interface PedidoAPI {

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    PedidoIdResponse savePedido(@Valid @RequestBody PedidoRequest pedidoRequest);

    @PostMapping(value = "/orcamento")
    @ResponseStatus(code = HttpStatus.CREATED)
    PedidoIdResponse savePedidoByOrcamento(@RequestParam String cpf);

    @GetMapping("/all")
    @ResponseStatus(code = HttpStatus.OK)
    List<PedidoListResponse> getAllPedidos();

    @GetMapping(value = "/{idPedido}")
    @ResponseStatus(code = HttpStatus.OK)
    PedidoDetalhadoResponse getOnePedido(@PathVariable UUID idPedido);

    @DeleteMapping(value = "/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void deletePedido(@PathVariable UUID idPedido);

    @PatchMapping(value = "/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void updatePedido(@PathVariable UUID idPedido,
                         @Valid @RequestBody PedidoAlteracaoRequest pedidoAlteracaoRequest);

    @PatchMapping("/finaliza-pedido/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void finalizaPedido(@PathVariable UUID idPedido);

    @PatchMapping("/ativa-pedido/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void ativaPedido(@PathVariable UUID idPedido);

    @PatchMapping("/cancela-pedido/{idPedido}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    void cancelaPedido(@PathVariable UUID idPedido);
}