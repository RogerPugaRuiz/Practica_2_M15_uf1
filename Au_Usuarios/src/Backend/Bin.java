/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Encryption.EncryptAndDecrypt;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import Backend.Exception.UserAlreadyExistException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * Classe que gestiona archivos binarios para guardar y leer perfiles de usuarios.
 * @author roger
 */
public class Bin {
    
    /**
     * Contructor por defecto.
     */
    public Bin(){
    }
    
    /**
     * Metodo para añadir un usuario al archivo bin
     * @param u
     * @return boolean true si la escritura fue un exito
     */
    public boolean add(Usuario u,String token) {
        try{
            // crear el objeto de output stream
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("archivo.bin"));
            // escribir solo un usuario
            
            EncryptAndDecrypt ead = new EncryptAndDecrypt();
            
            String password = u.getPassword();
            u.setPassword(ead.encrypt(password, token));
            oss.writeObject(u); 
            u.setPassword(password);
            return true;
        }catch (IOException e){
            return false;
        }catch (NullPointerException ex){
            System.out.println("Token invalido");
            return false;
        }catch (Exception ex){
            System.out.println("Error inesperado relacionado con la encriptacion del archivo.bin");
            return false;
        }
    }
    
    /**
     * Metodo para añadir multiples usuarios al archivo bin.
     * @param usuarios 
     */
    public void addList(Usuarios usuarios,String token) {

        try{
            // crear objeto de salida de datos
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("archivo.bin"));
            // crear un iterador de de usuarios
            Iterator<Usuario> iterator = usuarios.iterator();
            
            EncryptAndDecrypt ead = new EncryptAndDecrypt();
            // mientras exista algun usuario
            while(iterator.hasNext()){
                //escribe el usuario en el documento "archivo.bin"
  
                Usuario u = iterator.next();
                String password = u.getPassword();
                u.setPassword(ead.encrypt(password, token));
                oss.writeObject(u);
                u.setPassword(password);
            }
            // no existe más usuarios y se cierra la salida de datos
            oss.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }catch (NullPointerException ex){
            System.out.println("Token invalido");
        }catch (Exception ex){
            System.out.println("Error inesperado relacionado con archivo.bin");
        }
        
    }
    
    
    /**
     * Metodo para leer del archivo bin y crear los usuarios
     * @param usuarios 
     * @param token 
     */
    public void read(Usuarios usuarios,String token) {
        try {
            
            //nueva salida de datos de objetos
            ObjectInputStream ois;
            //instanciar la salida de datos de objetos con el "archivo.bin"
            ois = new ObjectInputStream(new FileInputStream("archivo.bin"));
            //leer el primer usuario
            Usuario u = (Usuario) ois.readObject();
            //mientras el usuario no sea nulo
            
            EncryptAndDecrypt ead = new EncryptAndDecrypt();
            while (u != null) {
                //System.out.println(u.getAll());
                //añadimos el usuario a la classe usuarios.
                try{
                    
                    

                    String password = u.getPassword();
                    u.setPassword(ead.decrypt(password, token));
                    
                    usuarios.add(u);

                    //siguiente usuario
                    
                } catch (UserAlreadyExistException ex) {
                    System.out.println(ex.getMessage());
                } catch (NullPointerException ex) {
                    System.out.println("Token invalido");
                } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException ex) {
                    System.out.println(ex.getMessage());
                }
                u = (Usuario) ois.readObject();
            }
            //cerramos la salida de datos
            ois.close();
        } catch (FileNotFoundException ex) {
            System.out.println("archivo binario no creado");
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            
        }
    }
}
