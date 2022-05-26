package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.gedesa.business.model.Cliente;

@SpringBootTest 
@Sql(scripts = {"/data/h2/schema.sql","/data/h2/data.sql"})
public class ClienteServicesIntegrationTest {

	@Autowired
	private ClienteServices clienteServices;
	
	@Test
	public void testCreate() {
		
		Cliente clienteCreado = clienteServices.read("66666666R");
		
		assertNull(clienteCreado);
		
		Cliente cliente = new Cliente();
		cliente.setIdentificadorFiscal("66666666R");
		cliente.setNombre("Nombre cliente nuevo");
		cliente.setApellido1("Apellido1 cliente nuevo");
		cliente.setApellido2("Apellido2 cliente nuevo");
		
		clienteServices.create(cliente);
		
		clienteCreado = null;
		
		clienteCreado = clienteServices.read("66666666R");
		
		assertNotNull(clienteCreado);
		assertEquals("66666666R", clienteCreado.getIdentificadorFiscal());
		assertEquals("Nombre cliente nuevo", clienteCreado.getNombre());
		
	}
	
	@Test
	public void testGetAll() {
		
		List<Cliente> clientes = clienteServices.getAll();
		
		assertNotNull(clientes);
		assertEquals(3, clientes.size());
		assertNotNull(clientes.get(0));	
	}
	
	@Test
	public void testsGetClientesSinPedidos() {
		
		List<Cliente> clientes = clienteServices.getClientesSinPedidos();
		
		assertNotNull(clientes);
		assertEquals(1, clientes.size());
		assertEquals("46344938L", clientes.get(0).getIdentificadorFiscal());
		assertEquals("Honorio", clientes.get(0).getNombre());
		
	}
	
}
