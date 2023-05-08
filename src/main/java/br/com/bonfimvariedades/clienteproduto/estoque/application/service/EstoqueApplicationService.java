package br.com.bonfimvariedades.clienteproduto.estoque.application.service;

import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueIdResponse;
import br.com.bonfimvariedades.clienteproduto.estoque.application.api.EstoqueRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class EstoqueApplicationService implements EstoqueService {
    private final EstoqueRepository estoqueRepository;

    @Override
    public EstoqueIdResponse saveEstoque(EstoqueRequest request) {
        log.info("[inicia] produtoApplicationService - saveproduto");
        Estoque estoque = estoqueRepository.salvaEstoque(new Estoque(request));
        log.info("[finaliza] produtoApplicationService - saveproduto");
        return EstoqueIdResponse
                .builder()
                .idEstoque(estoque.getIdEstoque())
                .build();
    }
}
