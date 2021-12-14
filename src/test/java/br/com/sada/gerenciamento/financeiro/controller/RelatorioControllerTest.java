package br.com.sada.gerenciamento.financeiro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sada.gerenciamento.financeiro.model.dto.BalancoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.BalancoView;
import br.com.sada.gerenciamento.financeiro.service.BalancoService;

@AutoConfigureMockMvc
@WebMvcTest(controllers = RelatorioController.class)
class RelatorioControllerTest {
	private final String url = "/relatorios";
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BalancoService balancoService;

	@Test
	void testBalancoMensal() throws Exception {
		BalancoView balancoView = new BalancoView(new ArrayList<>(), new ArrayList<>(), 0);
		String body = "{\"valor\" : \"2021-12-10\"}";

		doReturn(balancoView).when(balancoService).balancoMensal(Mockito.any(BalancoDto.class));

		MvcResult result = mockMvc.perform(post(url + "/mensal")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isOk()).andReturn();
		BalancoView balanco = mapper.readValue(result.getResponse().getContentAsString(), BalancoView.class);

		assertEquals(0, balanco.getTotal());
		assertEquals(0, balanco.getListaCreditos().size());
		assertEquals(0, balanco.getListaDebitos().size());
	}

	@Test
	void testBalancoSemanal() throws Exception {

		BalancoView balancoView = new BalancoView(new ArrayList<>(), new ArrayList<>(), 0);
		String body = "{\"valor\" : \"2021-12-10\"}";
		
		doReturn(balancoView).when(balancoService).balancoSemanal(Mockito.any(BalancoDto.class));

		MvcResult result = mockMvc.perform(post(url + "/semanal")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isOk()).andReturn();
		BalancoView balanco = mapper.readValue(result.getResponse().getContentAsString(), BalancoView.class);
		assertEquals(0, balanco.getTotal());
		assertEquals(0, balanco.getListaCreditos().size());
		assertEquals(0, balanco.getListaDebitos().size());
	}

	@Test
	void testBalancoDiario() throws Exception {
		BalancoView balancoView = new BalancoView(new ArrayList<>(), new ArrayList<>(), 0);
		String body = "{\"valor\" : \"2021-12-10\"}";
		
		doReturn(balancoView).when(balancoService).balancoDiario(Mockito.any(BalancoDto.class));

		MvcResult result = mockMvc.perform(post(url + "/diario")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isOk()).andReturn();
		BalancoView balanco = mapper.readValue(result.getResponse().getContentAsString(), BalancoView.class);

		assertEquals(0, balanco.getTotal());
		assertEquals(0, balanco.getListaCreditos().size());
		assertEquals(0, balanco.getListaDebitos().size());
	}

}
