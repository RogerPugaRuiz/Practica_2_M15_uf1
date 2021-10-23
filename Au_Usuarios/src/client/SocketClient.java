/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author roger
 */
public class SocketClient {
    private Socket socket;
    private String ip;
    private int port;
    
    public SocketClient(){}
    public SocketClient(String ip,int port){
        try {
            this.ip = ip;
            this.port = port;
            socket = new Socket(ip,port);
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    public Socket getSocket(){
        return this.socket;
    }
}
