package br.com.apss.fazendaweb.enums;

public enum Genero {

	M("MACHO","M"),
	F("FEMIA","F");
	
	private String descricao;
	private String sigla;
	
	private Genero(String descricao, String sigla) {
		this.descricao = descricao;
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}