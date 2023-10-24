package app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.Scanner;


public class cliente {
    public static void main(String[] args) {
        createServerSocket socketClass = new createServerSocket();

        try {

            InetAddress localHost = InetAddress.getLocalHost();
            String ipAddress = localHost.getHostAddress();

            socketClass.setIpStr(ipAddress);
            socketClass.setPort(12345);
            int port = socketClass.getPort();
            String ipAddressStr = socketClass.getIpStr();

            Socket clientSocket = new Socket(ipAddressStr, port);

            OutputStream outputStream = clientSocket.getOutputStream();

            OutputStreamWriter outputStreamWriter  = new OutputStreamWriter(outputStream);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

            Scanner scanner = new Scanner(System.in);
            System.out.println("Bem vindo! Digite /nick para colocar um nickname: ");
            String nick = "";
            while(true) {
                nick = scanner.nextLine();
                if(nick.startsWith("/nick") ) {
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