/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package client;

import Backend.Usuario;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author roger
 */
public class LoginClient implements Runnable{
    private String email;
    private String password;
    private JFrame root;
    private Usuario user;
    public LoginClient(String email,String password){
        this.email = email;
        this.password = password;
    }
    @Override
    public void run() {
        try {
            Socket socket= new Socket(ClientSocket.getIp(),ClientSocket.getPort());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String data = jsonLogin(email, password);
            dos.writeUTF(data);
            String usuario;
            if ((usuario = dis.readUTF()).equals("")){
                JOptionPane.showMessageDialog(root, "Correo o contraseña incorrecta");
            }else{
                user = jsonGetUser(usuario);
                root.setVisible(false);
                UsuarioFrame userFrame = new UsuarioFrame();
                userFrame.setVisible(true);
                
            }
            
            socket.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(root, "Conexión cancelada");
        }
    }
    
    private String jsonLogin(String email,String password){
        JSONObject obj = new JSONObject();
        obj.put("email",email);
        obj.put("password",password);
        return obj.toJSONString();
    }
    
    private Usuario jsonGetUser(String user){
        try {
            JSONObject json = (JSONObject) new JSONParser().parse(user);
            return new Usuario(
                json.get("nombre").toString(),
                json.get("apellidos").toString(),
                json.get("email").toString(),
                json.get("password").toString(),
                Integer.parseInt(json.get("rol").toString()));
        } catch (ParseException ex) {
            Logger.getLogger(LoginClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public JFrame getRoot() {
        return root;
    }

    public void setRoot(JFrame root) {
        this.root = root;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    
}
