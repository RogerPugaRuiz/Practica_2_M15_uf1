/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_logout;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;


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
    public boolean add(Usuario u){
        try{
            // crear el objeto de output stream
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("archivo.bin"));
            // escribir solo un usuario
            oss.writeObject(u); 
            return true;
        }catch (IOException e){
            return false;
        }
    }
    
    /**
     * Metodo para añadir multiples usuarios al archivo bin.
     * @param usuarios 
     */
    public void addList(Usuarios usuarios){

        try{
            // crear objeto de salida de datos
            ObjectOutputStream oss = new ObjectOutputStream(new FileOutputStream("archivo.bin"));
            // crear un iterador de de usuarios
            Iterator<Usuario> iterator = usuarios.iterator();
            
            // mientras exista algun usuario
            while(iterator.hasNext()){
                //escribe el usuario en el documento "archivo.bin"
                oss.writeObject(iterator.next());
            }
            // no existe más usuarios y se cierra la salida de datos
            oss.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    
    
    /**
     * Metodo para leer del archivo bin y crear los usuarios
     * @param usuarios 
     */
    public void read(Usuarios usuarios){
        try {
            
            //nueva salida de datos de objetos
            ObjectInputStream ois;
            //instanciar la salida de datos de objetos con el "archivo.bin"
            ois = new ObjectInputStream(new FileInputStream("archivo.bin"));
            //leer el primer usuario
            Usuario u = (Usuario) ois.readObject();
            //mientras el usuario no sea nulo
            while (u != null) {
                //System.out.println(u.getAll());
                //añadimos el usuario a la classe usuarios.
                usuarios.add(u);
                //siguiente usuario
                u = (Usuario) ois.readObject();
                
            }
            //cerramos la salida de datos
            ois.close();
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            
        } catch (ClassNotFoundException ex) {
            
        }
    }
}
