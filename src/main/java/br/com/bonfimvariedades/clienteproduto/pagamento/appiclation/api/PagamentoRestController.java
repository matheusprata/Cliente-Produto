package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api;

import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
@RequiredArgsConstructor
public class PagamentoRestController implements PagamentoAPI {
    private final PagamentoService pagamentoService;

    @Override
    public PagamentoResponse savePagamento(UUID idPedido, PagamentoRequest pagamentoRequest) {
        log.info("[inicia] PagamentoRestController - savePagamento");
        PagamentoResponse pagamentoResponse = pagamentoService.savePagamento(idPedido, pagamentoRequest);
        log.info("[finaliza] PagamentoRestController - savePagamento");
        return pagamentoResponse;
    }
    @Override
    public List<PagamentoResponse> getAllPagamentoByPedido(UUID idPedido) {
        log.info("[inicia] PagamentoRestController - getAllPagamentoByPedido");
        List<PagamentoResponse>  getPagamento = pagamentoService.getAllPagamentoByPedido(idPedido);
        log.info("[finaliza] PagamentoRestController - getAllPagamentoByPedido");
        return getPagamento;
    }
    @Override
    public PagamentoResponse getOnePagamento(Long idPagamento) {
        log.info("[inicia] PagamentoRestController - getOnePagamento");
        PagamentoResponse pagamentoResponse = pagamentoService.getOnePagamento(idPagamento);
        log.info("[finaliza] PagamentoRestController - getOnePagamento");
        return pagamentoResponse;
    }
    @Override
    public void deletePagamento(Long idPagamento) {
        log.info("[inicia] PagamentoRestController - deletePagamento");
        pagamentoService.deletePagamento(idPagamento);
        log.info("[finaliza] PagamentoRestController - deletePagamento");
    }
}