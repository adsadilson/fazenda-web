package br.com.apss.fazendaweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.apss.fazendaweb.enums.Genero;

@Entity
@Table(name = "animal")
@SequenceGenerator(name = "ANIMAL_ID", sequenceName = "ANIMAL_SEQ", allocationSize = 1)
public class Animal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "ANIMAL_ID")
	private Long id;

	@Column(name = "nome", nullable = true, length = 80)
	private String nome;

	@Column(name = "brinco", length = 10)
	private String brinco;

	@Enumerated(EnumType.STRING)
	@Column(name = "sexo", length = 20)
	private Genero genero;

	@ManyToOne
	@JoinColumn(name = "origem_id", nullable = true)
	private Origem origem;

	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = true)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "raca_id", nullable = true)
	private Raca raca;

	@ManyToOne
	@JoinColumn(name = "pelagem_id", nullable = true)
	private Pelagem pelagem;

	@Column(name = "rgn", length = 15)
	private String rgn;

	@Column(name = "rgd", length = 15)
	private String rgd;

	@Column(name = "mae", length = 80)
	private String mae;

	@Column(name = "pai", length = 80)
	private String pai;

	@Column(name = "obs", length = 255)
	private String obs;

	@Temporal(TemporalType.DATE)
	@Column(name = "nascimento")
	private Date nascimento;

	@Column(name = "status", length = 1)
	private Boolean status;
	
	@OneToMany(mappedBy = "animal", fetch = FetchType.LAZY)
	private List<FichaAnimal> fichaAnimals = new ArrayList<>();
	
	@Transient
	private Long fid;
	
	public Long getFid() {
		return fid;
	}

	public void setFid(Long fid) {
		this.fid = fid;
	}

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

	public String getBrinco() {
		return brinco;
	}

	public void setBrinco(String brinco) {
		this.brinco =  brinco == null ? null : brinco.toUpperCase();
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Origem getOrigem() {
		return origem;
	}

	public void setOrigem(Origem origem) {
		this.origem = origem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Raca getRaca() {
		return raca;
	}

	public void setRaca(Raca raca) {
		this.raca = raca;
	}

	public Pelagem getPelagem() {
		return pelagem;
	}

	public void setPelagem(Pelagem pelagem) {
		this.pelagem = pelagem;
	}

	public String getRgn() {
		return rgn;
	}

	public void setRgn(String rgn) {
		this.rgn = rgn;
	}

	public String getRgd() {
		return rgd;
	}

	public void setRgd(String rgd) {
		this.rgd = rgd;
	}

	public String getMae() {
		return mae;
	}

	public void setMae(String mae) {
		this.mae = mae == null ? null : mae.toUpperCase();
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai =  pai == null ? null : pai.toUpperCase();
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	public List<FichaAnimal> getFichaAnimals() {
		return fichaAnimals;
	}

	public void setFichaAnimals(List<FichaAnimal> fichaAnimals) {
		this.fichaAnimals = fichaAnimals;
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
		Animal other = (Animal) obj;
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
