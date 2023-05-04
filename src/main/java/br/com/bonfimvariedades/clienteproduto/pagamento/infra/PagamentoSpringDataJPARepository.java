package br.com.bonfimvariedades.clienteproduto.pagamento.infra;

import br.com.bonfimvariedades.clienteproduto.matricula.domain.Matricula;
import br.com.bonfimvariedades.clienteproduto.matricula.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoSpringDataJPARepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByMatricula(Matricula matricula);
    List<Pagamento> findByDataPagamento(LocalDate data);
    List<Pagamento> findByTipoPagamentoAndDataPagamento(TipoPagamento tipoPagamento, LocalDate data);
}