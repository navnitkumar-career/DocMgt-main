package com.doc;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DocumentMgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentMgtApplication.class, args);
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return  new ModelMapper();
	}

}
