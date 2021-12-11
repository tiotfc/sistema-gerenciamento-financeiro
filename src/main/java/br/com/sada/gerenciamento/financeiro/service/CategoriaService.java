package br.com.sada.gerenciamento.financeiro.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaDto;
import br.com.sada.gerenciamento.financeiro.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria buscaPorId(int id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Categoria id:" + id + "não encontrada!"));
	}

	public Categoria inserirCategoria(CategoriaDto categoriaDto) {
		return categoriaRepository.save(categoriaDto.toCategoria());
	}
	
	public List<Categoria> bustarTodos() {
		return categoriaRepository.findAll();
	}

	public Categoria atualizaCategoria(int id, CategoriaDto categoriaDto) {
		Categoria categoria = buscaPorId(id);
		categoria.setLimite(categoriaDto.getLimite());
		categoria.setNome(categoriaDto.getNome());
	
		return categoriaRepository.save(categoria);
	}

	public void deletaCategoria(int id) {
		categoriaRepository.deleteById(id);
	}
	
}
