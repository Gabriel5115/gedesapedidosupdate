package com.sinensia.gedesa.integration.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PEDIDOS")
public class PedidoPL implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaHora;
	
	@Enumerated(EnumType.STRING)
	private EstadoPL estado;
	
	@ManyToOne
	@JoinColumn(name="ID_COMERCIAL")
	private ComercialPL comercial;
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private ClientePL cliente;
	
	private String observaciones;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name="LINEAS_PEDIDO",
			   joinColumns = @JoinColumn(name="CODIGO_PEDIDO"))
	private List<LineaPedidoPL> lineas;
	
	
	
	public PedidoPL() {
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public ComercialPL getComercial() {
		return comercial;
	}

	public void setComercial(ComercialPL comercial) {
		this.comercial = comercial;
	}

	public ClientePL getCliente() {
		return cliente;
	}

	public void setCliente(ClientePL cliente) {
		this.cliente = cliente;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<LineaPedidoPL> getLineas() {
		return lineas;
	}

	public void setLineas(List<LineaPedidoPL> lineas) {
		this.lineas = lineas;
	}
	
	public EstadoPL getEstado() {
		return estado;
	}

	public void setEstado(EstadoPL estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		PedidoPL other = (PedidoPL) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "PedidoPL [codigo=" + codigo + ", fechaHora=" + fechaHora + ", estado=" + estado + ", comercial="
				+ comercial + ", cliente=" + cliente + ", observaciones=" + observaciones + ", lineas=" + lineas + "]";
	}

}
