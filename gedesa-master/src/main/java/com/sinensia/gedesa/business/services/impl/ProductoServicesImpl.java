package com.sinensia.gedesa.business.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Familia;
import com.sinensia.gedesa.business.model.Producto;
import com.sinensia.gedesa.business.services.ProductoServices;
import com.sinensia.gedesa.integration.model.FamiliaPL;
import com.sinensia.gedesa.integration.model.ProductoPL;
import com.sinensia.gedesa.integration.repositories.ProductoPLRepository;

@Service
public class ProductoServicesImpl implements ProductoServices{

	@Autowired
	private ProductoPLRepository productoPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;
	
	@Override
	@Transactional
	public Producto create(Producto producto) {
		
		ProductoPL productoPL = mapper.map(producto, ProductoPL.class);
		ProductoPL createdProductoPL = productoPLRepository.save(productoPL);
		
		return mapper.map(createdProductoPL, Producto.class);
	}

	@Override
	public Producto read(int codigo) {
		
		Optional<ProductoPL> optional = productoPLRepository.findById(codigo);
		
		return optional.isPresent() ? mapper.map(optional.get(), Producto.class) : null;
	}

	@Override
	@Transactional
	public boolean update(Producto producto) {
		
		boolean exists = productoPLRepository.existsById(producto.getCodigo());
		
		if(!exists) {
			throw new IllegalStateException("No existe el producto con codigo: " + producto.getCodigo());
		}
		
		ProductoPL updatedProductoPL = productoPLRepository.save(mapper.map(producto, ProductoPL.class));
		
		return updatedProductoPL != null;
	}

	@Override
	@Transactional
	public boolean delete(int codigo) {
		
		boolean existsBefore = productoPLRepository.existsById(codigo);
			
		productoPLRepository.deleteById(codigo);
		
		boolean existsAfter = productoPLRepository.existsById(codigo);
		
		return existsBefore && !existsAfter;
	}

	@Override
	public List<Producto> getAll() {
		return convertListToBusiness(productoPLRepository.findAll());  		
	}

	@Override
	public List<Producto> getBetweenPriceRange(double min, double max) {
		return convertListToBusiness(productoPLRepository.findByPrecioBetweenOrderByCodigo(min, max));	
	}

	@Override
	public List<Producto> getBetweenDates(Date desde, Date hasta) {
		return convertListToBusiness(productoPLRepository.findByFechaAltaBetweenOrderByCodigo(desde, hasta));
	}

	@Override
	public List<Producto> getByFamilia(Familia familia) {
		
		FamiliaPL familiaPL = FamiliaPL.valueOf(familia.toString());
		
		return convertListToBusiness(productoPLRepository.findByFamilia(familiaPL));
	}

	@Override
	public int getNumeroTotalProductos() {
		return (int) productoPLRepository.count();
	}

	@Override
	public int getNumeroTotalProductosByFamilia(Familia familia) {
		
		FamiliaPL familiaPL = FamiliaPL.valueOf(familia.toString());
		
		return productoPLRepository.getNumerProductosByFamilia(familiaPL);
	}

	@Override
	public Map<Familia, Integer> getNumeroProductosAgrupados() {
		
		Map<Familia, Integer> mapaResultados = new HashMap<>();
		mapaResultados.put(Familia.HARDWARE, null);
		mapaResultados.put(Familia.SOFTWARE, null);
		mapaResultados.put(Familia.CONSUMIBLES, null);
		
		List<Object[]> resultados = productoPLRepository.getCantidadProductosAgrupadosPorFamilia();
		
		resultados.stream().forEach(x -> {
			
			Familia familia = Familia.valueOf(((FamiliaPL) x[0]).toString())  ;
			Integer cantidad = (Integer) x[1];
			
			mapaResultados.replace(familia, cantidad);		
		});
		
		return mapaResultados;
	}

	@Override
	public Map<Familia, Double> getPreciosMediosAgrupados() {
		
		Map<Familia, Double> mapaResultados = new HashMap<>();
		mapaResultados.put(Familia.HARDWARE, null);
		mapaResultados.put(Familia.SOFTWARE, null);
		mapaResultados.put(Familia.CONSUMIBLES, null);
		
		List<Object[]> resultados = productoPLRepository.getPrecioMedioProductosAgrupadosPorFamilia();
		
		resultados.stream().forEach(x -> {
			
			Familia familia = Familia.valueOf(((FamiliaPL) x[0]).toString())  ;
			Double precioMedio = (Double) x[1];
			
			mapaResultados.replace(familia, precioMedio);		
		});
		
		return mapaResultados;
	}
	
	// *****************************************************************
	//
	// PRIVATED METHODS
	//
	// *****************************************************************
	
	private List<Producto> convertListToBusiness(List<ProductoPL> productosPL){
		
		return productosPL
				.stream()
				.map(x -> mapper.map(x, Producto.class))
				.collect(Collectors.toList());
	}

}
