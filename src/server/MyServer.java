package server;

import user.MyClientThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread {
    private int puerto;
    private boolean stop = false;

    public MyServer(int puerto) {
        this.puerto = puerto;
    }


    public int getPuerto() {
        return puerto;
    }


    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public void stopServer() {
        this.stop = true;
    }

    public void run() {
        ServerSocket servidor = null;
        try {
            servidor = new ServerSocket(this.puerto);
            System.out.println("Esperando conexiones en el puerto " + this.puerto);

            while (!stop) {
                Socket nuevoCliente = servidor.accept();
                MyClientThread tNuevoCliente = new MyClientThread(nuevoCliente);
                tNuevoCliente.start();
            }


            servidor.close();
            System.out.println("Servidor cerrado correctamente");

        } catch (IOException e) {
            System.out.println("Servidor cerrado abruptamente");
            e.printStackTrace();
        }finally {

            if (servidor != null) {
                try {
                    servidor.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new MyServer(123).run();
    }
}




