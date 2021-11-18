package com.everis.bootcamp.cloudConfigServer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

import com.everis.bootcamp.Persona;
import com.everis.bootcamp.cloudConfigServer.endPoints.ListaPersonasEndpoint;
import com.everis.bootcamp.cloudConfigServer.util.Simulador;

@EnableConfigServer
@SpringBootApplication
public class MicroServiceApplication implements CommandLineRunner {

	@Autowired
	ListaPersonasEndpoint ep;
	
	public static void main(String[] args) {
		SpringApplication.run(MicroServiceApplication.class, args);
	}
	
	
		public void run(String... args) throws Exception {
			
			//Generar Personas
			
			List<Persona> lista = new ArrayList<>();
					
			Persona p1 = new Persona.Persona2Builder("Rafael", "Liebana Fuentes", "empleado").build();	
			lista.add(p1);
			ep.writeOperation(p1);
			
			Persona p2 = new Persona.Persona2Builder("Jose", "Gomez Cepeda", "empleado").build();	
			lista.add(p2);
			ep.writeOperation(p2);
			
			Persona p3 = new Persona.Persona2Builder("Victor", "Castillo Navarro", "empleado").build();	
			lista.add(p3);
			ep.writeOperation(p3);
		
			Persona p4 = new Persona.Persona2Builder("David", "Garcia Garcia", "cliente").build();	
			lista.add(p4);
			ep.writeOperation(p4);
			
			Persona p5 = new Persona.Persona2Builder("Gabriel", "Lopez Fuentes", "cliente").build();	
			lista.add(p5);
			ep.writeOperation(p5);
			
			Persona p6 = new Persona.Persona2Builder("Alejandro", "Valverde Gonzalez", "cliente").build();
			lista.add(p6);
			ep.writeOperation(p6);
			
			//Simulación de llamadas a diferentes url para ver en actuator/prometheus el numero de invocaciones
			
			Simulador s = new Simulador();
				
			//3
			s.clientes();
			s.clientes();
			s.clientes();
			//5
			s.empleados();
			s.empleados();
			s.empleados();
			s.empleados();
			s.empleados();
			
			//2
			s.usuarios();
			s.usuarios();
					
		}
		
	

}
