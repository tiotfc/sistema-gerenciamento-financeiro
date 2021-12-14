package br.com.sada.gerenciamento.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.model.dto.BalancoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;
import br.com.sada.gerenciamento.financeiro.service.BalancoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/relatorios")
@Tag(name = "Relatorios", description = "Relatorios")
public class RelatorioController {
	
	private BalancoService balancoService;
	
	public RelatorioController(BalancoService balancoService) {
		this.balancoService = balancoService;
	}

	@Operation(summary = "Relatorio mensal de despesas. Recebe alguma data do mês desejado.")
	@PostMapping("/mensal")
	public ResponseEntity<BalancoView> balancoMensal(@RequestBody BalancoDto balancoDto) {
		return ResponseEntity.ok(balancoService.balancoMensal(balancoDto));
	}
	
	@Operation(summary = "Relatorio mensal de despesas. Recebe alguma data da semana desejada.")
	@PostMapping("/semanal")
	public ResponseEntity<BalancoView> balancoSemanal(@RequestBody BalancoDto balancoDto) {
		return ResponseEntity.ok(balancoService.balancoSemanal(balancoDto));
	}

	@Operation(summary = "Relatorio mensal de despesas. Recebe o dia desejado.")
	@PostMapping("/diario")
	public ResponseEntity<BalancoView> balancoDiario(@RequestBody BalancoDto balancoDto) {
		return ResponseEntity.ok(balancoService.balancoDiario(balancoDto));
	}


}
