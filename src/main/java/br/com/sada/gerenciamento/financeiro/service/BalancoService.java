package br.com.sada.gerenciamento.financeiro.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;

@Service
public class BalancoService {

	private MovimentoService movimentoService;

	public BalancoService(MovimentoService movimentoService) {
		this.movimentoService = movimentoService;
	}
	
	public BalancoView balancoMensal(BalancoDto balancoDto) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoMesPorData(balancoDto.getData());
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}

	public BalancoView balancoSemanal(BalancoDto balancoDto) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoSemanaPorData(balancoDto.getData());
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}

	public BalancoView balancoDiario(BalancoDto balancoDto) {
		List<Movimento> movimentoPeriodo = movimentoService.buscarMovimentoDia(balancoDto.getData());
		List<Movimento> listaDebitos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoPeriodo.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}
}
