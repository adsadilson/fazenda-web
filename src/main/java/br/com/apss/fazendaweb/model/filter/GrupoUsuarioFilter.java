package br.com.apss.fazendaweb.model.filter;

import java.io.Serializable;

public class GrupoUsuarioFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idDe;
	private Long idAte;
	private String nome;
	private String ativo;

	public Long getIdDe() {
		return idDe;
	}

	public void setIdDe(Long idDe) {
		this.idDe = idDe;
	}

	public Long getIdAte() {
		return idAte;
	}

	public void setIdAte(Long idAte) {
		this.idAte = idAte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome.toLowerCase();
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}


}
