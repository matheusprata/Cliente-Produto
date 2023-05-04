package br.com.bonfimvariedades.clienteproduto.pedido.infra;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.pedido.application.repository.PedidoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class PedidoInfraRepository implements PedidoRepository {
    private final PedidoSpringDataJPARepository pedidoSpringDataJPARepository;

    @Override
    public Pedido savePedido(Pedido pedido) {
        log.info("[inicia] PedidoInfraRepository - savePedido");
        pedidoSpringDataJPARepository.save(pedido);
        log.info("[finaliza] PedidoInfraRepository - savePedido");
        return pedido;
    }

    @Override
    public List<Pedido> getAllPedidos() {
        log.info("[inicia] PedidoInfraRepository - getAllPedidos");
        List<Pedido> todasPedidos = pedidoSpringDataJPARepository.findAll();
        log.info("[finaliza] PedidoInfraRepository - getAllPedidos");
        return todasPedidos;
    }

    @Override
    public Pedido getOnePedido(UUID idPedido) {
        log.info("[inicia] PedidoInfraRepository - getOnePedido");
        Optional<Pedido> pedidoOptional = pedidoSpringDataJPARepository.findById(idPedido);
        Pedido pedido = pedidoOptional
                .orElseThrow(() -> APIException.build(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
        log.info("[finaliza] PedidoInfraRepository - getOnePedido");
        return pedido;
    }

    @Override
    public void deletePedido(Pedido pedido) {
        log.info("[inicia] PedidoInfraRepository - deletePedido");
        pedidoSpringDataJPARepository.delete(pedido);
        log.info("[finaliza] PedidoInfraRepository - deletePedido");
    }
}