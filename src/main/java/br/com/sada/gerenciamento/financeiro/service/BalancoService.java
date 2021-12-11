package br.com.sada.gerenciamento.financeiro.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;

@Service
public class BalancoService {

	private MovimentoService movimentoService;

	public BalancoService(MovimentoService movimentoService) {
		this.movimentoService = movimentoService;
	}
	
	public BalancoView balancoMensal(LocalDate data) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoMesPorData(data);
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}

	public BalancoView balancoSemanal(LocalDate data) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoSemanaPorData(data);
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}

	public BalancoView balancoDiario(LocalDate data) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoDia(data);
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}
}
