/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author roger
 */
public class ThreadLogin implements Runnable{
    private int port;
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Esperando petición del cliente" + socket.getInetAddress());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String mensage = dis.readUTF();
                System.out.println(mensage);
                socket.close();
            }

            
            
        }catch(IOException ex){
            
        }
    }
    
    public void setPort(int port){
        this.port = port;
    }
    
}
