package br.com.bonfimvariedades.clienteproduto.cliente.infra;

import java.util.Optional;
import java.util.UUID;

import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteSpringDataJPARepository extends JpaRepository <Cliente, UUID>{

    Optional<Cliente> findByCpf(String cpf);
}
