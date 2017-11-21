package br.com.apss.fazendaweb.enums;

public enum EstadoCivil {

	SOLTEIRO("SOLTEIRO(A)"),
	CASADO("CASADO(A)"),
	DESQUITADO("DESQUITADO(A)"),
	DIVORCIADO("DIVORCIADO(A)"),
	VIUVO("VIUVO(A)"),
	UNIAO_ESTAVEL("UNI�O ESTAVEL");
	
	private String descricao;
	
	private EstadoCivil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


}