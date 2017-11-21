package br.com.apss.fazendaweb.util;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("meuBean")
@SessionScoped
public class PrecoProdutoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private CalculadoraPreco calculadora;
	
	@PostConstruct
	public void init() {
		System.out.println("Init PrecoProdutoBean");
		System.out.println("Nome: "+getNome());
	}
	
	public double getPreco() {
		//System.out.println(calculadora.getClass());
		return calculadora.calcularPreco(12, 45.32);
	}

	public String getNome() {
		return "paraguai";
	}

	
	

}
