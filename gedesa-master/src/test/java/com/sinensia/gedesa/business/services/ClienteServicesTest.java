package com.sinensia.gedesa.business.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.DatosContacto;
import com.sinensia.gedesa.business.model.Direccion;
import com.sinensia.gedesa.business.services.impl.ClienteServicesImpl;
import com.sinensia.gedesa.integration.model.ClientePL;
import com.sinensia.gedesa.integration.repositories.ClientePLRepository;

@ExtendWith(MockitoExtension.class)
public class ClienteServicesTest {

	@Mock
	private ClientePLRepository clientePLRepository;
	
	@Mock
	private DozerBeanMapper mapper;
	
	@InjectMocks
	private ClienteServicesImpl clienteServices;
	
	private Cliente cliente1;
	private Cliente cliente2;
	private ClientePL cliente1PL;
	private ClientePL cliente2PL;
	private List<ClientePL> clientesPL;
	
	@BeforeEach
	public void init() {
		initMocks();
	}
	
	@Test
	public void testCreate() {
		
		when(clientePLRepository.save(cliente1PL)).thenReturn(cliente1PL);
		when(mapper.map(cliente1, ClientePL.class)).thenReturn(cliente1PL);
		
		clienteServices.create(cliente1);
		
	}
	
	@Test
	public void testGetByPoblacionLike() {
		
		when(clientePLRepository.findByPoblacionLike("ata")).thenReturn(clientesPL);
		
		when(mapper.map(cliente1PL, Cliente.class)).thenReturn(cliente1);
		when(mapper.map(cliente2PL, Cliente.class)).thenReturn(cliente2);
		
		List<Cliente> clientes = clienteServices.getByPoblacionLike("ata");
		
		System.out.println(clientes);
		
		assertNotNull(clientes);
		assertEquals(2, clientes.size());
		assertNotNull(clientes.get(0));
		assertNotNull(clientes.get(1));
		
	}

	private void initMocks() {
		
		cliente1PL = new ClientePL();
		cliente2PL = new ClientePL();
		
		cliente1PL.setIdentificadorFiscal("463441");
		cliente2PL.setIdentificadorFiscal("463442");
		
		Direccion direccion1 = new Direccion();
		Direccion direccion2 = new Direccion();
		DatosContacto datosContacto1 = new DatosContacto();
		DatosContacto datosContacto2 = new DatosContacto();
		
		direccion1.setPoblacion("Matadepera");
		direccion2.setPoblacion("Mataró");
		
		cliente1 = new Cliente();
		cliente1.setIdentificadorFiscal("463441");
		cliente1.setNombre("Nombre Cliente1");
		cliente1.setApellido1("Apellido1 Cliente1");
		cliente1.setApellido2("Apellido2 Cliente1");
		cliente1.setDireccion(direccion1);
		cliente1.setDatosContacto(datosContacto1);

		cliente2 = new Cliente();
		cliente2.setIdentificadorFiscal("463442");
		cliente2.setNombre("Nombre Cliente2");
		cliente2.setApellido1("Apellido1 Cliente2");
		cliente2.setApellido2("Apellido2 Cliente2");
		cliente2.setDireccion(direccion2);
		cliente2.setDatosContacto(datosContacto2);
		
		clientesPL = new ArrayList<>();
		clientesPL.add(cliente1PL);
		clientesPL.add(cliente2PL);
		
	}
	
	
	
}
