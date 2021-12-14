package br.com.sada.gerenciamento.financeiro.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import br.com.sada.gerenciamento.financeiro.model.Categoria;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaDto;
import br.com.sada.gerenciamento.financeiro.model.dto.CategoriaView;
import br.com.sada.gerenciamento.financeiro.service.CategoriaService;

@AutoConfigureMockMvc
@WebMvcTest(controllers = CategoriaController.class)
class CategoriaControllerTest {

	private final String url = "/categorias";
	private final ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CategoriaService categoriaService;

	@Test
	void testBuscarPorId() throws Exception {
		Categoria categoria = new Categoria("teste", 0.0);
		categoria.setId(1);
		
		doReturn(categoria).when(categoriaService).buscaPorId(Mockito.anyInt());

		MvcResult result = mockMvc.perform(get(url + "/1")).andExpect(status().isOk()).andReturn();
		CategoriaView categoriaView = mapper.readValue(result.getResponse().getContentAsString(), CategoriaView.class);

		assertEquals("teste", categoriaView.getNome());
		assertEquals(1, categoriaView.getId());

	}

	@Test
	void testInserirCategoria() throws Exception {
		String body = "{\"nome\" : \"LOTERIA\", \"limite\" : 1000.00}";
		
		Categoria categoria = new Categoria("teste", 0.0);
		categoria.setId(1);
		
		doReturn(categoria).when(categoriaService).inserirCategoria(Mockito.any(CategoriaDto.class));
		
		MvcResult result = mockMvc.perform(post(url)
				.contentType(MediaType.APPLICATION_JSON)
				.content(body)).andExpect(status().isCreated()).andReturn();

		assertEquals("http://localhost/categorias/1" , result.getResponse().getHeader("Location"));

	}

	@Test
	void testListaCategoria() throws Exception {
		List<Categoria> listaCategoria = new ArrayList<>();
		listaCategoria.add(new Categoria("teste", 1.00));
		listaCategoria.add(new Categoria("teste2", 2.00));
		
		
		doReturn(listaCategoria).when(categoriaService).listarCategoria();

		MvcResult result = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		CategoriaView[] listaCategoriaView = mapper.readValue(result.getResponse().getContentAsString(), CategoriaView[].class);

		assertEquals("teste", listaCategoriaView[0].getNome());
		assertEquals(1.00, listaCategoriaView[0].getLimite());
	}

	@Test
	void testAtualizaCategoria() throws Exception {
		Categoria categoria = new Categoria("teste", 0.0);
		categoria.setId(1);
		
		doReturn(categoria).when(categoriaService).atualizaCategoria(Mockito.anyInt(), Mockito.any(CategoriaDto.class));
		doReturn(categoria).when(categoriaService).buscaPorId(Mockito.anyInt());

		MvcResult result = mockMvc.perform(get(url + "/1")).andExpect(status().isOk()).andReturn();
		CategoriaView categoriaView = mapper.readValue(result.getResponse().getContentAsString(), CategoriaView.class);

		assertEquals("teste", categoriaView.getNome());
		assertEquals(1, categoriaView.getId());
	}

	@Test
	void testDeletaCategoria() throws Exception {
		Categoria categoria = new Categoria("teste", 0.0);
		categoria.setId(1);
		doNothing().when(categoriaService).deletaCategoria(Mockito.anyInt());
		doReturn(categoria).when(categoriaService).buscaPorId(Mockito.anyInt());
		
		mockMvc.perform(get(url + "/1")).andExpect(status().isOk());
	}

}
