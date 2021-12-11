package br.com.sada.gerenciamento.financeiro.model.dto;

import java.util.List;

import br.com.sada.gerenciamento.financeiro.model.Movimento;

public class BalancoView {

	private List<Movimento> listaDebitos;
	private List<Movimento> listaCreditos;
	private double total;

	public BalancoView(List<Movimento> listaDebitos, List<Movimento> listaCreditos, double total) {
		this.listaDebitos = listaDebitos;
		this.listaCreditos = listaCreditos;
		this.total = total;
	}

	public List<Movimento> getListaDebitos() {
		return listaDebitos;
	}

	public List<Movimento> getListaCreditos() {
		return listaCreditos;
	}

	public double getTotal() {
		return total;
	}

}
