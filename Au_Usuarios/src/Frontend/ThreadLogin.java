/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Frontend;

import Users.Usuario;
import Users.Usuarios;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
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
    private JList jList1;
    private String[] inetAddress = new String[999];
    private int position = 0;
    
    @Override
    public void run() {
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Esperando petición del cliente" + socket.getInetAddress());
                inetAddress[position] = socket.getInetAddress().toString();
                position++;
                jList1 = new JList(inetAddress);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                String mensage = dis.readUTF();
                JSONObject json = (JSONObject) new JSONParser().parse(mensage);
                email = json.get("email").toString();
                password = json.get("password").toString();
                
                usuario = usuarios.login(email, password);
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                if (usuario != null){
                    dos.writeUTF(usuario.toJsonString());
                }else{
                    dos.writeUTF("");
                }
                if (usuario != null){
                    System.out.println(usuario.toJsonString());
                }else{
                    System.out.println("Intento de inicio de session incorrecto");
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

    public JList getjList1() {
        return jList1;
    }

    public void setjList1(JList jList1) {
        this.jList1 = jList1;
    }
    
    
    
    
}
