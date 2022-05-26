package com.sinensia.gedesa.presentation.controllers.exceptiosnhandlers;

import java.io.Serializable;

public class HttpErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
	
	public HttpErrorResponse(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	@Override
	public String toString() {
		return "MensajeError [mensaje=" + mensaje + "]";
	}

}
