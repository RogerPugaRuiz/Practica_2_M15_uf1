/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;

import PersistentData.Bin;
import Users.Usuario;
import PrivateToken.PrivateToken;
import Users.Usuarios;

/**
 *
 * @author roger
 */
public class Server{
    
    private Usuarios usuarios;
    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }

    public void run() {
        loadData();
        login();

    }
    public void loadData(){
        Bin bin = new Bin();
        usuarios = new Usuarios();
        PrivateToken privateToken = new PrivateToken();
        
        bin.read(usuarios, privateToken.getToken());
    }
    
    public void login(){
        ThreadLogin threadLogin = new ThreadLogin();
        threadLogin.setPort(8877);
        threadLogin.setUsuarios(usuarios);
        Thread miThread = new Thread(threadLogin);
        miThread.start();
    }
}
