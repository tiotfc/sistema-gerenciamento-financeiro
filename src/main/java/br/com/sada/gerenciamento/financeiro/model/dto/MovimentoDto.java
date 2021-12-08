package br.com.sada.gerenciamento.financeiro.model.dto;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.Movimento;

public class MovimentoDto {

	private double valor;
	private String tipoMovimento;
	private String detalhe;
	private int categoriaId;

	public MovimentoDto(double valor, String tipoMovimento, String detalhe, int categoriaId) {
		this.valor = valor;
		this.tipoMovimento = tipoMovimento;
		this.detalhe = detalhe;
		this.categoriaId = categoriaId;
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

	public int getCategoriaId() {
		return categoriaId;
	}

	public Movimento toMovimento(Categoria categoria) {
		return new Movimento(valor, tipoMovimento, detalhe, categoria);
	}
	
}
