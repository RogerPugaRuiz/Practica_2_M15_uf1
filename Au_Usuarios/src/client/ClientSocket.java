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
public class ClientSocket{
    private static String ip = "192.0.0.1";
    private static int port = 8888;

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        ClientSocket.ip = ip;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        ClientSocket.port = port;
    }
 
}
