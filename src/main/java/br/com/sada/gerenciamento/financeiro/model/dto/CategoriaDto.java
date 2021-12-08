package br.com.sada.gerenciamento.financeiro.model.dto;

import br.com.sada.gerenciamento.financeiro.model.Categoria;

public class CategoriaDto {

	private String nome;
	private String tipoMovimento;
	private double limite;
	
	public CategoriaDto(String nome, String tipoMovimento, double limite) {
		this.nome = nome;
		this.tipoMovimento = tipoMovimento;
		this.limite = limite;
	}

	public String getNome() {
		return nome;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public double getLimite() {
		return limite;
	}

	public Categoria toCategoria() {
		return new Categoria(nome, limite);
	}
}
