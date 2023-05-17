package br.com.bonfimvariedades.clienteproduto.fornecedor.infra;

import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FornecedorSpringDataJPARepository extends JpaRepository<Fornecedor, UUID> {
}
