/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;

import Backend.Usuario;
import Backend.Usuarios;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author roger
 */
public class ThreadLogin implements Runnable{
    private int port;
    private String email;
    private String password;
    private Usuario usuario;
    private Usuarios usuarios;
    
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Esperando petición del cliente" + socket.getInetAddress());
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String mensage = dis.readUTF();
                JSONObject json = (JSONObject) new JSONParser().parse(mensage);
                email = json.get("e-mail").toString();
                password = json.get("password").toString();
                
                usuario = usuarios.login(email, password);
//                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//                if (usuario != null){
//                    dos.writeBoolean(true);
//                }else{
//                    dos.writeBoolean(false);
//                }
                if (usuario != null){
                    System.out.println(usuario.getAll());
                }else{
                    System.out.println("usuarios vacio");
                }
                
                socket.close();
            }
            
        }catch(IOException ex){
            Logger.getLogger(ThreadLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ThreadLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }
    
    
    
    
}
