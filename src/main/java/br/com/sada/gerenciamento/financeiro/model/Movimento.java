package br.com.sada.gerenciamento.financeiro.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movimento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private double valor;
	private String tipoMovimento;
	private String detalhe;
	private LocalDate dataInclusao;
	
	@ManyToOne
	private Categoria categoria;

	public Movimento() {
	}

	public Movimento(double valor, String tipoMovimento, String detalhe, Categoria categoria) {
		this.valor = valor;
		this.tipoMovimento = tipoMovimento;
		this.detalhe = detalhe;
		this.dataInclusao = LocalDate.now();
		this.categoria = categoria;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipoMovimento() {
		return tipoMovimento;
	}

	public void setTipoMovimento(String tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}

	public String getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}

	public LocalDate getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(LocalDate dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	
}
