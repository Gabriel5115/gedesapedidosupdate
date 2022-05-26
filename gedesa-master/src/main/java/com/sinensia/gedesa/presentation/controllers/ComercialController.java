package com.sinensia.gedesa.presentation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinensia.gedesa.business.model.Comercial;
import com.sinensia.gedesa.business.services.ComercialServices;

@RestController
@RequestMapping("/comerciales")
public class ComercialController {

	@Autowired
	private ComercialServices comercialServices;
	
	@GetMapping
	public List<Comercial> geAll(){
		return comercialServices.getAll();
	}
	
	@GetMapping("/{id}")
	public Comercial getById(@PathVariable Integer id) {
		return comercialServices.read(id);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Comercial comercial){
		comercialServices.create(comercial);
		return ResponseEntity.ok().build();
	}
	
}
