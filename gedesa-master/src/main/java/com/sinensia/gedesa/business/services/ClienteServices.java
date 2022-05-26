package com.sinensia.gedesa.business.services;

import java.util.List;

import com.sinensia.gedesa.business.model.Cliente;

public interface ClienteServices {

	/**
	 * Lanza IllegalArgumentException si el identificadorFiscal es null o ya existe
	 *
	 */
	void create(Cliente cliente);
	
	Cliente read(String identificadorFiscal);
	
	boolean update(Cliente cliente);
	
	List<Cliente> getAll();
	
	/**
	 * Devuelve los clientes cuyo nombre contenga el parametro "nombre"
	 * 
	 */
	List<Cliente> getByNombreLike(String nombre);
	
	List<Cliente> getByPoblacionLike(String poblacion);
	List<Cliente> getClientesSinPedidos();
//	List<Cliente> getClientesSinPedidos(Date desde, Date hasta);
	
}
