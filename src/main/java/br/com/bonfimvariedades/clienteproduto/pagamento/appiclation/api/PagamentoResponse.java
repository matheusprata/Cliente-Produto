package br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.api;

import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Value
public class PagamentoResponse {
    Long recibo;
    String cpf;
    String nomeCompleto;
    String servico;
    BigDecimal valorContratado;
    BigDecimal valorPago;

    public PagamentoResponse(Pagamento pagamento) {
        this.recibo = pagamento.getIdPagamento();
        this.cpf = ocultarDocumento(pagamento.getPedido().getCliente().getCpf());
        this.nomeCompleto = pagamento.getPedido().getCliente().getNomeCompleto();
        this.servico = pagamento.getPedido().getProduto().getCategoria().toString();
        this.valorContratado = pagamento.getPedido().getValorFinal();
        this.valorPago = pagamento.getValorPago();
    }

    public static List<PagamentoResponse> convert(List<Pagamento> pagamento) {
        return pagamento.stream()
                .map(PagamentoResponse::new)
                .collect((Collectors.toList()));
    }

    public static String ocultarDocumento(String documento) {
        return documento.replaceAll("\\d{3}\\.\\d{3}", "xxx.xxx");
    }
}
