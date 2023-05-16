package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.StatusFuncionario;
import lombok.Value;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
public class FuncionarioResponse {
    UUID idFuncionario;
    String nomeCompleto;
    String cpf;
    String cargoFuncionario;
    String cnh;
    LocalDate validadeCnh;
    LocalDate dataAdmissao;
    LocalDate dataReadmissao;
    LocalDate dataDemissao;
    StatusFuncionario statusFuncionario;

    public FuncionarioResponse(Funcionario funcionario) {
        this.idFuncionario = funcionario.getIdFuncionario();
        this.nomeCompleto = funcionario.getNomeCompleto();
        this.cpf = funcionario.getCpf();
        this.cargoFuncionario = funcionario.getCargoFuncionario();
        this.cnh = funcionario.getCnh();
        this.validadeCnh = funcionario.getValidadeCnh();
        this.dataAdmissao = funcionario.getDataAdmissao();
        this.dataReadmissao = funcionario.getDataReadmissao();
        this.dataDemissao = funcionario.getDataDemissao();
        this.statusFuncionario = funcionario.getStatusFuncionario();
    }
    public static List<FuncionarioResponse> converte(List<Funcionario> funcionarios) {
        return funcionarios
                .stream()
                .map(FuncionarioResponse::new)
                .toList();
    }
}