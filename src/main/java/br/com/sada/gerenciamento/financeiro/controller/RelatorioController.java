package br.com.sada.gerenciamento.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;
import br.com.sada.gerenciamento.financeiro.service.BalancoService;


@RestController
@RequestMapping("/relatorios")
public class RelatorioController {
	
	private BalancoService balancoService;
	
	public RelatorioController(BalancoService balancoService) {
		this.balancoService = balancoService;
	}


	@GetMapping("/mensal")
	public ResponseEntity<BalancoView> balancoMensal() {
		return ResponseEntity.ok(balancoService.balancoMensal());
	}

}
