package br.com.apss.fazendaweb.enums;

public enum TipoProduto {

	REVENDA("REVENDA"),
	CONSUMO("CONSUMO"),
	SERVICO("SERVI�O");
	
	private String descricao;

	TipoProduto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}