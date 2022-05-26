package com.sinensia.gedesa.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.business.services.PedidoServices;
import com.sinensia.gedesa.integration.model.EstadoPL;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping
	public List<Pedido> getAll(){
		return pedidoServices.getAll();
	}
	
	@PostMapping
	public Pedido createPedido(@RequestBody Pedido pedido) {
		
		return pedidoServices.create(pedido);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> partialUpdateName(@PathVariable Long id,  @RequestParam(required=false) String estado) {
		
		boolean actualizado = pedidoServices.UpdateEstadoById(id, EstadoPL.valueOf(estado.toString()));
		
		if(!actualizado) {
			throw new RuntimeException("No se ha podido actualizar el pedido " + id);
		}
		
		return ResponseEntity.status(204).build();
	}
	
}
