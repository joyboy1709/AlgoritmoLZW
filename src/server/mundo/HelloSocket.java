package server.mundo;
/**
 *
 * @author santi
 */
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class HelloSocket implements Runnable 
{
	/* Constructor */
	 public HelloSocket()
	 { Thread treadListener = new Thread(this);
	   treadListener.start();		 
	 }
	
    /* Server:Data >> Socket >> Client */	
	 public static void socket(String msg)
	 { try 
	   { Socket server = new Socket ("192.168.1.25", 5050 ); // portSend 5050
	     DataOutputStream outBuffer = new DataOutputStream (server.getOutputStream() );
	     outBuffer.writeUTF(msg);
	     server.close();
	   } 
	   catch (UnknownHostException e) 
	   { JOptionPane.showMessageDialog(null, "Server : socket() : UnknownHostException: " + e.getMessage()); } 
	   catch (IOException e) 
	   { JOptionPane.showMessageDialog(null, "Server : socket() : IOException: " + e.getMessage()); }
	 }

  	 @Override
	 /* Server: Listen */	
	  public void run() 
	  { ServerSocket serverSocket;
		Socket socket;
		DataInputStream inDataBuffer;
		  
		try 
		{ serverSocket = new ServerSocket(5000); // portListen 5000
			
		  while (true)	 
		  { socket = serverSocket.accept();
		    inDataBuffer = new DataInputStream(socket.getInputStream());
			String msg = inDataBuffer.readUTF();
			System.out.println(msg);
			response("Server: Welcome to the world client/server...");	
			socket.close();
		  } 
		  
		} 
		catch (IOException e) 
		{ JOptionPane.showMessageDialog(null, "Server : run (): IOException: " + e.getMessage());
		}				
	  }
		
	  public static void response(String msg)
	  { socket(msg);
	  }
}
