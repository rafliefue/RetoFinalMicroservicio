package com.everis.bootcamp.cloudConfigServer.endPoints;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.everis.bootcamp.Persona;


@Component
@Endpoint(id = "personas")
public class ListaPersonasEndpoint {
	
	private List<Persona> personas = new ArrayList<>();
	
	@ReadOperation
	public List<Persona> Personas(){
		return personas;
	}
	
	@WriteOperation
	public void writeOperation(@Selector Persona nuevaPersona){
		personas.add(nuevaPersona);
	}
	
	
	@DeleteOperation
	public void deleteOperation(@Selector Persona nuevaPersona){
		personas.remove(nuevaPersona);
	}
	
	
	

}
