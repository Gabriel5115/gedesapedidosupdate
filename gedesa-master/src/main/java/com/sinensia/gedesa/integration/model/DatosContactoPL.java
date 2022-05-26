package com.sinensia.gedesa.integration.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class DatosContactoPL implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String telefono;
	private String movil;
	private String email;
	
	public DatosContactoPL() {
		
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "DatosContacto [telefono=" + telefono + ", movil=" + movil + ", email=" + email + "]";
	}

}
