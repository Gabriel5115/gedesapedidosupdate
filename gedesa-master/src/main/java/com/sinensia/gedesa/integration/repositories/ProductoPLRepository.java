package com.sinensia.gedesa.integration.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.integration.model.FamiliaPL;
import com.sinensia.gedesa.integration.model.ProductoPL;

@Repository
public interface ProductoPLRepository extends JpaRepository<ProductoPL, Integer>{

	List<ProductoPL> findByFechaAltaBetweenOrderByCodigo(Date desde, Date hasta);
	List<ProductoPL> findByPrecioBetweenOrderByCodigo(double min, double max);
	List<ProductoPL> findByFamilia(FamiliaPL familia);
	
	@Query("SELECT COUNT(p) FROM ProductoPL p WHERE p.familia = :familia")
	int getNumerProductosByFamilia(FamiliaPL familia);
	
	@Query("SELECT p.familia, COUNT(p.familia) FROM ProductoPL p GROUP BY p.familia")
	List<Object[]> getCantidadProductosAgrupadosPorFamilia();
	
	@Query("SELECT p.familia, SUM(p.familia) FROM ProductoPL p GROUP BY p.familia")
	List<Object[]> getPrecioMedioProductosAgrupadosPorFamilia();

}
