package br.com.bonfimvariedades.clientefiado.cliente.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bonfimvariedades.clientefiado.cliente.domain.Cliente;

public interface ClienteSpringDataJPARepository extends JpaRepository <Cliente, UUID>{

}
