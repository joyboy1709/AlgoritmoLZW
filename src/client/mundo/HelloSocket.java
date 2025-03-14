package client.mundo;
/**
 *
 * @author santi
 */
import Algoritmo.LZW;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

public class HelloSocket implements Runnable {
    private LZW lzw;

    public HelloSocket() {
        lzw = new LZW();
        Thread treadListener = new Thread(this);
        treadListener.start();
    }

    public void socket(String msg) {
        try {
            Socket client = new Socket("192.168.1.25", 5000); // portSend 5000
            DataOutputStream outBuffer = new DataOutputStream(client.getOutputStream());
            byte[] compressedMsg = lzw.compress(msg); // Comprimir el mensaje antes de enviarlo
            outBuffer.writeInt(compressedMsg.length);
            outBuffer.write(compressedMsg);
            client.close();
        } catch (UnknownHostException e) {
            JOptionPane.showMessageDialog(null, "Client: socket(1) : UnknownHostException: " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Client: socket(2) : IOException: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        Socket socket;
        DataInputStream inDataBuffer;

        try {
            serverSocket = new ServerSocket(5050); // portListen 5050

            while (true) {
                socket = serverSocket.accept();
                inDataBuffer = new DataInputStream(socket.getInputStream());
                int length = inDataBuffer.readInt();
                byte[] compressedMsg = new byte[length];
                inDataBuffer.readFully(compressedMsg);
                String msg = lzw.decompress(compressedMsg); // Descomprimir el mensaje recibido
                System.out.println(msg);
                socket.close();
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Client run() : IOException: " + e.getMessage());
        }
    }
}