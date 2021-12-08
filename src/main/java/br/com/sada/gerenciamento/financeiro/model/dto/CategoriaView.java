package br.com.sada.gerenciamento.financeiro.model.dto;

public class CategoriaView {

	private int id;
	private String nome;
	private double limite;

	public CategoriaView(int id, String nome, double limite) {
		this.id = id;
		this.nome = nome;
		this.limite = limite;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public double getLimite() {
		return limite;
	}

}
