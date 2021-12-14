package br.com.sada.gerenciamento.financeiro.model.dto;

import java.time.LocalDate;

public class BalancoDto {

	private LocalDate data;

	public BalancoDto() {
	}

	public BalancoDto(LocalDate data) {
		this.data = data;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

}
