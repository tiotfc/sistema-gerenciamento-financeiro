package br.com.sada.gerenciamento.financeiro.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaDto;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaView;
import br.com.sada.gerenciamento.financeiro.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/categorias")
@Tag(description = "Categorias", name = "Categorias")
public class CategoriaController {

	private CategoriaService categoriaService;

	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}

	@Operation(summary = "Buscar categorias por Id.")
	@GetMapping("/{id}")
	public ResponseEntity<CategoriaView> buscarPorId(@PathVariable int id) {

		Categoria categoria = categoriaService.buscaPorId(id);
		return ResponseEntity.ok(new CategoriaView(categoria.getId(), categoria.getNome(), categoria.getLimite()));

	}
	@Operation(summary = "Salvar uma nova categoria.")
	@PostMapping
	public ResponseEntity<CategoriaView> inserirCategoria(@RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaService.inserirCategoria(categoriaDto);
		return ResponseEntity.ok(new CategoriaView(categoria.getId(), categoria.getNome(), categoria.getLimite()));

	}
	@Operation(summary = "Listar todas as categorias.")
	@GetMapping
	public ResponseEntity<List<Categoria>> listaCategoria() {
		List<Categoria> bustarTodos = categoriaService.bustarTodos();
		return ResponseEntity.ok(bustarTodos);		
	}

}
