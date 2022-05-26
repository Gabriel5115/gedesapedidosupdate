package com.sinensia.gedesa.presentation.controllers.exceptiosnhandlers;

import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	@ExceptionHandler(value={Exception.class})
	protected ResponseEntity<?> handle1(Exception e, WebRequest request){
		
		logger.error(e.getMessage());
		
		HttpErrorResponse httpErrorResponse = new HttpErrorResponse(e.getMessage());
		
		return handleExceptionInternal(e, httpErrorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
		
	}
	
	@ExceptionHandler(RuntimeException.class)
	protected ResponseEntity<?> handle2(Exception e, WebRequest request){
		
		logger.error(e.getMessage());
		
		HttpErrorResponse httpErrorResponse = new HttpErrorResponse(e.getMessage());
		
		return handleExceptionInternal(e, httpErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
}
