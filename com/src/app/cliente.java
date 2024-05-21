package app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;


public class Cliente {
    public static void main(String[] args) {
        CreateServerSocket socketClass = new CreateServerSocket();

        try {

            InetAddress localHost = InetAddress.getLocalHost();
            // String ipAddress = localHost.getHostAddress();

            socketClass.setPort(12345);
            // System.out.println(ipAddress);
            int port = socketClass.getPort();

            // String ipAddressStr = socketClass.getIpStr();
            socketClass.setIp(localHost);
            System.out.println(localHost.getHostAddress());
            Socket clientSocket = new Socket(localHost.getHostAddress(), port);

            OutputStream outputStream = clientSocket.getOutputStream();

            OutputStreamWriter outputStreamWriter  = new OutputStreamWriter(outputStream);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            Scanner scanner = new Scanner(System.in);
            System.out.println("Bem vindo! Digite /nick para colocar um nickname: ");
            String nick = "";
            while((nick = scanner.nextLine()) != null) {
                if(nick.startsWith("/nick") ) {
                    System.err.println("Seu Nick é: " + nick);
                    break;
                }
                else {
                    System.err.println("Saindo... Nick não fornecido.");
                }
            }
            while(true) {
                System.out.println("envie a mensagem: ");
                String mensagem = scanner.nextLine();

                if (mensagem.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.write(nick);
                writer.newLine();
                writer.write(mensagem);
                writer.newLine();
                writer.flush();
            }
            scanner.close();
            writer.close();
            outputStreamWriter.close();
            outputStream.close();
            clientSocket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}