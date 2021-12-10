package br.com.sada.gerenciamento.financeiro.model.dto;

import java.time.LocalDate;

import br.com.sada.gerenciamento.financeiro.model.Categoria;

public class MovimentoView {

	private int id;
	private double valor;
	private String tipoMovimento;
	private String detalhe;
	private LocalDate dataInclusao;
	private Categoria categoria;
	

	public MovimentoView(int id, double valor, String tipoMovimento, String detalhe, Categoria categoria,
			LocalDate dataInclusao) {
		this.id = id;
		this.valor = valor;
		this.tipoMovimento = tipoMovimento;
		this.detalhe = detalhe;
		this.categoria = categoria;
		this.dataInclusao = dataInclusao;
	}

	public int getId() {
		return id;
	}

	public double getValor() {
		return valor;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

}
