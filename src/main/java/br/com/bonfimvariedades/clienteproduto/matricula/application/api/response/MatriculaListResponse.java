package br.com.bonfimvariedades.clienteproduto.matricula.application.api.response;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Value
public class MatriculaListResponse {
    UUID idMatricula;
    String cpf;
    TipoPagamento tipoPagamento;
    BigDecimal valorEntrada;
    int desconto;
    int quantidadeParcelas;
    BigDecimal valorFinal;
    LocalDate dataMatricula;
    String nomeCompleto;
    String observacao;
    String status;

    public static List<MatriculaListResponse> converte(List<Matricula>matriculas){
        return matriculas.stream()
                .map(MatriculaListResponse::new)
                .collect(Collectors.toList());
    }

    public MatriculaListResponse(Matricula matricula) {
        this.idMatricula = matricula.getIdMatricula();
        this.cpf = matricula.getCliente().getCpf();
        this.tipoPagamento = matricula.getTipoPagamento();
        this.valorEntrada = matricula.getValorEntrada();
        this.desconto = matricula.getDesconto();
        this.quantidadeParcelas = matricula.getQuantidadeParcelas();
        this.valorFinal = matricula.getValorFinal();
        this.dataMatricula = matricula.getDataMatricula();
        this.nomeCompleto = matricula.getCliente().getNomeCompleto();
        this.observacao = matricula.getObservacao();
        this.status = matricula.getStatus().toString();
    }
}