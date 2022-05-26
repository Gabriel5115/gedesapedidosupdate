package com.sinensia.gedesa.integration.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="PRODUCTOS")
public class ProductoPL implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer codigo;
	
	private String nombre;
	
	@Temporal(TemporalType.DATE)
	private Date fechaAlta;
	
	private double precio;
	
	@Enumerated(EnumType.STRING)
	private FamiliaPL familia;
	
	private boolean descatalogado;
	
	public ProductoPL() {
		
	}

	public ProductoPL(Integer codigo, String nombre, Date fechaAlta, double precio, FamiliaPL familia) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.fechaAlta = fechaAlta;
		this.precio = precio;
		this.familia = familia;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public FamiliaPL getFamilia() {
		return familia;
	}

	public void setFamilia(FamiliaPL familia) {
		this.familia = familia;
	}

	public boolean isDescatalogado() {
		return descatalogado;
	}

	public void setDescatalogado(boolean descatalogado) {
		this.descatalogado = descatalogado;
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
		ProductoPL other = (ProductoPL) obj;
		return Objects.equals(codigo, other.codigo);
	}

	@Override
	public String toString() {
		return "Producto [codigo=" + codigo + ", nombre=" + nombre + ", fechaAlta=" + fechaAlta + ", precio=" + precio
				+ ", familia=" + familia + ", descatalogado=" + descatalogado + "]";
	}
	
}
