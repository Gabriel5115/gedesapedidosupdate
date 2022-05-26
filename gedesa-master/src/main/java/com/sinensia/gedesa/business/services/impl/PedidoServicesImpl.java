package com.sinensia.gedesa.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.Estado;
import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.business.services.PedidoServices;
import com.sinensia.gedesa.integration.model.ClientePL;
import com.sinensia.gedesa.integration.model.EstadoPL;
import com.sinensia.gedesa.integration.model.PedidoPL;
import com.sinensia.gedesa.integration.repositories.PedidoPLRepository;

@Service
public class PedidoServicesImpl implements PedidoServices{

	@Autowired
	private PedidoPLRepository pedidoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Pedido create(Pedido pedido) {
		
		final PedidoPL pedidoPL = mapper.map(pedido, PedidoPL.class);
		pedidoPL.setEstado(EstadoPL.ABIERTO);
		final PedidoPL createdPedidoPL = pedidoPLRepository.save(pedidoPL);
	
		return mapper.map(createdPedidoPL, Pedido.class);
	}

	@Override
	public Pedido read(long codigo) {
		
		final Optional<PedidoPL> optional = pedidoPLRepository.findById(codigo);
		
		return optional.isPresent() ? mapper.map(optional.get(), Pedido.class) : null;
	}

	@Override
	public List<Pedido> getAll() {

		return pedidoPLRepository.findAll()
				.stream()
				.map(x -> mapper.map(x, Pedido.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<Pedido> getByIdentificadorFiscalCliente(String identificadorFiscal) {
		
		return pedidoPLRepository.findByClienteIdentificadorFiscalOrderByClienteIdentificadorFiscal(identificadorFiscal)
				.stream()
				.map(x -> mapper.map(x, Pedido.class))
				.collect(Collectors.toList());
		
	}

	@Override
	@Transactional
	public boolean UpdateEstadoById(Long id, EstadoPL estadoPL) {
		
			boolean exists = pedidoPLRepository.existsById(id);
			
			if(!exists) {
				throw new IllegalStateException("No existe el pedido con código: " + id);
			}
			
			PedidoPL pedidoPL = pedidoPLRepository.getById(id);
			
			checkEstado(pedidoPL, estadoPL);
			
			PedidoPL updatedPedidoPL = pedidoPLRepository.save(mapper.map(pedidoPL, PedidoPL.class));
			
			return updatedPedidoPL != null;
		
		
	}
	
	
	private void checkEstado(PedidoPL pedido, EstadoPL estadoNuevo) {
		
		if (estadoNuevo.equals(EstadoPL.CANCELADO)) {
			if (pedido.getEstado().equals(EstadoPL.ABIERTO) || pedido.getEstado().equals(EstadoPL.CERRADO) || pedido.getEstado().equals(EstadoPL.EN_PROCESO)) {
				pedido.setEstado(estadoNuevo);
			} else {
				throw new IllegalStateException("No se puede actualizar al estado " + estadoNuevo + " si el  estado actual es " + pedido.getEstado());
			}
		} else if(estadoNuevo.equals(EstadoPL.EN_PROCESO)){
			if (pedido.getEstado().equals(EstadoPL.ABIERTO)) {
				pedido.setEstado(estadoNuevo);
			} else {
				throw new IllegalStateException("No se puede actualizar al estado " + estadoNuevo + " si el  estado actual es " + pedido.getEstado());
			}
		} else if (estadoNuevo.equals(EstadoPL.CERRADO)) {
			if (pedido.getEstado().equals(EstadoPL.EN_PROCESO)) {
				pedido.setEstado(estadoNuevo);
			} else {
				throw new IllegalStateException("No se puede actualizar al estado " + estadoNuevo + " si el  estado actual es " + pedido.getEstado());
			}
		} else if (estadoNuevo.equals(EstadoPL.ENTREGADO)) {
			if (pedido.getEstado().equals(EstadoPL.CERRADO)) {
				pedido.setEstado(estadoNuevo);
			}else {
				throw new IllegalStateException("No se puede actualizar al estado " + estadoNuevo + " si el  estado actual es " + pedido.getEstado());
			}
		} else {
			throw new IllegalStateException("No se puede actualizar al estado " + estadoNuevo + " si el  estado actual es " + pedido.getEstado());
		}
	}
	
	

}
