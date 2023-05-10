package br.com.bonfimvariedades.clienteproduto.estoque.infra;

import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EstoqueSpringDataJPARepository extends JpaRepository<Estoque, UUID> {
    List<Estoque> findEstoqueByProduto(Produto produto);
}
