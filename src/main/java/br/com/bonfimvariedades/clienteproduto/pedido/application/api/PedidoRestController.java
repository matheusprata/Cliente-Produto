package br.com.bonfimvariedades.clienteproduto.pedido.application.api;

import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoIdResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoListResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.service.PedidoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PedidoRestController implements PedidoAPI {
    private final PedidoService pedidoService;

    @Override
    public PedidoIdResponse savePedido(PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoRestController - savePedido");
        PedidoIdResponse pedidoCriado = pedidoService.savePedido(pedidoRequest);
        log.info("[finaliza] PedidoRestController - savePedido");
        return pedidoCriado;
    }

    @Override
    public PedidoIdResponse savePedidoByOrcamento(String cpf) {
        log.info("[inicia] PedidoRestController -savePedidoByOrcamento");
        PedidoIdResponse pedidoCriado = pedidoService.savePedidoByOrcamento(cpf);
        log.info("[finaliza] PedidoRestController - savePedidoByOrcamento");
        return pedidoCriado;
    }

    @Override
    public List<PedidoListResponse> getAllPedidos() {
        log.info("[inicia] PedidoRestController - getAllPedidos");
        List<PedidoListResponse> pedidos = pedidoService.getAllPedidos();
        log.info("[finaliza] PedidoRestController - getAllPedidos");
        return pedidos;
    }

    @Override
    public PedidoDetalhadoResponse getOnePedido(UUID idPedido) {
        log.info("[inicia] PedidoRestController - getOnePedido");
        log.info("idPedido {}", idPedido);
        PedidoDetalhadoResponse pedidoDetalhadoResponse = pedidoService.getOnePedido(idPedido);
        log.info("[finaliza] PedidoRestController - getOnePedido");
        return pedidoDetalhadoResponse;
    }

    @Override
    public void deletePedido(UUID idPedido) {
        log.info("[inicia] PedidoRestController - deletePedido");
        pedidoService.deletePedido(idPedido);
        log.info("[finaliza] PedidoRestController - deletePedido");
    }

    @Override
    public void updatePedido(UUID idPedido, PedidoAlteracaoRequest pedidoAlteracaoRequest) {
        log.info("[inicia] PedidoRestController - updatePedido");
        pedidoService.updatePedido(idPedido, pedidoAlteracaoRequest);
        log.info("[finaliza] PedidoRestController - updatePedido");
    }

    @Override
    public void finalizaPedido(UUID idPedido) {
        log.info("[inicia] PedidoRestController - finalizaPedido");
        pedidoService.esgotadoPedido(idPedido);
        log.info("[finaliza] PedidoRestController - finalizaPedido");
    }

    @Override
    public void ativaPedido(UUID idPedido) {
        log.info("[inicia] PedidoRestController - ativaPedido");
        pedidoService.disponivelPedido(idPedido);
        log.info("[finaliza] PedidoRestController - ativaPedido");
    }

    @Override
    public void cancelaPedido(UUID idPedido) {
        log.info("[inicia] PedidoRestController - cancelaPedido");
        pedidoService.aguardandoChegarPedido(idPedido);
        log.info("[finaliza] PedidoRestController - cancelaPedido");
    }
}