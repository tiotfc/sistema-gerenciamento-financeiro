package br.com.sada.gerenciamento.financeiro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoDto;

@ExtendWith(MockitoExtension.class)
class BalancoServiceTest {

	@Mock
	private MovimentoService movimentoService;
	
	private BalancoService balancoService;
	
    @BeforeEach
    void initUseCase() {
    	balancoService = new BalancoService(movimentoService);
    }
	
	
	@Test
	void testBalancoMensal() {
		List<Movimento> listaMovimentos = new ArrayList<>();
		listaMovimentos.add(new Movimento(1.0, "D", "detalhe debito", new Categoria("Categoria", 0.0)));
		listaMovimentos.add(new Movimento(1.0, "C", "detalhe credito", new Categoria("Categoria", 0.0)));
		
		
		BalancoDto balancoDto = new BalancoDto();
		balancoDto.setData(LocalDate.now());
		
		doReturn(listaMovimentos).when(movimentoService).buscarMovimentoMesPorData(Mockito.any(LocalDate.class));
		
		assertEquals("detalhe debito", balancoService.balancoMensal(balancoDto).getListaDebitos().get(0).getDetalhe());
		assertEquals("detalhe credito", balancoService.balancoMensal(balancoDto).getListaCreditos().get(0).getDetalhe());
		assertEquals(0.0, balancoService.balancoMensal(balancoDto).getTotal());
		
	}

	@Test
	void testBalancoSemanal() {
		List<Movimento> listaMovimentos = new ArrayList<>();
		listaMovimentos.add(new Movimento(1.0, "D", "detalhe debito", new Categoria("Categoria", 0.0)));
		listaMovimentos.add(new Movimento(1.0, "C", "detalhe credito", new Categoria("Categoria", 0.0)));
		
		BalancoDto balancoDto = new BalancoDto();
		balancoDto.setData(LocalDate.now());
		
		doReturn(listaMovimentos).when(movimentoService).buscarMovimentoSemanaPorData(Mockito.any(LocalDate.class));
		
		assertEquals("detalhe debito", balancoService.balancoSemanal(balancoDto).getListaDebitos().get(0).getDetalhe());
		assertEquals("detalhe credito", balancoService.balancoSemanal(balancoDto).getListaCreditos().get(0).getDetalhe());
		assertEquals(0.0, balancoService.balancoSemanal(balancoDto).getTotal());
	}

	@Test
	void testBalancoDiario() {
		List<Movimento> listaMovimentos = new ArrayList<>();
		listaMovimentos.add(new Movimento(1.0, "D", "detalhe debito", new Categoria("Categoria", 0.0)));
		listaMovimentos.add(new Movimento(1.0, "C", "detalhe credito", new Categoria("Categoria", 0.0)));
		
		BalancoDto balancoDto = new BalancoDto();
		balancoDto.setData(LocalDate.now());
		
		doReturn(listaMovimentos).when(movimentoService).buscarMovimentoDia(Mockito.any(LocalDate.class));
		
		assertEquals("detalhe debito", balancoService.balancoDiario(balancoDto).getListaDebitos().get(0).getDetalhe());
		assertEquals("detalhe credito", balancoService.balancoDiario(balancoDto).getListaCreditos().get(0).getDetalhe());
		assertEquals(0.0, balancoService.balancoDiario(balancoDto).getTotal());
	}

}
