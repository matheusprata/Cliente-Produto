package br.com.bonfimvariedades.clienteproduto.pagamento.infra;

import br.com.bonfimvariedades.clienteproduto.pedido.domain.Pedido;
import br.com.bonfimvariedades.clienteproduto.pedido.domain.TipoPagamento;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoSpringDataJPARepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByPedido(Pedido pedido);
    List<Pagamento> findByDataPagamento(LocalDate data);
    List<Pagamento> findByTipoPagamentoAndDataPagamento(TipoPagamento tipoPagamento, LocalDate data);
}