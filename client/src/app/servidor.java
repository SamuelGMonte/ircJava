package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class servidor {

    public static void main(String[] args) {
        try {
            createServerSocket socketClass = new createServerSocket();
            socketClass.setPort(12345);
            int porta = socketClass.getPort();
            ServerSocket serverSocket = new ServerSocket(porta);

            System.out.println("Servidor aguardando na porta 12345");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente de ip " + clientSocket.getInetAddress() + " conectado.");
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                String[] commands = {"help", "nick"};
                Set<String> validCommands = new HashSet<>(Arrays.asList("help", "nick"));
                String texto;
                String nick = null;

                try {
                    while ((texto = reader.readLine()) != null) {
                        if (nick == null) {
                            int index = texto.indexOf('\n');
                            if(index != -1) {
                                nick = texto.substring(0, index).trim();
                                System.out.println("nick como: " + nick);
                            }
                            else {
                                System.out.println(nick + " disse " + texto.substring(nick.length()));
                            }
                        }
                        if(validCommands.contains(texto))
                            if (texto.startsWith("help")) {
                                System.out.println("Comandos disponiveis:");
                                System.out.println("help");
                                System.out.println("nick");
                                continue;
                            }


                    }
                } catch (IOException e) {
                    System.out.println("Cliente de IP " + clientSocket.getInetAddress() + " desconectado forçadamente.");
                }
                reader.close();
                clientSocket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

