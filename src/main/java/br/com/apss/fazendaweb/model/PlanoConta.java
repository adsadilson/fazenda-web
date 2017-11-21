package br.com.apss.fazendaweb.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "plano_conta")
@SequenceGenerator(name = "PLANO_CONTA_ID", sequenceName = "PLANO_CONTA_SEQ", allocationSize = 1)
public class PlanoConta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "PLANO_CONTA_ID")
	private Long id;

	@Column(name = "codigo_acesso", nullable = false, length = 30)
	private String codigoAcesso;

	@Column(name = "mascara", length = 30)
	private String mascara;

	@Column(name = "conta", length = 100)
	private String conta;

	@ManyToOne
	@JoinColumn(name = "conta_pai_id")
	private PlanoConta contaPai;

	@Column(name = "permite_lancamento", length = 80)
	private String permiteLanc;

	@Column(name = "status", nullable = false, length = 10)
	private Boolean status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoAcesso() {
		return codigoAcesso;
	}

	public void setCodigoAcesso(String codigoAcesso) {
		this.codigoAcesso = codigoAcesso;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta.toUpperCase();
	}

	public PlanoConta getContaPai() {
		return contaPai;
	}

	public void setContaPai(PlanoConta contaPai) {
		this.contaPai = contaPai;
	}

	public String getPermiteLanc() {
		return permiteLanc;
	}

	public void setPermiteLanc(String permiteLanc) {
		this.permiteLanc = permiteLanc;
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
		PlanoConta other = (PlanoConta) obj;
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
