package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ClienteThread extends Thread {
    public final Socket clientSocket;

    public ClienteThread(Socket socket) {
        this.clientSocket = socket;
    }

    PrintWriter out = null;
    BufferedReader in = null;

    public void run()  {
         try {
            out = new PrintWriter(clientSocket.getOutputStream(), true); 
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String line;
            if((line = in.readLine()) == "/sair") {
                System.out.println("Cliente saiu");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        finally { 
            try { 
                if (out != null) { 
                    out.close(); 
                } 
                if (in != null) { 
                    in.close(); 
                    clientSocket.close(); 
                } 
            } 
            catch (IOException e) { 
                e.printStackTrace(); 
            } 
        } 
    }
}
