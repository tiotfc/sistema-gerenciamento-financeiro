package br.com.sada.gerenciamento.financeiro.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoDto;
import br.com.sada.gerenciamento.financeiro.repository.MovimentoRepository;

@Service
public class MovimentoService {

	private MovimentoRepository movimentoRepository;
	private CategoriaService categoriaService;

	public MovimentoService(MovimentoRepository movimentoRepository, CategoriaService categoriaService) {
		this.movimentoRepository = movimentoRepository;
		this.categoriaService = categoriaService;
	}

	public Movimento buscarPorId(int id) {
		return movimentoRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Movimento id:" + id + "não encontrada!"));
	}

	public Movimento inserirMovimento(MovimentoDto movimentoDto) {
		return movimentoRepository
				.save(movimentoDto.toMovimento(categoriaService.buscaPorId(movimentoDto.getCategoriaId())));
	}

}
