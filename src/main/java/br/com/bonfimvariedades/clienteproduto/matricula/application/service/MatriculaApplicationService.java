package br.com.bonfimvariedades.clienteproduto.matricula.application.service;

import br.com.bonfimvariedades.clienteproduto.cliente.application.repository.ClienteRepository;
import br.com.bonfimvariedades.clienteproduto.cliente.domain.Cliente;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaAlteracaoRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.request.MatriculaRequest;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaDetalhadoResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaIdResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.api.response.MatriculaListResponse;
import br.com.bonfimvariedades.clienteproduto.matricula.application.repository.MatriculaRepository;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
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

import static br.com.bonfimvariedades.clienteproduto.matricula.annotation.constraints.Valid.validaAlteracaoMatricula;
import static br.com.bonfimvariedades.clienteproduto.matricula.annotation.constraints.Valid.validaSolicitacao;

@Service
@Log4j2
@RequiredArgsConstructor
public class MatriculaApplicationService implements MatriculaService{
    private final MatriculaRepository matriculaRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PagamentoService pagamentoService;
    private final OrcamentoRepository orcamentoRepository;

    @Override
    public MatriculaIdResponse saveMatricula(MatriculaRequest matriculaRequest) {
        log.info("[inicia] MatriculaApplicationService - saveMatricula");
        Produto produto = produtoRepository.getOneProduto(matriculaRequest.getIdProduto());
        validaSolicitacao(matriculaRequest, produto);
        Cliente cliente = clienteRepository.buscaClienteAtravesId(matriculaRequest.getIdCliente());
        Matricula matricula = matriculaRepository.saveMatricula(new Matricula(cliente, produto,matriculaRequest));
        if (matriculaRequest.getValorEntrada().compareTo(BigDecimal.ZERO)>0){
            Pagamento pagamento = pagamentoService.savePagamentoByEntrada(matricula, TipoPagamento.valueOf(matriculaRequest.getTipoPagamentoEntrada()));
        }
        log.info("[finaliza] MatriculaApplicationService - saveMatricula");
        return MatriculaIdResponse.builder().idMatricula(matricula.getIdMatricula()).build();
    }

    @Override
    public MatriculaIdResponse saveMatriculaByOrcamento(String cpf) {
        log.info("[inicia] MatriculaApplicationService - saveMatriculaByOrcamento");
        Orcamento orcamento = orcamentoRepository.getOneOrcamentoByCpf(cpf);
        Matricula matricula = matriculaRepository.saveMatricula(new Matricula(orcamento));
        orcamentoRepository.deleteOrcamento(orcamento.getIdOrcamento());
        log.info("[finaliza] MatriculaApplicationService - saveMatriculaByOrcamento");
        return MatriculaIdResponse.builder().idMatricula(matricula.getIdMatricula()).build();
    }

    @Override
    public List<MatriculaListResponse> getAllMatriculas() {
        log.info("[inicia] MatriculaApplicationService - getAllMatriculas");
        List<Matricula> matriculas = matriculaRepository.getAllMatriculas();
        log.info("[finaliza] MatriculaApplicationService - getAllMatriculas");
        return MatriculaListResponse.converte(matriculas);
    }

    @Override
    public MatriculaDetalhadoResponse getOneMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaApplicationService - getOneMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        log.info("[finaliza] MatriculaApplicationService - getOneMatricula");
        return new MatriculaDetalhadoResponse(matricula);
    }

    @Override
    public void deleteMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaApplicationService - deleteMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        matriculaRepository.deleteMatricula(matricula);
        log.info("[finaliza] MatriculaApplicationService - deleteMatricula");
    }

    @Override
    public void updateMatricula(UUID idMatricula, MatriculaAlteracaoRequest request) {
        log.info("[inicia] MatriculaApplicationService - updateMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        validaAlteracaoMatricula(matricula, request);
        matricula.altera(request);
        matriculaRepository.saveMatricula(matricula);
        log.info("[finaliza] MatriculaApplicationService - updateMatricula");
    }

    @Override
    public void finalizaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaApplicationService - finalizaMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        matricula.finalizaMatricula();
        matriculaRepository.saveMatricula(matricula);
        log.info("[finaliza] MatriculaApplicationService - finalizaMatricula");
    }

    @Override
    public void ativaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaApplicationService - ativaMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        matricula.ativaMatricula();
        matriculaRepository.saveMatricula(matricula);
        log.info("[finaliza] MatriculaApplicationService - ativaMatricula");
    }

    @Override
    public void cancelaMatricula(UUID idMatricula) {
        log.info("[inicia] MatriculaApplicationService - cancelaMatricula");
        Matricula matricula = matriculaRepository.getOneMatricula(idMatricula);
        matricula.cancelaMatricula();
        matriculaRepository.saveMatricula(matricula);
        log.info("[finaliza] MatriculaApplicationService - cancelaMatricula");
    }
}