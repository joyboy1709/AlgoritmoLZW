package client.controlador;
/**
 *
 * @author santi
 */

import client.mundo.HelloSocket;

public class Controlador
{   
 // Atributos
	
	
 // Relaciones 	
	private HelloSocket helloSocket;
	
 // Constructor	
	public Controlador()
	{ helloSocket = new HelloSocket(); 
	}
	
 // Recibe las referencias de los objetos a controlar en la interfaz	
	public void conectar()
	{ 
	}

 // --------------------------------------------------------------	
 // Implementacion de los requerimientos de usuario.	
 // --------------------------------------------------------------
	public void socket(String msg)
	{ helloSocket.socket(msg);		
	}
	
}