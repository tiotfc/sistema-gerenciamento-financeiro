package br.com.sada.gerenciamento.financeiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.exception.LimiteExcedidoException;
import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoView;
import br.com.sada.gerenciamento.financeiro.service.MovimentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/movimentos")
@Tag(description = "Movimentos", name = "Movimentos")
public class MovimentoController {

	private MovimentoService movimentoService;

	public MovimentoController(MovimentoService movimentoService) {
		this.movimentoService = movimentoService;
	}

	@Operation(summary = "Buscar um movimento por Id.")
	@GetMapping("/{id}")
	public ResponseEntity<MovimentoView> buscarPorId(@PathVariable int id) {
		Movimento movimento = movimentoService.buscarPorId(id);
		return ResponseEntity
				.ok(new MovimentoView(movimento.getId(), movimento.getValor(), movimento.getTipoMovimento(),
						movimento.getDetalhe(), movimento.getCategoria(), movimento.getDataInclusao()));
	}
	@Operation(summary = "Salvar um novo movimento.")
	@PostMapping
	public ResponseEntity<MovimentoView> inserirMovimento(@RequestBody MovimentoDto movimentoDto)
			throws LimiteExcedidoException {
		Movimento movimento = movimentoService.inserirMovimento(movimentoDto);
		return ResponseEntity
				.ok(new MovimentoView(movimento.getId(), movimento.getValor(), movimento.getTipoMovimento(),
						movimento.getDetalhe(), movimento.getCategoria(), movimento.getDataInclusao()));
	}
	
	@Operation(summary = "Buscar todos os movimentos no mes atual.")
	@GetMapping("/mes")
	public ResponseEntity<List<MovimentoView>> buscarMesAtual() {
		return ResponseEntity
				.ok(movimentoService
						.buscarMesAtual().stream().map(i -> new MovimentoView(i.getId(), i.getValor(),
								i.getTipoMovimento(), i.getDetalhe(), i.getCategoria(), i.getDataInclusao()))
						.collect(Collectors.toList()));
	}
	
	@Operation(summary = "Buscar todos os movimentos do mes atual por categoria.")
	@GetMapping("/mes/{categoriaId}")
	public ResponseEntity<List<MovimentoView>> buscarMesAtualCategoria(@PathVariable int categoriaId) {
		return ResponseEntity.ok(movimentoService
				.buscarMesAtualPorCategoria(categoriaId).stream().map(i -> new MovimentoView(i.getId(), i.getValor(),
						i.getTipoMovimento(), i.getDetalhe(), i.getCategoria(), i.getDataInclusao()))
				.collect(Collectors.toList()));
	}
	
//	@GetMapping("/balanco/") {
		
}
