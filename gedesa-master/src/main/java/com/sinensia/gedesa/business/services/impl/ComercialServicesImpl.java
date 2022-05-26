package com.sinensia.gedesa.business.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinensia.gedesa.business.model.Comercial;
import com.sinensia.gedesa.business.services.ComercialServices;
import com.sinensia.gedesa.integration.model.ComercialPL;
import com.sinensia.gedesa.integration.repositories.ComercialPLRepository;

@Service
public class ComercialServicesImpl implements ComercialServices{

	@Autowired
	private ComercialPLRepository comercialPLRepository;
	
	@Autowired
	private DozerBeanMapper mapper;

	@Override
	@Transactional
	public Comercial create(Comercial comercial) {
		
		final ComercialPL comercialPL = mapper.map(comercial, ComercialPL.class);
		final ComercialPL createdComercialPL = comercialPLRepository.save(comercialPL);
		
		return mapper.map(createdComercialPL, Comercial.class);
	}

	@Override
	public Comercial read(int codigo) {
		
		Optional<ComercialPL> optional = comercialPLRepository.findById(codigo);

		return optional.isPresent() ? mapper.map(optional.get(), Comercial.class) : null;
	}

	@Override
	public List<Comercial> getAll() {
		
		return comercialPLRepository.findAll().stream()
				.map(x -> mapper.map(x, Comercial.class))
				.collect(Collectors.toList());
	}

}
