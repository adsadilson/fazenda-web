package br.com.apss.fazendaweb.model;

import java.io.Serializable;
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

import br.com.apss.fazendaweb.enums.ResultadoExame;

@Table(name = "exame")
@Entity
@SequenceGenerator(name = "EXAME_ID", sequenceName = "EXAME_SEQ", allocationSize = 1, initialValue = 1)
public class Exame implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EXAME_ID")
	private Long id;

	@Column(length = 200)
	private String obs;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_exame")
	private Date dtExame;

	@ManyToOne
	@JoinColumn(name = "tipo_exame_id")
	private TipoExame tipoExame;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@Enumerated(EnumType.STRING)
	@Column(name = "resultado")
	private ResultadoExame resultado;

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

	public Date getDtExame() {
		return dtExame;
	}

	public void setDtExame(Date dtExame) {
		this.dtExame = dtExame;
	}

	public TipoExame getTipoExame() {
		return tipoExame;
	}

	public void setTipoExame(TipoExame tipoExame) {
		this.tipoExame = tipoExame;
	}

	public ResultadoExame getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoExame resultado) {
		this.resultado = resultado;
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
		Exame other = (Exame) obj;
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
