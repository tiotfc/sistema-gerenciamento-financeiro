package br.com.sada.gerenciamento.financeiro.service;

import java.time.DayOfWeek;
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
				.orElseThrow(() -> new EntityNotFoundException("Movimento id: " + id + " não encontrada!"));
	}

	public Movimento salvaMovimento(MovimentoDto movimentoDto) throws LimiteExcedidoException {
		Categoria categoria = categoriaService.buscaPorId(movimentoDto.getCategoriaId());
		regraLimite(movimentoDto, categoria);
		
		return movimentoRepository
				.save(movimentoDto.toMovimento(categoria));
	}

	public List<Movimento> buscarMovimentoMesPorData(LocalDate data) {
		LocalDate lastDayOfMonth = data.withDayOfMonth(LocalDate.now().lengthOfMonth());
		LocalDate firstDayOfMonth = data.withDayOfMonth(1);
		List<Movimento> listaMovimentosMes = movimentoRepository.findByDataInclusaoBetween(firstDayOfMonth,
				lastDayOfMonth);
		return listaMovimentosMes;
	}
	
	public List<Movimento> buscarMovimentoSemanaPorData(LocalDate data) {
		LocalDate firstDayOfWeek = data.with(DayOfWeek.MONDAY);
		LocalDate lastDayOfWeek = data.with(DayOfWeek.SUNDAY);
		System.out.println(firstDayOfWeek);
		System.out.println(lastDayOfWeek);
		
		List<Movimento> listaMovimentosSemana = movimentoRepository.findByDataInclusaoBetween(firstDayOfWeek,
				lastDayOfWeek);
		return listaMovimentosSemana;
	}
	
	public List<Movimento> buscarMovimentoDia(LocalDate data) {
		List<Movimento> listaMovimentosDia = movimentoRepository.findByDataInclusao(data);
		return listaMovimentosDia;
	}


	public List<Movimento> buscarMesAtualPorCategoria(int categoriaId) {
		LocalDate lastDayOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());
		LocalDate firstDayOfMonth = LocalDate.now().withDayOfMonth(1);
	
		List<Movimento> findByDataInclusaoBetween = buscarPorPeriodoECategoria(
				firstDayOfMonth, lastDayOfMonth, categoriaId);
		return findByDataInclusaoBetween;
	}
	
	private List<Movimento> buscarPorPeriodoECategoria(LocalDate dataIni, LocalDate dataFinal, int categoriaId) {
		List<Movimento> findByDataInclusaoBetween = movimentoRepository.findByDataInclusaoBetweenAndCategoria(
				dataIni, dataFinal, categoriaService.buscaPorId(categoriaId));
		return findByDataInclusaoBetween;
	}

	public Movimento atualizaMovimento(int id, MovimentoDto movimentoDto) {
		Categoria categoria = categoriaService.buscaPorId(movimentoDto.getCategoriaId());
		regraLimite(movimentoDto, categoria);
		Movimento movimento = buscarPorId(id);
		movimento.setCategoria(categoriaService.buscaPorId(movimentoDto.getCategoriaId()));
		movimento.setDataInclusao(movimentoDto.getDataInclusao());
		movimento.setDetalhe(movimentoDto.getDetalhe());
		movimento.setTipoMovimento(movimentoDto.getTipoMovimento());
		movimento.setValor(movimentoDto.getValor());
		
		movimentoRepository.save(movimento);
		
		return null;
	}

	public void deletaMovimento(int id) {
		movimentoRepository.deleteById(id);
		
	}
	
	public void regraLimite(MovimentoDto movimentoDto, Categoria categoria) {
		if ("D".equals(movimentoDto.getTipoMovimento())) {
			List<Movimento> listaNesteMesPorCategoria = buscarMesAtualPorCategoria(movimentoDto.getCategoriaId());
			String nomeCategoria = categoria.getNome();
			double limite = categoria.getLimite();
			double sum = 0.0;
			
			if (listaNesteMesPorCategoria.size() > 0) {
				sum = listaNesteMesPorCategoria.stream().mapToDouble(m -> m.getValor()).sum();
			}
			
			sum += movimentoDto.getValor(); 
			if (sum >= limite) {
				throw new LimiteExcedidoException(
						"Limite de: " + limite + " para categoria: " + nomeCategoria + " atingido!");
			}
		}
	}
}
