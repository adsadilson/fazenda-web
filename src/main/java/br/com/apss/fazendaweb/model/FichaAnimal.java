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
import javax.persistence.Transient;

@Table(name = "ficha_animal")
@Entity
@SequenceGenerator(name = "FICHA_ANIMAL_ID", sequenceName = "FICHA_ANIMAL_SEQ", allocationSize = 1, initialValue = 1)
public class FichaAnimal implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "FICHA_ANIMAL_ID")
	private Long id;

	@Column(name = "tipo_lanc", length = 80)
	private String tipoLanc;

	@Column(length = 80)
	private String descricao;

	@Column(name = "tipo_cobertura", length = 80)
	private String tipoCobertura;

	@ManyToOne
	@JoinColumn(name = "tipo_secagem_id")
	private TipoSecagem tipoSecagem;

	@ManyToOne
	@JoinColumn(name = "tipo_parto_id")
	private TipoParto tipoParto;

	@ManyToOne
	@JoinColumn(name = "condicao_corporal_id")
	private CondicaoCorporal condicaoCorporal;

	@Column(length = 80)
	private String resultado;

	@Column(length = 200)
	private String obs;

	@Column(name = "obs_parto", length = 200)
	private String obsParto;

	@Column(name = "obs_cobertura", length = 200)
	private String obsCobertura;

	@Column(name = "obs_secagem", length = 200)
	private String obsSecagem;

	private Integer quantidade = 0;

	@Column(length = 80)
	private String unidade;

	@Column(length = 80)
	private String medicamento;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cobertura")
	private Date dtCobertura;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_lanc")
	private Date dtLanc;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_parto")
	private Date dtParto;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_cadastro")
	private Date dtCadastro;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_secagem")
	private Date dtSecagem;

	@Temporal(TemporalType.DATE)
	@Column(name = "dt_diagnostico")
	private Date dtDiagnostico;

	@Column(length = 10)
	private String sexo;

	@Column(length = 12)
	private String partida;

	@Column(length = 80)
	private String reprodutor;

	@Column(length = 80)
	private String responsavel;

	@ManyToOne
	@JoinColumn(name = "animal_id")
	private Animal animal;

	@Column(name = "nome_cria1", length = 80)
	private String nomeCria1;

	@Column(name = "nome_cria2", length = 80)
	private String nomeCria2;

	@Column(name = "brinco_cria1", length = 15)
	private String brincoCria1;

	@Column(name = "brinco_cria2", length = 15)
	private String brincoCria2;

	@Column(name = "genero_cria1", length = 15)
	private String generoCria1;

	@Column(name = "genero_cria2", length = 15)
	private String generoCria2;

	@Column(name = "peso1", length = 15)
	private String peso1;

	@Column(name = "peso2", length = 15)
	private String peso2;

	@ManyToOne
	@JoinColumn(name = "raca1_id")
	private Raca raca1;

	@ManyToOne
	@JoinColumn(name = "raca2_id")
	private Raca raca2;

	@ManyToOne
	@JoinColumn(name = "categoria1_id")
	private Categoria categoria1;

	@ManyToOne
	@JoinColumn(name = "categoria2_id")
	private Categoria categoria2;
	
	@Transient
	private Long lactacao;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoLanc() {
		return tipoLanc;
	}

	public void setTipoLanc(String tipoLanc) {
		this.tipoLanc = tipoLanc;
	}

	public String getTipoCobertura() {
		return tipoCobertura;
	}

	public void setTipoCobertura(String tipoCobertura) {
		this.tipoCobertura = tipoCobertura;
	}

	public Date getDtLanc() {
		return dtLanc;
	}

	public void setDtLanc(Date dtLanc) {
		this.dtLanc = dtLanc;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public TipoSecagem getTipoSecagem() {
		return tipoSecagem;
	}

	public void setTipoSecagem(TipoSecagem tipoSecagem) {
		this.tipoSecagem = tipoSecagem;
	}

	public String getObsSecagem() {
		return obsSecagem;
	}

	public void setObsSecagem(String obsSecagem) {
		this.obsSecagem = obsSecagem;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(String medicamento) {
		this.medicamento = medicamento;
	}

	public Date getDtCobertura() {
		return dtCobertura;
	}

	public void setDtCobertura(Date dtCobertura) {
		this.dtCobertura = dtCobertura;
	}

	public Date getDtParto() {
		return dtParto;
	}

	public void setDtParto(Date dtParto) {
		this.dtParto = dtParto;
	}

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	public Date getDtDiagnostico() {
		return dtDiagnostico;
	}

	public void setDtDiagnostico(Date dtDiagnostico) {
		this.dtDiagnostico = dtDiagnostico;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public CondicaoCorporal getCondicaoCorporal() {
		return condicaoCorporal;
	}

	public void setCondicaoCorporal(CondicaoCorporal condicaoCorporal) {
		this.condicaoCorporal = condicaoCorporal;
	}

	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public String getPartida() {
		return partida;
	}

	public void setPartida(String partida) {
		this.partida = partida;
	}

	public String getReprodutor() {
		return reprodutor;
	}

	public void setReprodutor(String reprodutor) {
		this.reprodutor = reprodutor;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public TipoParto getTipoParto() {
		return tipoParto;
	}

	public void setTipoParto(TipoParto tipoParto) {
		this.tipoParto = tipoParto;
	}

	public String getNomeCria1() {
		return nomeCria1;
	}

	public void setNomeCria1(String nomeCria1) {
		this.nomeCria1 = nomeCria1 == null ? null : nomeCria1.toUpperCase();
	}

	public String getNomeCria2() {
		return nomeCria2;
	}

	public void setNomeCria2(String nomeCria2) {
		this.nomeCria2 = nomeCria2 == null ? null : nomeCria2.toUpperCase();
	}

	public String getBrincoCria1() {
		return brincoCria1;
	}

	public void setBrincoCria1(String brincoCria1) {
		this.brincoCria1 = brincoCria1 == null ? null : brincoCria1.toUpperCase();
	}

	public String getBrincoCria2() {
		return brincoCria2;
	}

	public void setBrincoCria2(String brincoCria2) {
		this.brincoCria2 = brincoCria2 == null ? null : brincoCria2.toUpperCase();
	}

	public String getGeneroCria1() {
		return generoCria1;
	}

	public void setGeneroCria1(String generoCria1) {
		this.generoCria1 = generoCria1;
	}

	public String getGeneroCria2() {
		return generoCria2;
	}

	public void setGeneroCria2(String generoCria2) {
		this.generoCria2 = generoCria2;
	}

	public String getPeso1() {
		return peso1;
	}

	public void setPeso1(String peso1) {
		this.peso1 = peso1;
	}

	public String getPeso2() {
		return peso2;
	}

	public void setPeso2(String peso2) {
		this.peso2 = peso2;
	}

	public Raca getRaca1() {
		return raca1;
	}

	public void setRaca1(Raca raca1) {
		this.raca1 = raca1;
	}

	public Raca getRaca2() {
		return raca2;
	}

	public void setRaca2(Raca raca2) {
		this.raca2 = raca2;
	}

	public Categoria getCategoria1() {
		return categoria1;
	}

	public void setCategoria1(Categoria categoria1) {
		this.categoria1 = categoria1;
	}

	public Categoria getCategoria2() {
		return categoria2;
	}

	public void setCategoria2(Categoria categoria2) {
		this.categoria2 = categoria2;
	}

	public String getObsParto() {
		return obsParto;
	}

	public void setObsParto(String obsParto) {
		this.obsParto = obsParto;
	}

	public String getObsCobertura() {
		return obsCobertura;
	}

	public void setObsCobertura(String obsCobertura) {
		this.obsCobertura = obsCobertura;
	}

	public Date getDtSecagem() {
		return dtSecagem;
	}

	public void setDtSecagem(Date dtSecagem) {
		this.dtSecagem = dtSecagem;
	}
	
	

	public Long getLactacao() {
		return lactacao;
	}

	public void setLactacao(Long lactacao) {
		this.lactacao = lactacao;
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
		FichaAnimal other = (FichaAnimal) obj;
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
