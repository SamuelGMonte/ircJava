package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

    public static void main(String[] args) {
        ServerSocket socketClass;

        try {
            socketClass = new ServerSocket(12345);

            System.out.println("Servidor aguardando conexão na porta 12345");
            String nickname = "";
            String nick = "";
            String lastMessage = null;
            User user = new User();
            user.setUserNick(nickname);
            while(true) {
                Socket clientSocket = socketClass.accept();
                System.out.println("Cliente de ip " + clientSocket.getInetAddress() + " conectado.");

                ClienteThread clientSock = new ClienteThread(clientSocket);
                new Thread(clientSock).start();

                InputStream inputStream = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                

                nick = reader.readLine();
                if (nick.contains("/nick")) {
                    nick.replaceAll("/nick", "");
                }

                String texto = reader.readLine();
                String[] tokens = texto.split(" ");

                if(texto.startsWith("/nick")) {
                    if(tokens.length > 1) {
                        String newNick = tokens[1];
                        if (!validadeNick.existNick(user, newNick)) {
                            user.setUserNick(nick);
                            nickname = user.getUserNick();
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
    
                reader.close();
                clientSocket.close();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

