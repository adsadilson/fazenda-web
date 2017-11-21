package br.com.apss.fazendaweb.enums;

public enum AtivoInativo {

	ATIVO("ATIVO"),
	INATIVO("INATIVO");
	
	private String descricao;

	AtivoInativo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}