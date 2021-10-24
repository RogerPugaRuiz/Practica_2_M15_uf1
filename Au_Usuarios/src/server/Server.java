/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author roger
 */
public class Server {
    public static void main(String[] args) {
        ThreadLogin threadLogin = new ThreadLogin();
        threadLogin.setPort(8877);
        Thread miThread = new Thread(threadLogin);
        miThread.start();
    }
}
