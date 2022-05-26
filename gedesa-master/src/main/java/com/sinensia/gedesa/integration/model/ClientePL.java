package com.sinensia.gedesa.integration.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENTES")
public class ClientePL implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="ID")
	private String identificadorFiscal;
	
	private String nombreComercial;
	private String nombre;
	private String apellido1;
	private String apellido2;
	
	@Embedded
	private DireccionPL direccion;
	
	@Embedded
	private DatosContactoPL datosContacto;

	public ClientePL() {
		
	}

	public String getIdentificadorFiscal() {
		return identificadorFiscal;
	}
	
	public void setIdentificadorFiscal(String identificadorFiscal) {
		this.identificadorFiscal = identificadorFiscal;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public DireccionPL getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionPL direccion) {
		this.direccion = direccion;
	}
	
	public DatosContactoPL getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContactoPL datosContacto) {
		this.datosContacto = datosContacto;
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
		ClientePL other = (ClientePL) obj;
		return Objects.equals(identificadorFiscal, other.identificadorFiscal);
	}

	@Override
	public String toString() {
		return "Cliente [identificadorFiscal=" + identificadorFiscal + ", nombreComercial=" + nombreComercial
				+ ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2 + ", direccion="
				+ direccion + ", datosContacto=" + datosContacto + "]";
	}

}
