package server;

import user.MyClientThread;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends Thread {

    private Socket service;
    private DataInputStream input;
    private DataOutputStream output;

    public MyServer(Socket service,DataInputStream input,DataOutputStream output){
        this.service = service;
        this.input = input;
        this.output = output;
    }
    public void run() {
        System.out.println("Se acepto la conexión");

        try{

        }catch (Exception e){
            System.out.println();
        }
    }


}




