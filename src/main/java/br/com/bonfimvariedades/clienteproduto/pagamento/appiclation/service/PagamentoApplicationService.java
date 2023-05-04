package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.service;

import br.com.bonfimvariedades.clienteproduto.handler.APIException;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.repository.CadastroRepository;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoRequest;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api.PagamentoResponse;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.repository.PagamentoRepository;
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
    private final CadastroRepository cadastroRepository;

    @Override
    public PagamentoResponse savePagamento(UUID idCadastro, PagamentoRequest pagamentoRequest) {
        log.info("[inicia] PagamentoApplicationService - savePagamento");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        BigDecimal totalPago = pagamentoRepository.totalPago(cadastro);
        BigDecimal saldoAPagar = cadastro.getValorFinal().subtract(totalPago);
        if (pagamentoRequest.getValorPago().compareTo(saldoAPagar)<=0){
            Pagamento pagamento = pagamentoRepository.salvaPagamento(new Pagamento(pagamentoRequest, cadastro));
            log.info("[finaliza] PagamentoApplicationService - savePagamento");
            return new PagamentoResponse(pagamento);
        } else {
            throw APIException.build(HttpStatus.BAD_REQUEST,
                    "Pagamento maior que o serviço contratado. Valor a Pagar R$: " + saldoAPagar);
        }
    }
    @Override
    public List<PagamentoResponse> getAllPagamentoByCadastro(UUID idCadastro) {
        log.info("[inicia] PagamentoApplicationService - getAllPagamentoByCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        List<Pagamento> pagamento = pagamentoRepository.getAllPagamentoByCadastro(cadastro);
        log.info("[finaliza] PagamentoApplicationService - getAllPagamentoByCadastro");
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
    public Pagamento savePagamentoByEntrada(Cadastro cadastro, TipoPagamento tipoPagamentoEntrada) {
        log.info("[inicia] PagamentoApplicationService - savePagamentoByEntrada");
        Pagamento pagamento = pagamentoRepository.salvaPagamento(new Pagamento(cadastro, tipoPagamentoEntrada));
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