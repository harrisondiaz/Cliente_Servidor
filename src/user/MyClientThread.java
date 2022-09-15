package user;

import server.MyConexion;

import java.io.*;
import java.net.Socket;

public class MyClientThread extends Thread{

    private Socket cliente = null;
    private DataOutputStream output;
    private DataInputStream input;
    private boolean stop = false;
    private static final int BUFFER_SIZE = 8192;

    public MyClientThread(Socket cliente) throws IOException {
        this.cliente = cliente;
        input = new DataInputStream(cliente.getInputStream());
        output = new DataOutputStream(cliente.getOutputStream());
    }

    public DataInputStream getInput(){
        return input;
    }

    public DataOutputStream getOutput(){
        return output;
    }

    public void sentMsg(String phrase) throws IOException {
        output.writeUTF(phrase);
    }


    public void run() {

        try {


            byte[] data = new byte[BUFFER_SIZE];

            while (!stop) {
                String phrase = input.readUTF();
                System.out.println(phrase);

            }

        } catch (IOException e) {
            System.out.println("Conexion con cliente: " + cliente.getRemoteSocketAddress() + " cerrada");
        }finally {
            parar();
        }

        System.out.println("Por fin se fue >:v");

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


