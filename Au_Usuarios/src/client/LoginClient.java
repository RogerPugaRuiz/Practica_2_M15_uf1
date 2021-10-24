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
import org.json.simple.JSONObject;

/**
 *
 * @author roger
 */
public class LoginClient implements Runnable{
    private String email;
    private String password;
    public LoginClient(String email,String password){
        this.email = email;
        this.password = password;
    }
    @Override
    public void run() {
        try {
            Socket socket= new Socket(ClientSocket.getIp(),ClientSocket.getPort());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String data = jsonObject(email, password);
            dos.writeUTF(data);
            socket.close();
            System.out.println(data);
        } catch (IOException ex) {
            Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String jsonObject(String email,String password){
        JSONObject obj = new JSONObject();
        obj.put("e-mail",email);
        obj.put("password",password);
        return obj.toJSONString();
    }
}
