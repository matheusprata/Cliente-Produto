package br.com.bonfimvariedades.clienteproduto.orcamento.application.api;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class OrcamentoResponse {
    Long idOrcamento;
    String cpf;
    String nomeCompleto;
    TipoPagamento tipoPagamento;
    LocalDate dataOrcamento;
    BigDecimal valorEntrada;
    int desconto;
    int quantidadeParcelas;
    String observacao;
    LocalDate validade;
    BigDecimal valorFinal;

    public OrcamentoResponse(Orcamento orcamento) {
        this.idOrcamento = orcamento.getIdOrcamento();
        this.cpf = orcamento.getCliente().getCpf();
        this.nomeCompleto = orcamento.getCliente().getNomeCompleto();
        this.tipoPagamento = orcamento.getTipoPagamento();
        this.dataOrcamento = orcamento.getDataOrcamento();
        this.valorEntrada = orcamento.getValorEntrada();
        this.desconto = orcamento.getDesconto();
        this.quantidadeParcelas = orcamento.getQuantidadeParcelas();
        this.observacao = orcamento.getObservacao();
        this.validade = orcamento.getValidade();
        this.valorFinal = orcamento.getValorFinal();
    }
}