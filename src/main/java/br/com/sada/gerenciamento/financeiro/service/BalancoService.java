package br.com.sada.gerenciamento.financeiro.service;

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
	
	public BalancoView balancoMensal() {
		List<Movimento> movimentoMesAtual = movimentoService.buscarMesAtual();
		List<Movimento> listaDebitos = movimentoMesAtual.stream().filter(m -> m.getTipoMovimento().equals("D")).collect(Collectors.toList());
		List<Movimento> listaCreditos = movimentoMesAtual.stream().filter(m -> m.getTipoMovimento().equals("C")).collect(Collectors.toList());
		double total = listaCreditos.stream().mapToDouble(c -> c.getValor()).sum() - listaDebitos.stream().mapToDouble(d -> d.getValor()).sum();
		
		return new BalancoView(listaDebitos, listaCreditos, total);
	}
	
}
