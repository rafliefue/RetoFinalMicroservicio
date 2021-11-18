package com.everis.bootcamp.cloudConfigServer.util;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class Simulador{
	
	private final static Logger logger = LoggerFactory.getLogger(Simulador.class);
	
	RestTemplate rtplate = new RestTemplate();
	
	String url = "http://localhost:8080/";
	
	public String clientes() {
		String resourceUrl = url + "clientes";	
		ResponseEntity<String> res = rtplate.getForEntity(URI.create(resourceUrl), String.class);
		logger.info("Acceso a /clientes");
		return res.getBody();
	}
	
	public String usuarios() {
		String resourceUrl = url + "usuarios";	
		ResponseEntity<String> res = rtplate.getForEntity(URI.create(resourceUrl), String.class);
		logger.info("Acceso a /usuarios");
		return res.getBody();
	}
	
	public String empleados() {
		String resourceUrl = url + "empleados";	
		ResponseEntity<String> res = rtplate.getForEntity(URI.create(resourceUrl), String.class);
		logger.info("Acceso a /empleados");
		return res.getBody();
	}
	
	
}

