package com.sinensia.gedesa.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.gedesa.business.model.Cliente;
import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.business.services.ClienteServices;
import com.sinensia.gedesa.business.services.PedidoServices;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteServices clienteServices;
	
	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping
	public List<Cliente> getClientes(@RequestParam(required=false) String nombre){
		return (nombre == null) ? clienteServices.getAll() : clienteServices.getByNombreLike(nombre);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Cliente cliente, UriComponentsBuilder uriComponentsBuilder){
		
		clienteServices.create(cliente);
		
		return ResponseEntity
				.created(uriComponentsBuilder.path("/clientes/{identificadorFiscal}").build(cliente.getIdentificadorFiscal()))
				.build();
		
	}
	
	@GetMapping("/{identificadorFiscal}")
	public Cliente getByIdentificadorFiscal(@PathVariable String identificadorFiscal) {
		return clienteServices.read(identificadorFiscal);
	}
	
	@GetMapping("/{identificadorFiscal}/pedidos")
	public List<Pedido> getByClienteId(@PathVariable String identificadorFiscal){
		return pedidoServices.getByIdentificadorFiscalCliente(identificadorFiscal);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Cliente cliente){
		
		boolean actualizado = clienteServices.update(cliente);
		
		if(!actualizado) {
			throw new RuntimeException("No se ha podido actualizar el cliente " + cliente.getIdentificadorFiscal());
		}
		
		return ResponseEntity.status(204).build();
	}
		
}
