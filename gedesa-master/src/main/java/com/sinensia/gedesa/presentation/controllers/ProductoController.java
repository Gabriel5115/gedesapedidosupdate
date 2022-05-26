package com.sinensia.gedesa.presentation.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.ProductoServices;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private ProductoServices productoServices;
	
	@GetMapping
	public List<Producto> getProductos(@RequestParam(required=false) Double min, 
									   @RequestParam(required=false) Double max,
									   @RequestParam(required=false) @DateTimeFormat(pattern="dd/MM/yyyy") Date desde,
									   @RequestParam(required=false) @DateTimeFormat(pattern="dd/MM/yyyy") Date hasta){
		
		List<Producto> productos = null;
			
		if(min == null && desde == null) {
			productos = productoServices.getAll();
		} else {
			if(min != null && max != null) {
				productos = productoServices.getBetweenPriceRange(min, max);
			} else {
				productos = productoServices.getBetweenDates(desde, hasta);
			}
		}
		
		return productos;
		
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Producto producto, UriComponentsBuilder uriComponentsBuilder) {
		
		Producto createdProducto = productoServices.create(producto);
		
		return ResponseEntity
				.created(uriComponentsBuilder.path("/productos/{codigo}").build(createdProducto.getCodigo()))
				.body(createdProducto);
	}
	
	@GetMapping("/{codigo}")
	public Producto getByCodigo(@PathVariable("codigo") Integer codigo) {
		return productoServices.read(codigo);
	}
	
	@PutMapping
	public ResponseEntity<?> update(Producto producto) {
		
		boolean actualizado = productoServices.update(producto);
		
		if(!actualizado) {
			throw new RuntimeException("No se ha podido actualizar el producto " + producto.getCodigo());
		}
		
		return ResponseEntity.status(204).build();
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)		// new!
	public void delete(@PathVariable("codigo") Integer codigo) {
		
		boolean eliminado = productoServices.delete(codigo);
		
		if(!eliminado) {
			throw new RuntimeException("El producto " + codigo + " no existe. No se ha podido eliminar.");
		}
	}
		
}
