package br.com.bonfimvariedades.clienteproduto.funcionario.domain;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.groups.PessoaFisica;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioResquest;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.api.FuncionarioUpdateResquest;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFuncionario;
    private String nomeCompleto;
    @NotBlank(message = "Campo Obrigatório!")
    @CPF(groups = PessoaFisica.class, message = "CPF inválido!")
    @Column(unique = true, length = 14, updatable = false)
    private String cpf;
    @NotBlank(message = "Campo Obrigatório!")
    private String cargoFuncionario;
    @Column(unique = true, length = 11, updatable = false)
    private String cnh;
    private LocalDate validadeCnh;
    private LocalDate dataAdmissao;
    private LocalDate dataReadmissao;
    private LocalDate dataDemissao;
    @Enumerated(EnumType.STRING)
    private StatusFuncionario statusFuncionario = StatusFuncionario.ATIVO;

    public Funcionario(FuncionarioResquest resquest) {
        this.nomeCompleto = resquest.getNomeCompleto().toUpperCase();
        this.cpf = resquest.getCpf();
        this.cargoFuncionario = resquest.getCargoFuncionario().toUpperCase();
        this.cnh = resquest.getCnh();
        this.validadeCnh = resquest.getValidadeCnh();
        this.dataAdmissao = resquest.getDataAdmissao();
    }
    public void altera(FuncionarioUpdateResquest updateRequest) {
        this.nomeCompleto = updateRequest.getNomeCompleto().toUpperCase();
        this.cargoFuncionario = updateRequest.getCargoFuncionario().toUpperCase();
        this.cnh = updateRequest.getCnh();
        this.validadeCnh = updateRequest.getValidadeCnh();
    }
    public void demitido() {
        this.statusFuncionario = statusFuncionario.DEMITIDO;
        this.dataDemissao = getDataDemissao().now();
    }
    public void ativa() {
        this.statusFuncionario = statusFuncionario.ATIVO;
        this.dataReadmissao = getDataReadmissao().now();
    }
}