package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import com.sinensia.gedesa.business.model.Pedido;

@SpringBootTest 
@Sql(scripts = {"/data/h2/schema.sql","/data/h2/data.sql"})
public class PedidoServicesIntegrationTest {

	@Autowired
	private PedidoServices pedidoServices;
	
	@Test
	public void testGetAll() {
		
		List<Pedido> pedidos = pedidoServices.getAll();
		
		assertNotNull(pedidos);
		assertEquals(4, pedidos.size());
		assertNotNull(pedidos.get(0));	
	}
	

}
