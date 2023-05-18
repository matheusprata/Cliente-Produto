package br.com.bonfimvariedades.clienteproduto.pedido.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.repository.OrcamentoRepository;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.application.service.PagamentoService;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.request.PedidoRequest;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoIdResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.api.response.PedidoListResponse;
import br.com.bonfimvariedades.clienteproduto.pedido.application.repository.PedidoRepository;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints.Valid.validaAlteracaoPedido;
import static br.com.bonfimvariedades.clienteproduto.pedido.annotation.constraints.Valid.validaSolicitacao;

@Service
@Log4j2
@RequiredArgsConstructor
public class PedidoApplicationService implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PagamentoService pagamentoService;
    private final OrcamentoRepository orcamentoRepository;
    private final FuncionarioRepository funcionarioRepository;

    @Override
    public PedidoIdResponse savePedido(PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - savePedido");
        Produto produto = produtoRepository.getOneProduto(pedidoRequest.getIdProduto());
        Funcionario funcionario = funcionarioRepository.getFuncionario(pedidoRequest.getIdFuncionario());
        validaSolicitacao(pedidoRequest, produto);
        Cliente cliente = clienteRepository.buscaClienteAtravesId(pedidoRequest.getIdCliente());
        Pedido pedido = pedidoRepository.savePedido(new Pedido(cliente, produto, funcionario, pedidoRequest));
        if (pedidoRequest.getValorEntrada().compareTo(BigDecimal.ZERO)>0){
            Pagamento pagamento = pagamentoService.savePagamentoByEntrada(pedido, TipoPagamento.valueOf(pedidoRequest.getTipoPagamentoEntrada()));
        }
        log.info("[finaliza] PedidoApplicationService - savePedido");
        return PedidoIdResponse.builder().idPedido(pedido.getIdPedido()).build();
    }

    @Override
    public PedidoIdResponse savePedidoByOrcamento(String cpf) {
        log.info("[inicia] PedidoApplicationService - savePedidoByOrcamento");
        Orcamento orcamento = orcamentoRepository.getOneOrcamentoByCpf(cpf);
        Pedido pedido = pedidoRepository.savePedido(new Pedido(orcamento));
        orcamentoRepository.deleteOrcamento(orcamento.getIdOrcamento());
        log.info("[finaliza] PedidoApplicationService - savePedidoByOrcamento");
        return PedidoIdResponse.builder().idPedido(pedido.getIdPedido()).build();
    }

    @Override
    public List<PedidoListResponse> getAllPedidos() {
        log.info("[inicia] PedidoApplicationService - getAllPedidos");
        List<Pedido> pedidos = pedidoRepository.getAllPedidos();
        log.info("[finaliza] PedidoApplicationService - getAllPedidos");
        return PedidoListResponse.converte(pedidos);
    }

    @Override
    public PedidoDetalhadoResponse getOnePedido(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - getOnePedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        log.info("[finaliza] PedidoApplicationService - getOnePedido");
        return new PedidoDetalhadoResponse(pedido);
    }

    @Override
    public void deletePedido(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - deletePedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        pedidoRepository.deletePedido(pedido);
        log.info("[finaliza] PedidoApplicationService - deletePedido");
    }

    @Override
    public void updatePedido(UUID idPedido, PedidoAlteracaoRequest request) {
        log.info("[inicia] PedidoApplicationService - updatePedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        validaAlteracaoPedido(pedido, request);
        pedido.altera(request);
        pedidoRepository.savePedido(pedido);
        log.info("[finaliza] PedidoApplicationService - updatePedido");
    }

    @Override
    public void esgotadoPedido(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - finalizaPedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        pedido.esgotadoPedido();
        pedidoRepository.savePedido(pedido);
        log.info("[finaliza] PedidoApplicationService - finalizaPedido");
    }

    @Override
    public void disponivelPedido(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - ativaPedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        pedido.disponivelPedido();
        pedidoRepository.savePedido(pedido);
        log.info("[finaliza] PedidoApplicationService - ativaPedido");
    }

    @Override
    public void aguardandoChegarPedido(UUID idPedido) {
        log.info("[inicia] PedidoApplicationService - cancelaPedido");
        Pedido pedido = pedidoRepository.getOnePedido(idPedido);
        pedido.aguardandoChegarPedido();
        pedidoRepository.savePedido(pedido);
        log.info("[finaliza] PedidoApplicationService - cancelaPedido");
    }
}