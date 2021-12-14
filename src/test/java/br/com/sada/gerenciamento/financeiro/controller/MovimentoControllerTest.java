package br.com.sada.gerenciamento.financeiro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.Movimento;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaView;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoDto;
import br.com.sada.gerenciamento.financeiro.model.dto.MovimentoView;
import br.com.sada.gerenciamento.financeiro.service.MovimentoService;

@AutoConfigureMockMvc
@WebMvcTest(controllers = MovimentoController.class)
class MovimentoControllerTest {

	private final String url = "/movimentos";
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovimentoService movimentoService;

	@Test
	void testBuscarPorId() throws Exception {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		Movimento movimento = new Movimento(0.0, "D", "teste", new Categoria("teste", 1.00));
		movimento.setId(1);

		doReturn(movimento).when(movimentoService).buscarPorId(Mockito.anyInt());

		MvcResult result = mockMvc.perform(get(url + "/1")).andExpect(status().isOk()).andReturn();
		MovimentoView movimentoView = mapper.readValue(result.getResponse().getContentAsString(), MovimentoView.class);

		assertEquals("teste", movimentoView.getDetalhe());
		assertEquals(1, movimentoView.getId());

	}

	@Test
	void testInserirMovimento() throws Exception {
		String body = "{\"valor\" : 1200.00,\"tipoMovimento\" : \"C\",\"detalhe\" : \"salario\",\"categoriaId\" : 9}";

		Movimento movimento = new Movimento(0.0, "D", "teste", new Categoria("teste", 1.00));
		movimento.setId(1);

		doReturn(movimento).when(movimentoService).salvaMovimento(Mockito.any(MovimentoDto.class));

		MvcResult result = mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).content(body))
				.andExpect(status().isCreated()).andReturn();

		assertEquals("http://localhost/movimentos/1", result.getResponse().getHeader("Location"));
	}

	@Test
	void testAtualizaMovimento() throws Exception {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		
		Movimento movimento = new Movimento(0.0, "D", "teste", new Categoria("teste", 1.00));
		movimento.setId(1);
		
		doReturn(movimento).when(movimentoService).atualizaMovimento(Mockito.anyInt(), Mockito.any(MovimentoDto.class));
		doReturn(movimento).when(movimentoService).buscarPorId(Mockito.anyInt());
		
		MvcResult result = mockMvc.perform(get(url + "/1")).andExpect(status().isOk()).andReturn();
		MovimentoView movimentoView = mapper.readValue(result.getResponse().getContentAsString(), MovimentoView.class);

		assertEquals("teste", movimentoView.getDetalhe());
		assertEquals(1, movimentoView.getId());
	}

	@Test
	void testDeletaMovimento() throws Exception {
		Movimento movimento = new Movimento(0.0, "D", "teste", new Categoria("teste", 1.00));
		movimento.setId(1);
		doReturn(movimento).when(movimentoService).buscarPorId(Mockito.anyInt());
		doNothing().when(movimentoService).deletaMovimento(Mockito.anyInt());

		mockMvc.perform(get(url + "/1")).andExpect(status().isOk());
	}

	@Test
	void testBuscarMesAtual() throws Exception {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<Movimento> listaMovimento = new ArrayList<>();
		listaMovimento.add(new Movimento(1.0, "D", "teste", new Categoria("teste", 1.00)));
		listaMovimento.add(new Movimento(1.0, "D", "teste", new Categoria("teste", 2.00)));
		
		
		doReturn(listaMovimento).when(movimentoService).buscarMovimentoMesPorData(Mockito.any(LocalDate.class));

		MvcResult result = mockMvc.perform(get(url+"/mes")).andExpect(status().isOk()).andReturn();
		MovimentoView[] listaCategoriaView = mapper.readValue(result.getResponse().getContentAsString(), MovimentoView[].class);

		assertEquals("teste", listaCategoriaView[0].getDetalhe());
		assertEquals(1.00, listaCategoriaView[0].getValor());
	}

	@Test
	void testBuscarMesAtualCategoria() throws Exception {
		mapper.registerModule(new JavaTimeModule());
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		List<Movimento> listaMovimento = new ArrayList<>();
		listaMovimento.add(new Movimento(1.0, "D", "teste", new Categoria("teste", 1.00)));
		listaMovimento.add(new Movimento(1.0, "D", "teste", new Categoria("teste", 2.00)));
		
		
		doReturn(listaMovimento).when(movimentoService).buscarMesAtualPorCategoria(Mockito.anyInt());

		MvcResult result = mockMvc.perform(get(url+"/mes/1")).andExpect(status().isOk()).andReturn();
		MovimentoView[] listaCategoriaView = mapper.readValue(result.getResponse().getContentAsString(), MovimentoView[].class);

		assertEquals("teste", listaCategoriaView[0].getDetalhe());
		assertEquals(1.00, listaCategoriaView[0].getValor());
	}

}
