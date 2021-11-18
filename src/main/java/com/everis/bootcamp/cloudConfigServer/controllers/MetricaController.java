package com.everis.bootcamp.cloudConfigServer.controllers;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.everis.bootcamp.FiltradoPersonas;
import com.everis.bootcamp.Persona;
import com.everis.bootcamp.cloudConfigServer.endPoints.ListaPersonasEndpoint;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@RestController
public class MetricaController {
	
	private final static Logger logger = LoggerFactory.getLogger(MetricaController.class);
	
	List<Persona> lista;
	
	@Autowired
	FiltradoPersonas a;
	
	@Autowired
	ListaPersonasEndpoint ep;
	
	private Counter counterClientes;
	private Counter counterEmpleados;
	private Counter counterUsuarios;
	private Counter counterTotal;
	
	/**Aqui hago el contador que se podra ver en locoalhost:8888/actuator/prometheus**/

	public MetricaController(MeterRegistry registry) {
		this.counterClientes = Counter.builder("invocaciones.clientes").description("Invocaciones de /clientes").register(registry);
		this.counterEmpleados = Counter.builder("invocaciones.empleados").description("Invocaciones de /empleados").register(registry);
		this.counterUsuarios = Counter.builder("invocaciones.usuarios").description("Invocaciones de /usuarios").register(registry);
		this.counterTotal = Counter.builder("invocaciones.total").description("Invocaciones de totales").register(registry);
	}
		
	@GetMapping("/")
	public ResponseEntity<String> index(){
		
		return new ResponseEntity<String>(HttpStatus.OK).ok("index");
	}
	
	@GetMapping("/clientes")
	public ResponseEntity<String> clientes(){
		
		//Incremento del contador
		counterClientes.increment();
		counterTotal.increment();
		
		List<Persona> todos = ep.Personas();
		
		List<Persona> empleados = a.filtrado("cliente", todos);
		
		List<String> lista = new ArrayList<>();
		empleados.stream().forEach(x -> lista.add(x.getNombre() + " " + x.getApellidos()));
		
		return new ResponseEntity<String>(HttpStatus.OK).ok("CLIENTES:" + lista.toString());
	}
	
	@GetMapping("/empleados")
	public ResponseEntity<String> empleados(){
		
		//Incremento del contador
		counterEmpleados.increment();
		counterTotal.increment();
		
		List<Persona> todos = ep.Personas();
		
		List<Persona> empleados = a.filtrado("empleado", todos);
		
		List<String> lista = new ArrayList<>();
		empleados.stream().forEach(x -> lista.add(x.getNombre() + " " + x.getApellidos()));
		
		return new ResponseEntity<String>(HttpStatus.OK).ok("EMPLEADOS:" + lista.toString());
	}
	
	@GetMapping("/usuarios")
	public ResponseEntity<String> usuarios(){
		
		//Incremento del contador
		counterUsuarios.increment();
		counterTotal.increment();
		
		List<Persona> todos = ep.Personas();
		
		List<String> lista = new ArrayList<>();
		todos.stream().forEach(x -> lista.add(x.getNombre() + " " + x.getApellidos()));
		
		return new ResponseEntity<String>(HttpStatus.OK).ok("TODOS LOS USUARIOS:" + lista.toString());
	}
}
