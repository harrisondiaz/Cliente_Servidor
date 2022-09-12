package user;

import java.io.*;
import java.net.Socket;

public class MyClientThread extends Thread{

    private Socket cliente = null;
    private DataOutputStream output = null;
    private DataInputStream input = null;
    private boolean stop = false;
    private static final int BUFFER_SIZE = 8192;

    //Constructor, aquí pasamos el socket obtenido en la clase Servidor como resultado del método accept()
    public MyClientThread(Socket cliente) {
        this.cliente = cliente;
    }

    public void run() {
        // Obtenemos los flujos
        try {
            output = new DataOutputStream(this.cliente.getOutputStream());
            input = new DataInputStream(this.cliente.getInputStream());
            // Creamos un buffer de 8KB
            byte[] data = new byte[BUFFER_SIZE];

            while (!stop) {
                String phrase = input.readUTF();
                System.out.println(phrase);

                output.writeUTF(phrase);


            }

        } catch (IOException e) {
            System.out.println("Conexion con cliente: " + cliente.getRemoteSocketAddress() + " cerrada");
        }finally {
            parar();
        }

        System.out.println("Thread finalizado");

    }

    public void parar() {
        stop = true;

        try {
            if (output != null) {
                output.close();
            }

            if (input != null) {
                input.close();
            }

            if (cliente != null) {
                cliente.close();
            }
        }catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}


