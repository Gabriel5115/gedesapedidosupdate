package com.sinensia.gedesa.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.services.ClienteServices;
import com.sinensia.gedesa.integration.model.ClientePL;
import com.sinensia.gedesa.integration.repositories.ClientePLRepository;

@Service
public class ClienteServicesImpl implements ClienteServices{
	
	@Autowired
	private ClientePLRepository clientePLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public void create(Cliente cliente) {
		clientePLRepository.save(mapper.map(cliente, ClientePL.class));
	}

	@Override
	public Cliente read(String identificadorFiscal) {
		
		final Optional<ClientePL> optional = clientePLRepository.findById(identificadorFiscal);
		
		return optional.isPresent() ? mapper.map(optional.get(), Cliente.class) : null;	
	}

	@Override
	@Transactional
	public boolean update(Cliente cliente) {
		
		boolean exists = clientePLRepository.existsById(cliente.getIdentificadorFiscal());
		
		if(!exists) {
			throw new IllegalStateException("No existe el cliente con identificador fiscal: " + cliente.getIdentificadorFiscal());
		}
		
		ClientePL updatedClientePL = clientePLRepository.save(mapper.map(cliente, ClientePL.class));
		
		return updatedClientePL != null;
	}

	@Override
	public List<Cliente> getAll() {
		return convertListToBusiness(clientePLRepository.findAll());
	}

	@Override
	public List<Cliente> getByNombreLike(String nombre) {
		return convertListToBusiness(clientePLRepository.findByNombreLike(nombre));
	}

	@Override
	public List<Cliente> getByPoblacionLike(String poblacion) {
		return convertListToBusiness(clientePLRepository.findByPoblacionLike(poblacion));  
	}

	@Override
	public List<Cliente> getClientesSinPedidos() {
		return convertListToBusiness(clientePLRepository.findClientesSinPedidos());  		
	}
	
	// *****************************************************************
	//
	// PRIVATED METHODS
	//
	// *****************************************************************
	
	private List<Cliente> convertListToBusiness(List<ClientePL> clientesPL){
		
		return clientesPL
				.stream()
				.map(x -> mapper.map(x, Cliente.class))
				.collect(Collectors.toList());
	}

}
