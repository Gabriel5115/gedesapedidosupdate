package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sinensia.gedesa.business.model.Producto;

@SpringBootTest   
public class ProductoServicesIntegrationTest {

	@Autowired
	private ProductoServices productoServices;
	
	@Test
	public void testGetAll() {
		
		System.out.println(productoServices);
		
		List<Producto> productos = productoServices.getAll();
		
		assertNotNull(productos);
		assertEquals(5, productos.size());
		assertNotNull(productos.get(0));
		
	}
}
