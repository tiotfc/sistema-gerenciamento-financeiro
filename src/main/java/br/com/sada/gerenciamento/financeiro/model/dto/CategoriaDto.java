package br.com.sada.gerenciamento.financeiro.model.dto;

import br.com.sada.gerenciamento.financeiro.model.Categoria;

public class CategoriaDto {

	private String nome;
	private double limite;
	
	public CategoriaDto(String nome, double limite) {
		this.nome = nome;
		this.limite = limite;
	}

	public String getNome() {
		return nome;
	}

	public double getLimite() {
		return limite;
	}

	public Categoria toCategoria() {
		return new Categoria(nome, limite);
	}
}
