package br.com.sada.gerenciamento.financeiro.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaDto;
import br.com.sada.gerenciamento.financeiro.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		super();
		this.categoriaRepository = categoriaRepository;
	}

	public Categoria buscaPorId(int id) {
		return categoriaRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Categoria id:" + id + "não encontrada!"));
	}

	public Categoria inserirCategoria(CategoriaDto categoriaDto) {
		return categoriaRepository.save(categoriaDto.toCategoria());
	}

}
