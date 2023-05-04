package br.com.bonfimvariedades.clienteproduto.matricula.application.repository;


import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;

import java.util.List;
import java.util.UUID;

public interface MatriculaRepository {
    Matricula saveMatricula(Matricula matricula);
    List<Matricula> getAllMatriculas();
    Matricula getOneMatricula(UUID idMatricula);
    void deleteMatricula(Matricula matricula);
}
