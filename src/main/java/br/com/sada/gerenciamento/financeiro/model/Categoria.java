package br.com.sada.gerenciamento.financeiro.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private double limite;
	
	@OneToMany(mappedBy = "categoria")
	@JsonIgnore
	private List<Movimento> movimento;

	public Categoria(String nome, double limite) {
		this.nome = nome;
		this.limite = limite;
	}

	public Categoria() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getLimite() {
		return limite;
	}

	public void setLimite(double limite) {
		this.limite = limite;
	}

	public List<Movimento> getMovimento() {
		return movimento;
	}

	public void setMovimento(List<Movimento> movimento) {
		this.movimento = movimento;
	}

}
