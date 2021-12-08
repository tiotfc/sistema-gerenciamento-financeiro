package br.com.sada.gerenciamento.financeiro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoView;
import br.com.sada.gerenciamento.financeiro.service.MovimentoService;

@RestController
@RequestMapping("/movimentos")
public class MovimentoController {

	private MovimentoService movimentoService;

	public MovimentoController(MovimentoService movimentoService) {
		super();
		this.movimentoService = movimentoService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<MovimentoView> buscarPorId(@PathVariable int id) {
		Movimento movimento = movimentoService.buscarPorId(id);
		return ResponseEntity.ok(new MovimentoView(movimento.getId(), movimento.getValor(),
				movimento.getTipoMovimento(), movimento.getDetalhe(), movimento.getCategoria()));
	}
	
	@PostMapping
	public ResponseEntity<MovimentoView> inserirMovimento(@RequestBody MovimentoDto movimentoDto) {
		Movimento movimento = movimentoService.inserirMovimento(movimentoDto);
		return ResponseEntity.ok(new MovimentoView(movimento.getId(), movimento.getValor(), movimento.getTipoMovimento(), movimento.getDetalhe(), movimento.getCategoria()));
		
	}

}
