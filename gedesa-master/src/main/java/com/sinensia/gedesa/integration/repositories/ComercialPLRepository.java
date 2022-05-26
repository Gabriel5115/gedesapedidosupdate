package com.sinensia.gedesa.integration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.integration.model.ComercialPL;

@Repository
public interface ComercialPLRepository extends JpaRepository<ComercialPL, Integer>{

}
