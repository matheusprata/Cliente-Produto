package br.com.bonfimvariedades.clienteproduto.cadastro.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.request.CadastroRequest;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroIdResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.api.response.CadastroListResponse;
import br.com.bonfimvariedades.clienteproduto.cadastro.application.repository.CadastroRepository;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.Cadastro;
import br.com.bonfimvariedades.clienteproduto.cadastro.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.orcamento.application.repository.OrcamentoRepository;
import br.com.bonfimvariedades.clienteproduto.orcamento.domain.Orcamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.appiclation.service.PagamentoService;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import br.com.bonfimvariedades.clienteproduto.produto.application.repository.ProdutoRepository;
import br.com.bonfimvariedades.clienteproduto.produto.domain.Produto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static br.com.bonfimvariedades.clienteproduto.cadastro.annotation.constraints.Valid.validaAlteracaoCadastro;
import static br.com.bonfimvariedades.clienteproduto.cadastro.annotation.constraints.Valid.validaSolicitacao;

@Service
@Log4j2
@RequiredArgsConstructor
public class CadastroApplicationService implements CadastroService{
    private final CadastroRepository cadastroRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PagamentoService pagamentoService;
    private final OrcamentoRepository orcamentoRepository;

    @Override
    public CadastroIdResponse saveCadastro(CadastroRequest cadastroRequest) {
        log.info("[inicia] CadastroApplicationService - saveCadastro");
        Produto produto = produtoRepository.getOneProduto(cadastroRequest.getIdProduto());
        validaSolicitacao(cadastroRequest, produto);
        Cliente cliente = clienteRepository.buscaClienteAtravesId(cadastroRequest.getIdCliente());
        Cadastro cadastro = cadastroRepository.saveCadastro(new Cadastro(cliente, produto,cadastroRequest));
        if (cadastroRequest.getValorEntrada().compareTo(BigDecimal.ZERO)>0){
            Pagamento pagamento = pagamentoService.savePagamentoByEntrada(cadastro, TipoPagamento.valueOf(cadastroRequest.getTipoPagamentoEntrada()));
        }
        log.info("[finaliza] CadastroApplicationService - saveCadastro");
        return CadastroIdResponse.builder().idCadastro(cadastro.getIdCadastro()).build();
    }

    @Override
    public CadastroIdResponse saveCadastroByOrcamento(String cpf) {
        log.info("[inicia] CadastroApplicationService - saveCadastroByOrcamento");
        Orcamento orcamento = orcamentoRepository.getOneOrcamentoByCpf(cpf);
        Cadastro cadastro = cadastroRepository.saveCadastro(new Cadastro(orcamento));
        orcamentoRepository.deleteOrcamento(orcamento.getIdOrcamento());
        log.info("[finaliza] CadastroApplicationService - saveCadastroByOrcamento");
        return CadastroIdResponse.builder().idCadastro(cadastro.getIdCadastro()).build();
    }

    @Override
    public List<CadastroListResponse> getAllCadastros() {
        log.info("[inicia] CadastroApplicationService - getAllCadastros");
        List<Cadastro> cadastros = cadastroRepository.getAllCadastros();
        log.info("[finaliza] CadastroApplicationService - getAllCadastros");
        return CadastroListResponse.converte(cadastros);
    }

    @Override
    public CadastroDetalhadoResponse getOneCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroApplicationService - getOneCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        log.info("[finaliza] CadastroApplicationService - getOneCadastro");
        return new CadastroDetalhadoResponse(cadastro);
    }

    @Override
    public void deleteCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroApplicationService - deleteCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        cadastroRepository.deleteCadastro(cadastro);
        log.info("[finaliza] CadastroApplicationService - deleteCadastro");
    }

    @Override
    public void updateCadastro(UUID idCadastro, CadastroAlteracaoRequest request) {
        log.info("[inicia] CadastroApplicationService - updateCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        validaAlteracaoCadastro(cadastro, request);
        cadastro.altera(request);
        cadastroRepository.saveCadastro(cadastro);
        log.info("[finaliza] CadastroApplicationService - updateCadastro");
    }

    @Override
    public void finalizaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroApplicationService - finalizaCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        cadastro.finalizaCadastro();
        cadastroRepository.saveCadastro(cadastro);
        log.info("[finaliza] CadastroApplicationService - finalizaCadastro");
    }

    @Override
    public void ativaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroApplicationService - ativaCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        cadastro.ativaCadastro();
        cadastroRepository.saveCadastro(cadastro);
        log.info("[finaliza] CadastroApplicationService - ativaCadastro");
    }

    @Override
    public void cancelaCadastro(UUID idCadastro) {
        log.info("[inicia] CadastroApplicationService - cancelaCadastro");
        Cadastro cadastro = cadastroRepository.getOneCadastro(idCadastro);
        cadastro.cancelaCadastro();
        cadastroRepository.saveCadastro(cadastro);
        log.info("[finaliza] CadastroApplicationService - cancelaCadastro");
    }
}