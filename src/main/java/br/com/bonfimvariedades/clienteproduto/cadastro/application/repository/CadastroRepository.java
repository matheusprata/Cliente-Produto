package br.com.bonfimvariedades.clienteproduto.cadastro.application.repository;


import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;

import java.util.List;
import java.util.UUID;

public interface CadastroRepository {
    Cadastro saveCadastro(Cadastro cadastro);
    List<Cadastro> getAllCadastros();
    Cadastro getOneCadastro(UUID idCadastro);
    void deleteCadastro(Cadastro cadastro);
}
