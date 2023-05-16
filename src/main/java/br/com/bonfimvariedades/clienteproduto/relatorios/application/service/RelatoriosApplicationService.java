package br.com.bonfimvariedades.clienteproduto.relatorios.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pedido.application.repository.PedidoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioEstoquesDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioFuncionariosDisponivelResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosDisponivelResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class RelatoriosApplicationService implements RelatoriosService {
    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;
    private final EstoqueRepository estoqueRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public List<RelatorioClientesResponse> getAllClientes() {
        log.info("[inicia] RelatoriosApplicationService - getAllClientes");
        List<Cliente> clientes = clienteRepository.buscaTodosClientes();
        log.info("[Finaliza] RelatoriosApplicationService - getAllClientes");
        return RelatorioClientesResponse.convert(clientes);
    }

    @Override
    public List<RelatorioPedidosDisponivelResponse> getAllPedidosDisponiveis() {
        log.info("[inicia] RelatoriosApplicationService - getAllPedidosDisponiveis");
        List<Pedido> pedidos = pedidoRepository.getAllPedidos();
        log.info("[Finaliza] RelatoriosApplicationService - getAllPedidosDisponiveis");
        return RelatorioPedidosDisponivelResponse.convert(pedidos);
    }

    @Override
    public List<RelatorioEstoquesDisponivelResponse> getAllEstoquesDisponiveis() {
        log.info("[inicia] RelatoriosApplicationService - getAllEstoquesDisponiveis");
        List<Estoque> estoques = estoqueRepository.getAllEstoque();
        log.info("[Finaliza] RelatoriosApplicationService - getAllEstoquesDisponiveis");
        return RelatorioEstoquesDisponivelResponse.convert(estoques);
    }

    @Override
    public List<RelatorioFuncionariosDisponivelResponse> getAllFuncionariosDisponiveis() {
        log.info("[inicia] RelatoriosApplicationService - getAllFuncionariosDisponiveis");
        List<Funcionario> funcionarios = funcionarioRepository.getAllFuncionarios();
        log.info("[Finaliza] RelatoriosApplicationService - getAllFuncionariosDisponiveis");
        return RelatorioFuncionariosDisponivelResponse.convert(funcionarios);
    }
}