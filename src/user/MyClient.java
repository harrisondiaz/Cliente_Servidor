package user;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class MyClient {
    private static final int BUFFER_SIZE = 8192;

    private void run(){
        byte [] data = new byte[BUFFER_SIZE];

        DataInputStream input = null;
        DataOutputStream output = null;
        Socket socket = null;

        try {

            socket = new Socket("localhost", 123);
            System.out.println("Conectado con: " + socket.getRemoteSocketAddress());


            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
             if(input.readUTF().equals("Por favor espere a conectarse")){
                 System.out.println("Ups! No podemos conectarte ");
                 new IOException();
             }else{
                 while (true) {
                     Scanner sc = new Scanner(System.in);
                     System.out.println("Mensaje:");
                     String phrase = sc.nextLine();
                     phrase = new String(phrase.getBytes(), StandardCharsets.UTF_8);
                     output.writeUTF(phrase);
                 }
             }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Conexion con el servidor cerrada \n Intentaremos conectarte");
            try {
                Thread.sleep(15000);
            } catch (InterruptedException ex) {
                System.out.println("Ups! se fue");
            }
            run();

        }finally {
            try {
                if (input != null) {
                    input.close();
                }

                if (output != null) {
                    output.close();
                }

                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        new MyClient().run();
    }
}
