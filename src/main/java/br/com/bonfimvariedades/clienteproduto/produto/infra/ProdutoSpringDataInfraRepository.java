package br.com.bonfimvariedades.clienteproduto.produto.infra;

import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoSpringDataInfraRepository extends JpaRepository<Produto, UUID> {
}
