package com.sinensia.gedesa.business.services;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;


public interface ProductoServices {

	/**
	 * Devuelve el producto con el codigo otorgado por el sistema
	 *
	 */
	Producto create(Producto producto);		// CREATE
	
	Producto read(int codigo);				// READ
	
	/**
	 * El producto requiere llevar un codigo existente
	 * 
	 * Si no se puede actualizar devuelve false
	 *
	 */
	boolean update(Producto producto);		// UPDATE
	
	boolean delete(int codigo);				// DELETE
	
	List<Producto> getAll();
	
	/**
	 * Incluye los extemos
	 *
	 */
	List<Producto> getBetweenPriceRange(double min, double max);
	
	/**
	 * Incluye los extemos
	 *
	 */
	List<Producto> getBetweenDates(Date desde, Date hasta);
	
	List<Producto> getByFamilia(Familia familia);
	
	int getNumeroTotalProductos();
	int getNumeroTotalProductosByFamilia(Familia familia);
	
	Map<Familia, Integer> getNumeroProductosAgrupados();
	Map<Familia, Double> getPreciosMediosAgrupados();
	
}
