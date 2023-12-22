package br.com.bonfimvariedades.clienteproduto.pedido.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.estoque.application.repository.EstoqueRepository;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.funcionario.application.repository.FuncionarioRepository;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.handler.ProdutoNaoDisponivelException;
import br.com.bonfimvariedades.clienteproduto.handler.QuantidadeInsuficienteException;
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
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
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
    private final EstoqueRepository estoqueRepository;

    @Override
    public PedidoIdResponse savePedido(PedidoRequest pedidoRequest) {
        log.info("[inicia] PedidoApplicationService - savePedido");
        // Obtem o produto e valida a solicitação
        Produto produto = produtoRepository.getOneProduto(pedidoRequest.getIdProduto());
        validaSolicitacao(pedidoRequest, produto);
        // valida funcionario e seu cadastro para realizar venda
        Funcionario funcionario = funcionarioRepository.getFuncionario(pedidoRequest.getIdFuncionario());
        // valida cliente e seus dados para o pedido
        Cliente cliente = clienteRepository.buscaClienteAtravesId(pedidoRequest.getIdCliente());
        // Verificar se há estoque disponível
        BigDecimal quantidadeDisponivel = verificarQuantidadeDisponivelNoEstoque(produto, pedidoRequest.getQuantidadeProdutoPedido());
        if (quantidadeDisponivel.compareTo(BigDecimal.ZERO) <= 0){
            log.warn("Não contem essa quantidade de {} no estoque. Quantidade disponível: {}", produto.getNomeProduto(), quantidadeDisponivel);
            throw new QuantidadeInsuficienteException("A quantidade em estoque não é suficiente para atender ao pedido.");
        }
        // Verificar se a quantidade solicitada é maior que a disponível
        if (pedidoRequest.getQuantidadeProdutoPedido().compareTo(quantidadeDisponivel) > 0) {
            log.warn("A quantidade solicitada é maior que a quantidade disponível em estoque. Quantidade disponível: {}", quantidadeDisponivel);
            throw new QuantidadeInsuficienteException("A quantidade em estoque não é suficiente para atender ao pedido.");
        }
        // Calcular o total do pedido
        BigDecimal valorUnitario = produto.getValorProduto();
        BigDecimal totalPedido = valorUnitario.multiply(pedidoRequest.getQuantidadeProdutoPedido());
        // Salva o pedido
        Pedido pedido = pedidoRepository.savePedido(new Pedido(cliente, produto, funcionario, pedidoRequest));
        // Atualizar o estoque
        atualizarEstoque(produto, pedidoRequest.getQuantidadeProdutoPedido());
        if (pedidoRequest.getValorEntrada().compareTo(BigDecimal.ZERO)>0){
            Pagamento pagamento = pagamentoService.savePagamentoByEntrada(pedido, TipoPagamento.valueOf(pedidoRequest.getTipoPagamentoEntrada()));
        }
        // Verificar se precisa alterar para estoque esgotado
        BigDecimal quantidadeDisponivelEstoque = verificarSubtracaoNoEstoque(produto, pedidoRequest.getQuantidadeProdutoPedido(), quantidadeDisponivel);
        if (quantidadeDisponivelEstoque.compareTo(BigDecimal.ZERO) <= 0){
            // Se a quantidade disponível for menor ou igual a zero, o produto está esgotado
            produto.produtoEsgotado();
            produtoRepository.salvaProduto(produto);
            throw new ProdutoNaoDisponivelException("Vendido todos os produtos do estoque.");
        }
        log.info("[finaliza] PedidoApplicationService - savePedido");
        return PedidoIdResponse.builder().idPedido(pedido.getIdPedido()).build();
    }

    private BigDecimal verificarSubtracaoNoEstoque(Produto produto, BigDecimal quantidadePedido, BigDecimal quantidadeDisponivel){
        log.info("Quantidade disponível antes da subtração: {}", quantidadeDisponivel);
        // Subtrair a quantidade do pedido da quantidade disponível
        quantidadeDisponivel = quantidadeDisponivel.subtract(quantidadePedido);
        log.info("Quantidade disponível após a subtração: {}", quantidadeDisponivel);
        // Garantir que a quantidade disponível não seja menor que zero após a subtração
        quantidadeDisponivel = quantidadeDisponivel.max(BigDecimal.ZERO);
        return quantidadeDisponivel;
    }
    private BigDecimal verificarQuantidadeDisponivelNoEstoque(Produto produto, BigDecimal quantidadePedido) {
        // Obter a lista de estoques associados ao produto
        List<Estoque> estoques = estoqueRepository.getEstoqueByIdProduto(produto);
        // Verificar a quantidade total disponível no estoque
        BigDecimal quantidadeDisponivel = estoques.stream()
                .map(Estoque::getQuantidadeProduto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        log.info("Quantidade disponível após garantir que não seja menor que zero: {}", quantidadeDisponivel);
        return quantidadeDisponivel;
    }

    private void atualizarEstoque(Produto produto, BigDecimal quantidadePedido) {
        // Obter a lista de estoques associados ao produto
        List<Estoque> estoques = estoqueRepository.getEstoqueByIdProduto(produto);
        // Iterar sobre os estoques para atualizar a quantidade disponível
        for (Estoque estoque : estoques) {
            BigDecimal novaQuantidade = estoque.getQuantidadeProduto().subtract(quantidadePedido);
            // Garantir que a nova quantidade não seja menor que zero
            novaQuantidade = novaQuantidade.max(BigDecimal.ZERO);
            // Atualizar o estoque no banco de dados
            estoque.setQuantidadeProduto(novaQuantidade);
            estoqueRepository.salvaEstoque(estoque);
        }
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