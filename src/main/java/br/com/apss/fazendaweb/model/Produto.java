package br.com.apss.fazendaweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;

import br.com.apss.fazendaweb.enums.TipoProduto;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "PRODUTO_ID", sequenceName = "PRODUTO_SEQ", allocationSize = 1)
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PRODUTO_ID")
	private Long id;

	@Column(name = "nome", nullable = false, length = 80)
	private String nome;

	@Column(name = "codigo_barra", length = 15)
	private String codigoBarra;

	@ManyToOne
	@JoinColumn(name = "fornecedor_id")
	private Pessoa fornecedor;

	@ManyToOne
	@JoinColumn(name = "unidade_medida_id")
	private UnidadeMedida unidadeMedia;

	@ManyToOne
	@JoinColumn(name = "grupo_produto_id")
	private GrupoProduto grupoProduto;

	@Column(name = "tipo_produto", length = 10)
	@Enumerated(EnumType.STRING)
	private TipoProduto tipoProduto;

	@Column(name = "quantidade", precision = 12, scale = 2)
	private BigDecimal quantidade = BigDecimal.ZERO;

	@Column(name = "qtd_minima", precision = 12, scale = 2)
	private BigDecimal qtdMinma = BigDecimal.ZERO;

	@Column(name = "qtd_maxima", precision = 12, scale = 2)
	private BigDecimal qtdMaxima = BigDecimal.ZERO;

	@DecimalMin(value="0.01", message="Valor de Custo precisa ser acima de 0.00")
	@Column(name = "vlr_custo", precision = 12, scale = 2)
	private BigDecimal vlrCusto;

	@DecimalMin(value="0.01", message="Valor de Venda precisa ser acima de 0.00")
	@Column(name = "vlr_venda", precision = 12, scale = 2)
	private BigDecimal vlrVenda;

	@Column(name = "marg_lucro", precision = 12, scale = 2)
	private BigDecimal margLucro;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_ultima_compra")
	private Date dtUltimaCompra;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_ultima_venda")
	private Date dtUltimaVenda;

	@Column(name = "status", length = 1)
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toUpperCase();
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Pessoa getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Pessoa fornecedor) {
		this.fornecedor = fornecedor;
	}

	public UnidadeMedida getUnidadeMedia() {
		return unidadeMedia;
	}

	public void setUnidadeMedia(UnidadeMedida unidadeMedia) {
		this.unidadeMedia = unidadeMedia;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
	}

	public TipoProduto getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getQtdMinma() {
		return qtdMinma;
	}

	public void setQtdMinma(BigDecimal qtdMinma) {
		this.qtdMinma = qtdMinma;
	}

	public BigDecimal getQtdMaxima() {
		return qtdMaxima;
	}

	public void setQtdMaxima(BigDecimal qtdMaxima) {
		this.qtdMaxima = qtdMaxima;
	}

	public BigDecimal getVlrCusto() {
		return vlrCusto;
	}

	public void setVlrCusto(BigDecimal vlrCusto) {
		this.vlrCusto = vlrCusto;
	}

	public BigDecimal getVlrVenda() {
		return vlrVenda;
	}

	public void setVlrVenda(BigDecimal vlrVenda) {
		this.vlrVenda = vlrVenda;
	}

	public BigDecimal getMargLucro() {
		return margLucro;
	}

	public void setMargLucro(BigDecimal margLucro) {
		this.margLucro = margLucro;
	}

	public Date getDtUltimaCompra() {
		return dtUltimaCompra;
	}

	public void setDtUltimaCompra(Date dtUltimaCompra) {
		this.dtUltimaCompra = dtUltimaCompra;
	}

	public Date getDtUltimaVenda() {
		return dtUltimaVenda;
	}

	public void setDtUltimaVenda(Date dtUltimaVenda) {
		this.dtUltimaVenda = dtUltimaVenda;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

}
