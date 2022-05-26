package com.sinensia.gedesa.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
@Aspect
public class LogAspectConfig {

	//TODO
	
	// 1.- Crear un interceptor para la capa de presentación a imagen y semejanza del que tenemos para business
	// 2.- Investigat cómo extraer información de los argumentos que se están pasando al método interceptado (JoinPoint)
	// 3.- OPCIONAL: Interceptar el valor de retorono (si lo hubiera) del método interceptado
	
	private Logger logger = LoggerFactory.getLogger(LogAspectConfig.class);
	
	@Before(value="execution(* com.sinensia.gedesa.business.services.impl.*.*(..))")  // PointCut expresado mediante AspectJ
	public void logBusinessLayer(JoinPoint joinPoint) {
		
		String clase = joinPoint.getTarget().getClass().getSimpleName();
		String signature = joinPoint.getSignature().getName();
		
		logger.info("Execution: [{}] de la clase [{}]", signature, clase);
		
	}
	
}
