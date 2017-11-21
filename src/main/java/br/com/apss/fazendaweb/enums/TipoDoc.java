package br.com.apss.fazendaweb.enums;

public enum TipoDoc {

	RG("RG - REGISTRO GERAL"), 
	HABILITACAO("HABILITAÇÃO NANCIONAL");

	private String descricao;
	
	private TipoDoc(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}