package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class servidor {

    public static void main(String[] args) {

        try {
            createServerSocket socketClass = new createServerSocket();
            socketClass.setPort(12345);
            int porta = socketClass.getPort();
            ServerSocket serverSocket = new ServerSocket(porta);

            System.out.println("Servidor aguardando conexão na porta 12345");
            String nickname = "";
            String lastMessage = null;
            User user = new User();
            user.setUserNick(nickname);
            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente de ip " + clientSocket.getInetAddress() + " conectado.");
                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                try {
                    String nick = reader.readLine();
                    while (true) {
                        String texto = reader.readLine();
                        String[] tokens = texto.split(" ");
                        if(texto.isEmpty()) {
                            continue;
                        }
                        else if(texto.startsWith("/nick")) {
                            if(tokens.length > 1) {
                                String newNick = tokens[1];
                                if (!validadeNick.existNick(user, newNick)) {
                                    user.setUserNick(newNick);
                                    nick = user.getUserNick();
                                } else {
                                    System.out.println("Nickname inválido");
                                }
                            }
                            else {
                                System.out.println("Nickname ja existente");
                            }
                        }
                        else if(!texto.equals(nick)) {
                            System.out.println(nick + " disse " + texto);
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

