package br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.UUID;

@Value
public class CadastroDetalhadoResponse {
    UUID idCadastro;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int quantidadeParcelas;
    BigDecimal valorProduto;
    BigDecimal desconto;
    BigDecimal valorFinal;
    LocalDate dataCadastro;
    String nomeCompleto;
    String observacao;
    String status;

    public CadastroDetalhadoResponse(Cadastro cadastro) {
        this.idCadastro = cadastro.getIdCadastro();
        this.cpf = cadastro.getCliente().getCpf();
        this.tipoPagamento = cadastro.getTipoPagamento();
        this.valorEntrada = cadastro.getValorEntrada();
        this.quantidadeParcelas = cadastro.getQuantidadeParcelas();
        this.valorProduto = cadastro.getProduto().getValorProduto();
        this.desconto =  calculaDesconto(cadastro.getDesconto(), cadastro.getProduto().getValorProduto()) ;
        this.valorFinal = cadastro.getValorFinal();
        this.dataCadastro = cadastro.getDataCadastro();
        this.nomeCompleto = cadastro.getCliente().getNomeCompleto();
        this.observacao = cadastro.getObservacao();
        this.status = cadastro.getStatus().toString();
    }

    private BigDecimal calculaDesconto(int desconto, BigDecimal valorProduto) {
        return valorProduto.multiply(new BigDecimal(desconto)).divide(BigDecimal.valueOf(100),
                2, RoundingMode.HALF_UP);
    }
}
