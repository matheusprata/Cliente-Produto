package br.com.bonfimvariedades.clienteproduto.relatorios.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.repository.PagamentoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.application.repository.PedidoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioClientesResponse;
import br.com.bonfimvariedades.clienteproduto.relatorios.application.api.response.RelatorioPedidosAtivasResponse;
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
    private final PagamentoRepository pagamentoRepository;

    @Override
    public List<RelatorioClientesResponse> getAllClientes() {
        log.info("[inicia] RelatoriosApplicationService - getAllClientes");
        List<Cliente> clientes = clienteRepository.buscaTodosClientes();
        log.info("[Finaliza] RelatoriosApplicationService - getAllClientes");
        return RelatorioClientesResponse.convert(clientes);
    }

    @Override
    public List<RelatorioPedidosAtivasResponse> getAllPedidosAtivas() {
        log.info("[inicia] RelatoriosApplicationService - getAllPedidosAtivas");
        List<Pedido> pedidos = pedidoRepository.getAllPedidos();
        log.info("[Finaliza] RelatoriosApplicationService - getAllPedidosAtivas");
        return RelatorioPedidosAtivasResponse.convert(pedidos);
    }
}
