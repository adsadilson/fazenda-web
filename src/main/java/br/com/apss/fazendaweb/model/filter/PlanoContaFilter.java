package br.com.apss.fazendaweb.model.filter;

import java.io.Serializable;

public class PlanoContaFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String conta;
	private Long contaPaiID;
	private String ativo;

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public Long getContaPaiID() {
		return contaPaiID;
	}

	public void setContaPaiID(Long contaPaiID) {
		this.contaPaiID = contaPaiID;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

}
