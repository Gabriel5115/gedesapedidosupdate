package com.sinensia.gedesa.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.integration.model.ClientePL;

@Repository
public interface ClientePLRepository extends JpaRepository<ClientePL, String>{

	List<ClientePL> findByNombreLike(String nombre);
	
	@Query("SELECT c "
		+ " FROM ClientePL c "
		+ " WHERE UPPER(c.direccion.poblacion) LIKE UPPER(CONCAT('%',:poblacion,'%')) "
		+ " ORDER BY c.direccion.poblacion ")
	List<ClientePL> findByPoblacionLike(String poblacion);
	
	@Query("SELECT c FROM ClientePL c WHERE c NOT IN (SELECT p.cliente FROM PedidoPL p)")
	List<ClientePL> findClientesSinPedidos();
	
}
