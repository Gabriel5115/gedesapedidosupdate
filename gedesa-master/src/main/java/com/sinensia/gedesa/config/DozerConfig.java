package com.sinensia.gedesa.config;

import java.util.Arrays;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DozerConfig {

	@Bean
    public DozerBeanMapper getMapper() {
        
		DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
		List<String> mappingFiles = Arrays.asList("dozer-configuration-mapping.xml");
        dozerBeanMapper.setMappingFiles(mappingFiles);
        
        return dozerBeanMapper;
    }
	
}
