package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {

    public static void main(String[] args) {

            int port = 90;
            boolean running = true;
            MyServer myServer = new MyServer();
            System.out.println("Server: ");
            try {
                ServerSocket serverSocket = new ServerSocket(port);
                System.out.println("Host ip: " + serverSocket.getInetAddress().getHostAddress() + " Port " + serverSocket.getLocalPort());
                while (running) {

                    System.out.println("Esperando respuesta....");
                    Socket socket = serverSocket.accept();
                    System.out.println("Se conecto: " + socket.getInetAddress().getHostName());

                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    int data = dataInputStream.readInt();
                    System.out.println("Recibi : " + data);

                    dataInputStream.close();
                    dataOutputStream.close();
                }
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("Chale me desconecte: " + e);
            }

    }

}

