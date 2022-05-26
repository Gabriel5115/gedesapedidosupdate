package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.Comercial;
import com.sinensia.gedesa.business.model.DatosContacto;
import com.sinensia.gedesa.business.model.Direccion;
import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.LineaPedido;
import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.impl.PedidoServicesImpl;
import com.sinensia.gedesa.integration.model.ClientePL;
import com.sinensia.gedesa.integration.model.ComercialPL;
import com.sinensia.gedesa.integration.model.DatosContactoPL;
import com.sinensia.gedesa.integration.model.DireccionPL;
import com.sinensia.gedesa.integration.model.FamiliaPL;
import com.sinensia.gedesa.integration.model.LineaPedidoPL;
import com.sinensia.gedesa.integration.model.PedidoPL;
import com.sinensia.gedesa.integration.model.ProductoPL;
import com.sinensia.gedesa.integration.repositories.PedidoPLRepository;

@ExtendWith(MockitoExtension.class)
class PedidoServicesTest {

	@Mock
    private PedidoPLRepository pedidoPLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private PedidoServicesImpl pedidoServices;
	
	private Pedido pedido1;
	private Pedido pedido2;
	
	private PedidoPL pedido1PL;
	private PedidoPL pedido2PL;
	
	private List<PedidoPL> pedidosPL;
	
	@BeforeEach
	public void init() {
		initMocks();
	}
	
	@Test
	void testCreate() {
	//	fail("Not yet implemented");
	}

	@Test
	void testRead() {
	//	fail("Not yet implemented");
	}

	@Test
	void testGetAll() {
	//	fail("Not yet implemented");
	}

	@Test
	void testGetByIdentificadorFiscalCliente() {
		
		when(pedidoPLRepository.findByClienteIdentificadorFiscalOrderByClienteIdentificadorFiscal("1000A")).thenReturn(pedidosPL);
		when(mapper.map(pedido1PL, Pedido.class)).thenReturn(pedido1);
		when(mapper.map(pedido2PL, Pedido.class)).thenReturn(pedido2);
		
		List<Pedido> pedidos = pedidoServices.getByIdentificadorFiscalCliente("1000A");
		
		assertNotNull(pedidos);
		assertEquals(2, pedidos.size());
		
	}
	
	private void initMocks() {
		
		Cliente cliente = new Cliente();
		Comercial comercial = new Comercial();
		Producto producto1 = new Producto();
		Direccion direccion1 = new Direccion();
		DatosContacto datosContacto1 = new DatosContacto();

		datosContacto1.setEmail("email@gmail.com");
		datosContacto1.setMovil("606000000");
		
		direccion1.setDireccion("c/Dirección 1");
		direccion1.setPoblacion("Matadepera");
		direccion1.setProvincia("Barcelona");
		direccion1.setCodigoPostal("08020");
		direccion1.setPais("España");
		
		cliente.setIdentificadorFiscal("1000A");
		cliente.setNombre("Nombre Cliente1");
		cliente.setApellido1("Apellido1 Cliente1");
		cliente.setApellido2("Apellido2 Cliente2");
		cliente.setDatosContacto(datosContacto1);
		cliente.setDireccion(direccion1);
		
		producto1.setCodigo(100);
		producto1.setFamilia(Familia.HARDWARE);
		producto1.setNombre("Producto 1");
		producto1.setPrecio(50.0);
	
		LineaPedido lineaPedido1 = new LineaPedido();
		lineaPedido1.setProducto(producto1);
		lineaPedido1.setCantidad(10);
		
		LineaPedido lineaPedido2 = new LineaPedido();
		lineaPedido2.setProducto(producto1);
		lineaPedido2.setCantidad(5);
		
		List<LineaPedido> lineasPedido1 = new ArrayList<>();
		List<LineaPedido> lineasPedido2 = new ArrayList<>();
		
		pedido1 = new Pedido();
		pedido2 = new Pedido();
		
		pedido1.setCodigo(1L);
		pedido1.setCliente(cliente);
		pedido1.setComercial(comercial);
		pedido1.setFechaHora(new Date(10000000));
		pedido1.setLineas(lineasPedido1);
		pedido1.setObservaciones("Observaciones Pedido1");
		
		pedido2.setCodigo(2L);
		pedido2.setCliente(cliente);
		pedido2.setComercial(comercial);
		pedido2.setFechaHora(new Date(20000000));
		pedido2.setLineas(lineasPedido2);
		pedido2.setObservaciones("Observaciones Pedido2");
		
		// PL
		
		ClientePL cliente1PL = new ClientePL();
		ClientePL cliente2PL = new ClientePL();
		ComercialPL comercialPL = new ComercialPL();
		ProductoPL producto1PL = new ProductoPL();
		
		DireccionPL direccion1PL = new DireccionPL();
		DireccionPL direccion2PL = new DireccionPL();
		
		DatosContactoPL datosContacto1PL = new DatosContactoPL();
		DatosContactoPL datosContacto2PL= new DatosContactoPL();

		datosContacto1PL.setEmail("email@gmail.com");
		datosContacto1PL.setMovil("606000000");
		
		direccion1PL.setDireccion("c/Dirección 1");
		direccion1PL.setPoblacion("Matadepera");
		direccion1PL.setProvincia("Barcelona");
		direccion1PL.setCodigoPostal("08020");
		direccion1PL.setPais("España");
		
		datosContacto2PL.setEmail("email@gmail.com");
		datosContacto2PL.setMovil("606000000");
		
		direccion1PL.setDireccion("c/Dirección 1");
		direccion1PL.setPoblacion("Matadepera");
		direccion1PL.setProvincia("Barcelona");
		direccion1PL.setCodigoPostal("08020");
		direccion1PL.setPais("España");
		
		direccion2PL.setDireccion("c/Dirección 2");
		direccion2PL.setPoblacion("Matadepera");
		direccion2PL.setProvincia("Barcelona");
		direccion2PL.setCodigoPostal("08020");
		direccion2PL.setPais("España");
		
		cliente1PL.setIdentificadorFiscal("1000A");
		cliente1PL.setNombre("Nombre Cliente1");
		cliente1PL.setApellido1("Apellido1 Cliente1");
		cliente1PL.setApellido2("Apellido2 Cliente1");
		cliente1PL.setDatosContacto(datosContacto1PL);
		cliente1PL.setDireccion(direccion1PL);
		
		cliente2PL.setIdentificadorFiscal("1000B");
		cliente2PL.setNombre("Nombre Cliente2");
		cliente2PL.setApellido1("Apellido1 Cliente2");
		cliente2PL.setApellido2("Apellido2 Cliente2");
		cliente2PL.setDatosContacto(datosContacto2PL);
		cliente2PL.setDireccion(direccion2PL);
		
		producto1PL.setCodigo(100);
		producto1PL.setFamilia(FamiliaPL.HARDWARE);
		producto1PL.setNombre("Producto 1");
		producto1PL.setPrecio(50.0);
	
		LineaPedidoPL lineaPedido1PL = new LineaPedidoPL();
		lineaPedido1PL.setProducto(producto1PL);
		lineaPedido1PL.setCantidad(10);
		
		LineaPedidoPL lineaPedido2PL = new LineaPedidoPL();
		lineaPedido2PL.setProducto(producto1PL);
		lineaPedido2PL.setCantidad(5);
		
		List<LineaPedidoPL> lineasPedido1PL = new ArrayList<>();
		List<LineaPedidoPL> lineasPedido2PL = new ArrayList<>();
		
		pedido1PL = new PedidoPL();
		pedido2PL = new PedidoPL();
		
		pedido1PL.setCodigo(1L);
		pedido1PL.setCliente(cliente1PL);
		pedido1PL.setComercial(comercialPL);
		pedido1PL.setFechaHora(new Date(10000000));
		pedido1PL.setLineas(lineasPedido1PL);
		pedido1PL.setObservaciones("Observaciones Pedido1PL");
		
		pedido2PL.setCodigo(2L);
		pedido2PL.setCliente(cliente2PL);
		pedido2PL.setComercial(comercialPL);
		pedido2PL.setFechaHora(new Date(20000000));
		pedido2PL.setLineas(lineasPedido2PL);
		pedido2PL.setObservaciones("Observaciones Pedido2PL");
		
		pedidosPL = new ArrayList<>();
		pedidosPL.add(pedido1PL);
		pedidosPL.add(pedido2PL);
		
	}

}
