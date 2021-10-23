/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;


import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roger
 */
public abstract class Client {
    private static SocketClient socketClient = new SocketClient("192.168.1.112",8877);
    abstract void run();
    public static void setIp(String ip){
        Client.socketClient.setIp(ip);
    }
    public static void setPort(int port){
        Client.socketClient.setPort(port);
    }
    public static String getIp(){
        return Client.socketClient.getIp();
    }
    public static int getPort(){
        return Client.socketClient.getPort();
    }
    public static Socket getSocket(){
        return Client.socketClient.getSocket();
    } 
}
