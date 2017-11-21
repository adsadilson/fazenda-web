package br.com.apss.fazendaweb.model;

import java.io.Serializable;
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

@Table(name = "vacina")
@Entity
@SequenceGenerator(name = "VACINA_ID", sequenceName = "VACINA_SEQ", allocationSize = 1, initialValue = 1)
public class Vacina implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "VACINA_ID")
	private Long id;

	@Column(length = 200)
	private String obs;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_vacina")
	private Date dtVacina;

	@ManyToOne
	@JoinColumn(name = "tipo_vacina_id")
	private TipoVacina tipoVacina;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@ManyToOne
	@JoinColumn(name = "responsavel_id")
	private Pessoa responsavel;

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

	public Date getDtVacina() {
		return dtVacina;
	}

	public void setDtVacina(Date dtVacina) {
		this.dtVacina = dtVacina;
	}

	public TipoVacina getTipoVacina() {
		return tipoVacina;
	}

	public void setTipoVacina(TipoVacina tipoVacina) {
		this.tipoVacina = tipoVacina;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
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
		Vacina other = (Vacina) obj;
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
