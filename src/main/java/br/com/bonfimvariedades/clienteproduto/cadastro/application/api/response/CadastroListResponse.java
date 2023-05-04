package br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response;

import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class CadastroListResponse {
    UUID idCadastro;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int desconto;
    int quantidadeParcelas;
    BigDecimal valorFinal;
    LocalDate dataCadastro;
    String nomeCompleto;
    String observacao;
    String status;

    public static List<CadastroListResponse> converte(List<Cadastro>cadastros){
        return cadastros.stream()
                .map(CadastroListResponse::new)
                .collect(Collectors.toList());
    }

    public CadastroListResponse(Cadastro cadastro) {
        this.idCadastro = cadastro.getIdCadastro();
        this.cpf = cadastro.getCliente().getCpf();
        this.tipoPagamento = cadastro.getTipoPagamento();
        this.valorEntrada = cadastro.getValorEntrada();
        this.desconto = cadastro.getDesconto();
        this.quantidadeParcelas = cadastro.getQuantidadeParcelas();
        this.valorFinal = cadastro.getValorFinal();
        this.dataCadastro = cadastro.getDataCadastro();
        this.nomeCompleto = cadastro.getCliente().getNomeCompleto();
        this.observacao = cadastro.getObservacao();
        this.status = cadastro.getStatus().toString();
    }
}