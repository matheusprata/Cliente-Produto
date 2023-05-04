package br.com.bonfimvariedades.clienteproduto.matricula.application.api.response;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class MatriculaDetalhadoResponse {
    UUID idMatricula;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int quantidadeParcelas;
    BigDecimal valorProduto;
    BigDecimal desconto;
    BigDecimal valorFinal;
    LocalDate dataMatricula;
    String nomeCompleto;
    String observacao;
    String status;

    public MatriculaDetalhadoResponse(Matricula matricula) {
        this.idMatricula = matricula.getIdMatricula();
        this.cpf = matricula.getCliente().getCpf();
        this.tipoPagamento = matricula.getTipoPagamento();
        this.valorEntrada = matricula.getValorEntrada();
        this.quantidadeParcelas = matricula.getQuantidadeParcelas();
        this.valorProduto = matricula.getProduto().getValorProduto();
        this.desconto =  calculaDesconto(matricula.getDesconto(), matricula.getProduto().getValorProduto()) ;
        this.valorFinal = matricula.getValorFinal();
        this.dataMatricula = matricula.getDataMatricula();
        this.nomeCompleto = matricula.getCliente().getNomeCompleto();
        this.observacao = matricula.getObservacao();
        this.status = matricula.getStatus().toString();
    }

    private BigDecimal calculaDesconto(int desconto, BigDecimal valorProduto) {
        return valorProduto.multiply(new BigDecimal(desconto)).divide(BigDecimal.valueOf(100),
                2, RoundingMode.HALF_UP);
    }
}
