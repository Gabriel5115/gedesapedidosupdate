package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.impl.ProductoServicesImpl;
import com.sinensia.gedesa.integration.model.FamiliaPL;
import com.sinensia.gedesa.integration.model.ProductoPL;
import com.sinensia.gedesa.integration.repositories.ProductoPLRepository;

@ExtendWith(MockitoExtension.class)
class ProductoServicesTest {

	@Mock
    private ProductoPLRepository productoPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private ProductoServicesImpl productoServices;
	
	private Producto producto1;
	private Producto producto2;
	private ProductoPL producto1PL;
	private ProductoPL producto2PL;
	private List<ProductoPL> productosPLFromRepository;
	private List<ProductoPL> productosPLHardwarweFromRepository;
	
	@BeforeEach
	public void init() {
		initMocks();
	}
	
	@Test
	void testGetAll() {
		
		when(productoPLRepository.findAll()).thenReturn(productosPLFromRepository);
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		when(mapper.map(producto2PL, Producto.class)).thenReturn(producto2);
			
		List<Producto> productos = productoServices.getAll();
		
		assertEquals(2, productos.size());
		assertNotNull(productos.get(0));
		
		Producto producto = new Producto();
		producto.setCodigo(100);
		
		assertTrue(productos.contains(producto));
		
	}
	
	@Test
	void testCreate() {
		
		when(productoPLRepository.save(producto1PL)).thenReturn(producto1PL);
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		when(mapper.map(producto1, ProductoPL.class)).thenReturn(producto1PL);
		
		Producto createdProducto = productoServices.create(producto1);
		
		assertNotNull(createdProducto);
		assertEquals(100 , createdProducto.getCodigo());
		assertEquals("Producto 1", createdProducto.getNombre());
		
	}

	@Test
	void testRead() {
		
		when(productoPLRepository.findById(100)).thenReturn(Optional.of(producto1PL));
		when(productoPLRepository.findById(450)).thenReturn(Optional.empty());
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		
		Producto producto = productoServices.read(100);
		
		assertNotNull(producto);
		assertEquals(100, producto.getCodigo());
		assertEquals("Producto 1", producto.getNombre());
		
		Producto producto2 = productoServices.read(450);
		assertNull(producto2);
	}

	@Test
	void testUpdate() {
		
		when(productoPLRepository.existsById(100)).thenReturn(true);
		when(productoPLRepository.existsById(666)).thenReturn(false);
		when(productoPLRepository.save(producto1PL)).thenReturn(producto1PL);
		when(mapper.map(producto1, ProductoPL.class)).thenReturn(producto1PL);
		
		Producto productoExistente = new Producto();
		Producto productoNOExistente = new Producto();
		
		productoExistente.setCodigo(100);
		productoNOExistente.setCodigo(666);
		
		boolean productoActualizado = productoServices.update(productoExistente);
		
		assertTrue(productoActualizado);
		
		Exception exception = assertThrows(IllegalStateException.class, () -> {
			productoServices.update(productoNOExistente);	
	    });
		
		String mensajeError = exception.getMessage();
		
		assertEquals(mensajeError, "No existe el producto con codigo: 666");
		
	}

	@Test
	void testDelete() {
		
		when(productoPLRepository.existsById(100)).thenReturn(true, false); // devolverá diferentes cosas!
		doNothing().when(productoPLRepository).deleteById(100);
		
		boolean eliminado = productoServices.delete(100);
		
		assertTrue(eliminado);
	}

	@Test
	void testGetBetweenPriceRange() {
		
		when(productoPLRepository.findByPrecioBetweenOrderByCodigo(100, 500)).thenReturn(productosPLFromRepository);
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		when(mapper.map(producto2PL, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServices.getBetweenPriceRange(100, 500);
		
		assertNotNull(productos);
		
		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		producto1.setCodigo(100);
		producto2.setCodigo(101);
		
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));
		
		assertEquals(productos.size(), 2);
		
	}

	@Test
	void testGetBetweenDates() {
		
		Date desde = new Date(100000);
		Date hasta = new Date(200000);
		
		when(productoPLRepository.findByFechaAltaBetweenOrderByCodigo(desde, hasta)).thenReturn(productosPLFromRepository);
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		when(mapper.map(producto2PL, Producto.class)).thenReturn(producto2);
		
		List<Producto> productos = productoServices.getBetweenDates(desde, hasta);
		
		assertNotNull(productos);
		
		Producto producto1 = new Producto();
		Producto producto2 = new Producto();
		producto1.setCodigo(100);
		producto2.setCodigo(101);
		
		assertTrue(productos.contains(producto1));
		assertTrue(productos.contains(producto2));
		
		assertEquals(productos.size(), 2);
		
	}

	@Test
	void testGetByFamilia() {
		
		when(productoPLRepository.findByFamilia(FamiliaPL.HARDWARE)).thenReturn(productosPLHardwarweFromRepository);
		when(mapper.map(producto1PL, Producto.class)).thenReturn(producto1);
		
		List<Producto> productos = productoServices.getByFamilia(Familia.HARDWARE);
		
		assertNotNull(productos);
		
		Producto producto = new Producto();
		producto.setCodigo(100);
		
		assertTrue(productos.contains(producto));
		
		assertEquals(productos.size(), 1);
	}

	@Test
	void testGetNumeroTotalProductos() {
		
		when(productoPLRepository.count()).thenReturn(12773L);
		
		int numeroProductos = productoServices.getNumeroTotalProductos();
		
		assertEquals(12773, numeroProductos);
		
	}

	@Test
	void testGetNumeroTotalProductosByFamilia() {
		
		when(productoPLRepository.getNumerProductosByFamilia(FamiliaPL.HARDWARE)).thenReturn(254);
		
		int numeroProductosHardware = productoServices.getNumeroTotalProductosByFamilia(Familia.HARDWARE);
		
		assertEquals(numeroProductosHardware, 254);
	}

	@Test
	void testGetNumeroProductosAgrupados() {
		
		List<Object[]> resultados = new ArrayList<>();
		resultados.add(new Object[] {FamiliaPL.HARDWARE, 56});
		resultados.add(new Object[] {FamiliaPL.SOFTWARE, 42});
		
		when(productoPLRepository.getCantidadProductosAgrupadosPorFamilia()).thenReturn(resultados);
		
		Map<Familia, Integer> mapaResultados = productoServices.getNumeroProductosAgrupados();
		
		assertEquals(3, mapaResultados.size());
		assertEquals(56, mapaResultados.get(Familia.HARDWARE));
		assertEquals(42, mapaResultados.get(Familia.SOFTWARE));
		assertEquals(null, mapaResultados.get(Familia.CONSUMIBLES));
		
	}

	@Test
	void testGetPreciosMediosAgrupados() {
		
		List<Object[]> resultados = new ArrayList<>();
		resultados.add(new Object[] {FamiliaPL.HARDWARE, 100.0});
		resultados.add(new Object[] {FamiliaPL.SOFTWARE, 45.0});
		
		when(productoPLRepository.getPrecioMedioProductosAgrupadosPorFamilia()).thenReturn(resultados);
		
		Map<Familia, Double> mapaResultados = productoServices.getPreciosMediosAgrupados();
		
		assertEquals(3, mapaResultados.size());
		assertEquals(100.0, mapaResultados.get(Familia.HARDWARE));
		assertEquals(45.0, mapaResultados.get(Familia.SOFTWARE));
		assertEquals(null, mapaResultados.get(Familia.CONSUMIBLES));
		
	}
	
	// *****************************************************************
	//
	// PRIVATE METHODS
	//
	// *****************************************************************
	
	private void initMocks() {
		
		producto1PL = new ProductoPL();
		producto2PL = new ProductoPL();
		producto1 = new Producto();
		producto2 = new Producto();
		
		producto1PL.setCodigo(100);
		producto1PL.setNombre("Producto 1");
		producto1PL.setFechaAlta(new Date(100000));
		producto1PL.setPrecio(100.0);
		producto1PL.setFamilia(FamiliaPL.HARDWARE);
		producto1PL.setDescatalogado(false);
		
		producto2PL.setCodigo(101);
		producto2PL.setNombre("Producto 2");
		producto2PL.setFechaAlta(new Date(200000));
		producto2PL.setPrecio(500.0);
		producto2PL.setFamilia(FamiliaPL.SOFTWARE);
		producto2PL.setDescatalogado(true);
	
		producto1.setCodigo(100);
		producto1.setNombre("Producto 1");
		producto1.setFechaAlta(new Date(100000));
		producto1.setPrecio(100.0);
		producto1.setFamilia(Familia.HARDWARE);
		producto1.setDescatalogado(false);
		
		producto2.setCodigo(101);
		producto2.setNombre("Producto 2");
		producto2.setFechaAlta(new Date(200000));
		producto2.setPrecio(500.0);
		producto2.setFamilia(Familia.SOFTWARE);
		producto2.setDescatalogado(true);
		
		productosPLFromRepository = new ArrayList<>();
		productosPLFromRepository.add(producto1PL);
		productosPLFromRepository.add(producto2PL);
		
		productosPLHardwarweFromRepository = new ArrayList<>();
		productosPLHardwarweFromRepository.add(producto1PL);
		
	}

}
