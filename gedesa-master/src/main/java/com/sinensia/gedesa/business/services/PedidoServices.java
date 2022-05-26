package com.sinensia.gedesa.business.services;

import java.util.List;

import com.sinensia.gedesa.business.model.Pedido;
import com.sinensia.gedesa.integration.model.EstadoPL;

public interface PedidoServices {

	Pedido create(Pedido pedido);
	
	Pedido read(long codigo);
	
	List<Pedido> getAll();
	
	List<Pedido> getByIdentificadorFiscalCliente(String identificadorFiscal);
	
	boolean UpdateEstadoById(Long id, EstadoPL estado);
//	List<Pedido> getByIdComercial(int id);
//	List<Pedido> getByIdentificadorFiscalCliente(String identificadorFiscal, Date desde, Date hasta);
//  List<Pedido> getLatestOrderByFechaAltaDesc(int n);	
	
	
//	List<LineaPedido> getLineasPedidoBetweenDates(Date desde, Date hasta);
//	List<LineaPedido> getLineasPedidoByCliente(Cliente cliente);
//	List<LineaPedido> getLineasPedidoByCliente(String identificadorFiscal);
	
}
