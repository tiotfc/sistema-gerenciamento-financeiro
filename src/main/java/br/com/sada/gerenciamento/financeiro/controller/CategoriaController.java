package br.com.sada.gerenciamento.financeiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

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
	public ResponseEntity<CategoriaView> inserirCategoria(@RequestBody CategoriaDto categoriaDto,
			UriComponentsBuilder uriBuilder) {
		Categoria categoria = categoriaService.inserirCategoria(categoriaDto);
		CategoriaView categoriaView = new CategoriaView(categoria.getId(), categoria.getNome(), categoria.getLimite());
		return ResponseEntity.created(uriBuilder.path("categorias/{id}").buildAndExpand(categoriaView.getId()).toUri())
				.build();

	}

	@Operation(summary = "Listar todas as categorias.")
	@GetMapping
	public ResponseEntity<List<CategoriaView>> listaCategoria() {
		List<Categoria> listaCategorias = categoriaService.listarCategoria();

		return ResponseEntity.ok(listaCategorias.stream()
				.map(c -> new CategoriaView(c.getId(), c.getNome(), c.getLimite())).collect(Collectors.toList()));
	}

	@Operation(summary = "Atualizar uma categoria.")
	@PutMapping("/{id}")
	public ResponseEntity<CategoriaView> atualizaCategoria(@PathVariable int id,
			@RequestBody CategoriaDto categoriaDto) {
		Categoria categoria = categoriaService.atualizaCategoria(id, categoriaDto);
		return ResponseEntity.ok(new CategoriaView(categoria.getId(), categoria.getNome(), categoria.getLimite()));
	}

	@Operation(summary = "Remove uma categoria(somente se não estiver sendo utilizada).")
	@DeleteMapping("/{id}")
	public void deletaCategoria(@PathVariable int id) {
		categoriaService.deletaCategoria(id);
	}

}
