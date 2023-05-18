package br.com.bonfimvariedades.clienteproduto.compra.domain;

import br.com.bonfimvariedades.clienteproduto.compra.application.api.CompraRequest.CompraRequest;
import br.com.bonfimvariedades.clienteproduto.estoque.domain.Estoque;
import br.com.bonfimvariedades.clienteproduto.fornecedor.domain.Fornecedor;
import br.com.bonfimvariedades.clienteproduto.funcionario.domain.Funcionario;
import br.com.bonfimvariedades.clienteproduto.pagamento.domain.TipoPagamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCompra;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra")
    @JsonIgnore
    private List<Fornecedor> fornecedores;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra")
    @JsonIgnore
    private List<Funcionario> funcionarios;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compra")
    @JsonIgnore
    private List<Estoque> estoques;

    @NotNull(message = "Campo Numero Pedido Obrigatório!")
    private String numeroPedido;
    @NotBlank(message = "Campo Obrigatório!")
    private LocalDate dataEmissaoPedido;
    private LocalDate previsaoEntrega;
    private BigDecimal valorTotalPedido;

    public Compra(Fornecedor fornecedor,Funcionario funcionario, Estoque estoque, CompraRequest request){
        this.fornecedores = request.getIdFornecedor();
        this.funcionarios = getFuncionarios();
        this.estoques = getEstoques();
        this.numeroPedido = getNumeroPedido();
        this. =

    }
}
