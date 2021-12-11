package br.com.sada.gerenciamento.financeiro.service;

import java.time.LocalDate;
import java.util.List;

import javax.naming.LimitExceededException;
import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.exception.LimiteExcedidoException;
import br.com.sada.gerenciamento.financeiro.model.Categoria;
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

	public Movimento inserirMovimento(MovimentoDto movimentoDto) throws LimiteExcedidoException {
		if (movimentoDto.getTipoMovimento() == "D") {
			List<Movimento> listaNesteMesPorCategoria = buscarMesAtualPorCategoria(movimentoDto.getCategoriaId());
			String nomeCategoria = listaNesteMesPorCategoria.get(0).getCategoria().getNome();
			double limite = listaNesteMesPorCategoria.get(0).getCategoria().getLimite();
			double sum = listaNesteMesPorCategoria.stream().mapToDouble(m -> m.getValor()).sum();

			if (sum >= limite) {
				throw new LimiteExcedidoException(
						"Limite de: " + limite + " para categoria: " + nomeCategoria + " atingido!");
			}
		}
		return movimentoRepository
				.save(movimentoDto.toMovimento(categoriaService.buscaPorId(movimentoDto.getCategoriaId())));
	}

	public List<Movimento> buscarMesAtual() {
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
		List<Movimento> findByDataInclusaoBetween = movimentoRepository.findByDataInclusaoBetween(firstDayOfMonth,
				lastDayOfMonth);
		return findByDataInclusaoBetween;
	}

	public List<Movimento> buscarMesAtualPorCategoria(int categoriaId) {
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
	
		List<Movimento> findByDataInclusaoBetween = buscaEntreDatasPorCategoria(
				firstDayOfMonth, lastDayOfMonth, categoriaId);
		return findByDataInclusaoBetween;
	}
	
	public List<Movimento> buscaEntreDatasPorCategoria(LocalDate dataIni, LocalDate dataFinal, int categoriaId) {
		List<Movimento> findByDataInclusaoBetween = movimentoRepository.findByDataInclusaoBetweenAndCategoria(
				dataIni, dataFinal, categoriaService.buscaPorId(categoriaId));
		return findByDataInclusaoBetween;
	}
}
