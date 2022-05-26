package com.sinensia.gedesa.integration.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sinensia.gedesa.integration.model.PedidoPL;

@Repository
public interface PedidoPLRepository extends JpaRepository<PedidoPL, Long> {

	List<PedidoPL> findByClienteIdentificadorFiscalOrderByClienteIdentificadorFiscal(String identificadorFiscal);

}
