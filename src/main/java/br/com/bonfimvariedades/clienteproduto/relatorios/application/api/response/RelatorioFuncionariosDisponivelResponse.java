package br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response;

import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.StatusFuncionario;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class RelatorioFuncionariosDisponivelResponse {
    UUID idFuncionario;
    String nomeCompleto;
    String cpf;
    LocalDate dataNascimento;
    String cargoFuncionario;
    BigDecimal salario;
    String cnh;
    LocalDate validadeCnh;
    StatusFuncionario statusFuncionario;

    public RelatorioFuncionariosDisponivelResponse(Funcionario funcionario){
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nomeCompleto = funcionario.getNomeCompleto();
        this.cpf = funcionario.getCpf();
        this.dataNascimento = funcionario.getDataNascimento();
        this.cargoFuncionario = funcionario.getCargoFuncionario();
        this.salario = funcionario.getSalario();
        this.cnh = funcionario.getCnh();
        this.validadeCnh = funcionario.getValidadeCnh();
        this.statusFuncionario = funcionario.getStatusFuncionario();
    }

    public static List<RelatorioFuncionariosDisponivelResponse> convert(List<Funcionario> funcionarios) {
        return funcionarios.stream()
                .map(RelatorioFuncionariosDisponivelResponse::new)
                .collect(Collectors.toList());
    }
}