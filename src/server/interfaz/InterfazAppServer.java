package server.interfaz;
/**
 *
 * @author santi
 */
import client.interfaz.InterfazAppClient;
import server.controlador.Controlador;

public class InterfazAppServer
{
 /* Relaciones */
  private Controlador ctrl;
  
 /* Constructor */
  public InterfazAppServer(Controlador ctrl)
  { this.ctrl = ctrl;
    System.out.println("Server listening...");	
  }
	
  public static void main(String[] args) 
  { new InterfazAppServer(new Controlador()); 
  }

}
