package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyClient {
    public static void main(String[] args) {
        if(args.length==2) {
            String ip = args[0];
            int port = Integer.parseInt(args[1]);
            Scanner scanner = new Scanner(System.in);
            System.out.println("Client");
            for (int i = 0; i < 50; i++) {
                try {
                    Socket socket = new Socket(ip, port);
                    DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    System.out.println("Digita un numero: ->");
                    int data = Integer.parseInt(scanner.next());
                            System.out.println("Numero Enviado : " + data);
                    dataOutputStream.writeInt(data);
                    System.out.println("Recibi : " + dataInputStream.readInt());
                    dataInputStream.close();
                    dataOutputStream.close();
                    socket.close();
                } catch (IOException e) {
                    System.out.println("chale no me conecte");
                }
            }
        }else{
            System.out.println("Parametros invalidos, se debe intentar con el parametro 186.114.217.12383 12383");
        }
    }
}

