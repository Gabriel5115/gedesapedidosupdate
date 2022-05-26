package com.sinensia.gedesa.presentation.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.List;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.services.ClienteServices;
import com.sinensia.gedesa.business.services.PedidoServices;

// https://reflectoring.io/spring-boot-web-controller-test/

@WebMvcTest(controllers = ClienteController.class)
public class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private ClienteServices clienteServices;
	
	@MockBean
	private PedidoServices pedidoServices;
	
	@BeforeEach
	public void init() {
		initMocks();
	}

	private Cliente cliente1;
	private Cliente cliente2;
	private List<Cliente> clientes1;
	private List<Cliente> clientes2;
	
	@Test
	public void cuandoPidoClientesSinParametro() throws Exception {
		
		when(clienteServices.getAll()).thenReturn(clientes1);
		
		MvcResult mvcResult = mockMvc.perform(get("/clientes")
								.contentType("application/json"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
		
		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(clientes1));
	}
	
	
	@Test
	public void cuandoPidoClientesConParametroNombre() throws Exception {
	
		when(clienteServices.getByNombreLike("pe")).thenReturn(clientes2);
		
		MvcResult mvcResult = mockMvc.perform(get("/clientes")
								.param("nombre", "pe")
								.contentType("application/json"))
								.andExpect(status().isOk())
								.andReturn();
		
		String responseBody = mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
	
		assertThat(responseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(clientes2));
		
	}
	
	// *****************************************************************
	//
	// PRIVATE METHODS
	//
	// *****************************************************************	
	
	private void initMocks() {
		
		cliente1 = new Cliente();
		cliente2 = new Cliente();
		
		cliente1.setIdentificadorFiscal("111A");
		cliente2.setIdentificadorFiscal("222B");
		
		cliente1.setNombre("Pepín");
		cliente2.setNombre("Honorio");
		
		clientes1 = Arrays.asList(cliente1, cliente2);
		clientes2 = Arrays.asList(cliente1);
		
	}
	
}
