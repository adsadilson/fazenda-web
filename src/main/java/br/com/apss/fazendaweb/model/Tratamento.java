package br.com.apss.fazendaweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "tratamento")
@Entity
@SequenceGenerator(name = "TRATAMENTO_ID", sequenceName = "TRATAMENTO_SEQ", allocationSize = 1, initialValue = 1)
public class Tratamento implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "TRATAMENTO_ID")
	private Long id;

	@Column(length = 200)
	private String obs;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_tratamento")
	private Date dtTratamento;

	@ManyToOne
	@JoinColumn(name = "tipo_tratamento_id")
	private TipoTratamento tipoTratamento;

	@ManyToOne
	@JoinColumn(name = "tipo_aplicacao_id")
	private TipoAplicacao tipoAplicacao;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@Column(name = "medicamento")
	private String medicamento;

	@Column(name = "quantidade", precision = 12, scale = 2)
	private BigDecimal quantidade;

	@ManyToOne
	@JoinColumn(name = "unidade_id")
	private UnidadeMedida unidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Date getDtTratamento() {
		return dtTratamento;
	}

	public void setDtTratamento(Date dtTratamento) {
		this.dtTratamento = dtTratamento;
	}

	public TipoTratamento getTipoTratamento() {
		return tipoTratamento;
	}

	public void setTipoTratamento(TipoTratamento tipoTratamento) {
		this.tipoTratamento = tipoTratamento;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento.toUpperCase();
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public UnidadeMedida getUnidade() {
		return unidade;
	}

	public void setUnidade(UnidadeMedida unidade) {
		this.unidade = unidade;
	}

	public TipoAplicacao getTipoAplicacao() {
		return tipoAplicacao;
	}

	public void setTipoAplicacao(TipoAplicacao tipoAplicacao) {
		this.tipoAplicacao = tipoAplicacao;
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
		Tratamento other = (Tratamento) obj;
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
