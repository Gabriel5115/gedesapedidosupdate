package com.sinensia.gedesa.business.services;

import java.util.List;

import com.sinensia.gedesa.business.model.Comercial;

public interface ComercialServices {

	Comercial create(Comercial comercial);
	
	Comercial read(int codigo);
	
	List<Comercial> getAll();
	
}
