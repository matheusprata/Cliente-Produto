package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RelatorioClientesResponse {
    private UUID idCliente;
    private String nomeCompleto;
    private String email;
    private String celular;
    private String sexo;
    private String tipoPessoa;
    private String cpf;
    private String estadoCivil;
    LocalDate dataCadastro;
    List<RelatorioPedidoResponse> pedidos;

    public RelatorioClientesResponse(Cliente cliente) {
        this.idCliente = cliente.getIdCliente();
        this.cpf = cliente.getCpf();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.dataCadastro = cliente.getDataCadastro();
        this.pedidos = RelatorioPedidoResponse.converte(cliente.getPedidos());
    }

    public static List<RelatorioClientesResponse> convert(List<Cliente> clientes) {
        return clientes.stream()
                .map(RelatorioClientesResponse::new)
                .collect((Collectors.toList()));
    }
}