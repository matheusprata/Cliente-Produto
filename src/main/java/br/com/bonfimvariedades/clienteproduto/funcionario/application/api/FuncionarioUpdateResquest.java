package br.com.bonfimvariedades.clienteproduto.funcionario.application.api;

import lombok.Data;
import java.time.LocalDate;

@Data
public class FuncionarioUpdateResquest {
    String nomeCompleto;
    LocalDate validadeCnh;
    String cargoFuncionario;
    String cnh;
}