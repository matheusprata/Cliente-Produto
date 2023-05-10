package br.com.bonfimvariedades.clienteproduto.pagamento.application.service;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.pedido.application.repository.PedidoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.api.PagamentoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.api.PagamentoResponse;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.repository.PagamentoRepository;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PagamentoApplicationService implements PagamentoService {
    private final PagamentoRepository pagamentoRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    public PagamentoResponse savePagamento(UUID idPedido, PagamentoRequest pagamentoRequest) {
        log.info("[inicia] PagamentoApplicationService - savePagamento");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        BigDecimal totalPago = pagamentoRepository.totalPago(pedido);
        BigDecimal saldoAPagar = pedido.getValorFinal().subtract(totalPago);
        if (pagamentoRequest.getValorPago().compareTo(saldoAPagar)<=0){
            Pagamento pagamento = pagamentoRepository.salvaPagamento(new Pagamento(pagamentoRequest, pedido));
            log.info("[finaliza] PagamentoApplicationService - savePagamento");
            return new PagamentoResponse(pagamento);
        } else {
            throw APIException.build(HttpStatus.BAD_REQUEST,
                    "Pagamento maior que o serviço contratado. Valor a Pagar R$: " + saldoAPagar);
        }
    }
    @Override
    public List<PagamentoResponse> getAllPagamentoByPedido(UUID idPedido) {
        log.info("[inicia] PagamentoApplicationService - getAllPagamentoByPedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        List<Pagamento> pagamento = pagamentoRepository.getAllPagamentoByPedido(pedido);
        log.info("[finaliza] PagamentoApplicationService - getAllPagamentoByPedido");
        return PagamentoResponse.convert(pagamento);
    }
    @Override
    public PagamentoResponse getOnePagamento(Long idPagamento) {
        log.info("[inicia] PagamentoApplicationService - getOnePagamento");
        Pagamento pagamento = pagamentoRepository.getOnePagamento(idPagamento);
        log.info("[finaliza] PagamentoApplicationService - getOnePagamento");
        return new PagamentoResponse(pagamento);
    }
    @Override
    public Pagamento savePagamentoByEntrada(Pedido pedido, TipoPagamento tipoPagamentoEntrada) {
        log.info("[inicia] PagamentoApplicationService - savePagamentoByEntrada");
        Pagamento pagamento = pagamentoRepository.salvaPagamento(new Pagamento(pedido, tipoPagamentoEntrada));
        log.info("[finaliza] PagamentoApplicationService - savePagamentoByEntrada");
        return pagamento;
    }
    @Override
    public void deletePagamento(Long idPagamento) {
        log.info("[inicia] PagamentoApplicationService - deletePagamento");
        pagamentoRepository.deletePagamento(pagamentoRepository.getOnePagamento(idPagamento).getIdPagamento());
        log.info("[finaliza] PagamentoApplicationService - deletePagamento");
    }
}