package br.com.sada.gerenciamento.financeiro.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;
import br.com.sada.gerenciamento.financeiro.service.BalancoService;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatorios", description = "Relatorios")
public class RelatorioController {
	
	private BalancoService balancoService;
	
	public RelatorioController(BalancoService balancoService) {
		this.balancoService = balancoService;
	}


	@PostMapping("/mensal")
	public ResponseEntity<BalancoView> balancoMensal(@RequestBody LocalDate data) {
		return ResponseEntity.ok(balancoService.balancoMensal(data));
	}
	
	@PostMapping("/semanal")
	public ResponseEntity<BalancoView> balancoSemanal(@RequestBody LocalDate data) {
		return ResponseEntity.ok(balancoService.balancoSemanal(data));
	}

	@PostMapping("/diario")
	public ResponseEntity<BalancoView> balancoDiario(@RequestBody LocalDate data) {
		return ResponseEntity.ok(balancoService.balancoDiario(data));
	}


}
