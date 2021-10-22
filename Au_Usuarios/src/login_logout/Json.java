/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login_logout;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import login_logout.Exception.UserAlreadyExistException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 * Classe que importa y exporta Usuarios en json.
 * @author roger
 */
public class Json extends CryptAndDecrypt{
    private final String PHRASE;
    private static boolean isCrypted;
    
    public Json(String PHRASE){
        this.PHRASE = PHRASE;
    }
    /**
     * Metodo para importar un archivo json en objeto Usuarios
     * @param jsonFile
     * @return Usuarios
     */
    public Usuarios jsonImport(String jsonFile){
        // crear el convertidos de json 
        JSONParser parser = new JSONParser();
        
        // intenta leer el archivo jsonFile
        try ( Reader reader = new FileReader(jsonFile)) {
            // creamos el objetos con el json
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            // array de usuarios
            JSONArray listaUsuarios = (JSONArray) jsonObject.get("usuarios");
            // System.out.println(listaUsuarios.toString());
            // Iteramos el array
            Iterator<JSONObject> iterator = listaUsuarios.iterator();
            Usuarios usuarios = new Usuarios();
            
            // mientras el siguente iterador existe
            while(iterator.hasNext()){
                final JSONObject nextUser = iterator.next();
                // creamos el nuevo usuario
                Usuario usuario;
                if (isCrypted){
//                    String password = this.decrypt(nextUser.get("password").toString(), PHRASE);
                    usuario = new Usuario(
                        nextUser.get("nombre").toString(),
                        nextUser.get("apellidos").toString(),
                        nextUser.get("email").toString(),
                        nextUser.get("password").toString(),
                        rol(nextUser.get("rol").toString()));
                    // lo añadimos a la classe usuarios
                }else{
                    usuario = new Usuario(
                        nextUser.get("nombre").toString(),
                        nextUser.get("apellidos").toString(),
                        nextUser.get("email").toString(),
                        nextUser.get("password").toString(),
                        rol(nextUser.get("rol").toString()));
                    // lo añadimos a la classe usuarios
                }
                
                try{
                    usuarios.add(usuario);
                }catch(UserAlreadyExistException ex){
                    System.out.println(ex.getMessage());
                }
                            
            }
            
            return usuarios;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }
        
        return null;
            
    }
    
    /**
     * Metodo para exportar los usuarios a un archivo json
     * @param jsonFile
     * @param usuarios 
     */
    public void jsonExport(String jsonFile, Usuarios usuarios){
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        Iterator <Usuario> i = usuarios.iterator();
        
        // mientras exista algun usuario
        while(i.hasNext()){
            Usuario u = i.next();
            JSONObject objUsuario = new JSONObject();
            objUsuario.put("nombre", u.getNombre());
            objUsuario.put("apellidos", u.getApellidos());
            objUsuario.put("email", u.getEmail());
            objUsuario.put("password",u.getPassword());
            objUsuario.put("rol", u.getRol());
            // añadimos el usuarios al array list de usuarios
            list.add(objUsuario);
        }
        // añadimos el array list en el objeto json
        obj.put("usuarios", list);
        
        //escribimos en el archivo json el objeto json
        try (FileWriter file = new FileWriter(jsonFile)){
            file.write(obj.toJSONString());
        } catch (IOException ex) {
        }
        
    }
    
    // convierte un estring, en el caso de que la estring sea admin devuelve
    // un 2. Caso contrario un 1.
    private int rol(String s){
        return s.equalsIgnoreCase("admin") ? 2 : 1;
    }

    @Override
    public String startCrypting(String paraCifrar, String phrase) {
        return "";
    }

    @Override
    public String startDecrypting(String paraCifrar, String phrase) {
        return "";
    }
}
