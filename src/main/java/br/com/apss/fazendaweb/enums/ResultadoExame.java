package br.com.apss.fazendaweb.enums;

public enum ResultadoExame {
	
	AGUARDANDO("AGUARDANDO"),
	POSITIVO("POSITIVO"), 
	NEGATIVO("NEGATIVO");

	private String descricao;
	
	private ResultadoExame(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}