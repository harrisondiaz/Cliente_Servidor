package server;

import user.MyClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyConexion implements Runnable{

    private int puerto;
    private boolean stop = false;

    private static int counters = 0;
    private  int counter = 0;

    public MyConexion (int puerto) {
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

    @Override
    public void run() {
        ServerSocket servidor = null;
        System.out.println("Esperando conexiones en el puerto " + this.puerto);
        try {
            servidor = new ServerSocket(this.puerto);

            while (!stop) {
                Socket nuevoCliente = servidor.accept();
                MyClientThread tNuevoCliente = new MyClientThread(nuevoCliente);
                tNuevoCliente.start();
                System.out.println("Se ha conectado el cliente: "+nuevoCliente.getRemoteSocketAddress());
                MyServer myServer = new MyServer(nuevoCliente,tNuevoCliente.getInput(),tNuevoCliente.getOutput());
                counters++;
                counter++;
                if(counter < 5) {
                    myServer.start();
                }else if(!tNuevoCliente.isAlive()){
                    counter--;
                }else {
                    tNuevoCliente.sentMsg("Por favor espere a conectarse");
                }
            }


            servidor.close();
            System.out.println("Servidor cerrado correctamente");

        } catch (IOException e) {
            System.out.println("Servidor cerrado abruptamente");
            e.printStackTrace();
        } finally {

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
        MyConexion myConexion = new MyConexion(123);
        myConexion.run();
    }

}
