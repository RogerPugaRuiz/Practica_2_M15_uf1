/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend;

import Encryption.EncryptAndDecrypt;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Classe que importa y exporta Usuarios en json.
 *
 * @author roger
 */
public class Json {

    private static boolean isJsonEncrypt = false;

    /**
     * Metodo para importar un archivo json en objeto Usuarios
     *
     * @param jsonFile
     * @return Usuarios
     */
    public Usuarios jsonImport(String jsonFile) {
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

            Scanner sc = new Scanner(System.in);
            System.out.println("¿Las contraseñas estan encriptada?(Si/No)");
            char s = sc.next().charAt(0);
            String key = "";
            if (s == 'S' || s == 's') {
                System.out.println("Escribe el token: ");
                key = sc.next();
                Json.isJsonEncrypt = true;
            } else {
                Json.isJsonEncrypt = false;
            }

            // mientras el siguente iterador existe
            while (iterator.hasNext()) {
                final JSONObject nextUser = iterator.next();
                // creamos el nuevo usuario
                Usuario usuario = null;

                if (Json.isJsonEncrypt) {

                    EncryptAndDecrypt ead = new EncryptAndDecrypt();
                    String password = ead.decrypt(nextUser.get("password").toString(), key);
                    usuario = new Usuario(
                            nextUser.get("nombre").toString(),
                            nextUser.get("apellidos").toString(),
                            nextUser.get("email").toString(),
                            password,
                            rol(nextUser.get("rol").toString()));
                    // lo añadimos a la classe usuarios
                } else {
                    usuario = new Usuario(
                            nextUser.get("nombre").toString(),
                            nextUser.get("apellidos").toString(),
                            nextUser.get("email").toString(),
                            nextUser.get("password").toString(),
                            rol(nextUser.get("rol").toString()));
                    // lo añadimos a la classe usuarios
                }

                usuarios.add(usuario);

            }

            return usuarios;
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } catch (ParseException ex) {
        }catch (NullPointerException ex){
            System.out.println("Token invalido");
        }catch (Exception ex){
            System.out.println("Error inesperado relacionado con" + jsonFile + ".json");
        }

        return null;

    }

    /**
     * Metodo para exportar los usuarios a un archivo json
     *
     * @param jsonFile
     * @param usuarios
     */
    public void jsonExport(String jsonFile, Usuarios usuarios) {
        JSONObject obj = new JSONObject();
        JSONArray list = new JSONArray();
        Iterator<Usuario> i = usuarios.iterator();
        
        // create a token for encrypt and decrypt file
        EncryptAndDecrypt ead = new EncryptAndDecrypt();
        String token = ead.getKey();
        System.out.println("token-> " + token);
        
        // mientras exista algun usuario
        while (i.hasNext()) {
            try {
                Usuario u = i.next();
                
                String encryptPassword = ead.encrypt(u.getPassword(), token);
                JSONObject objUsuario = new JSONObject();
                objUsuario.put("nombre", u.getNombre());
                objUsuario.put("apellidos", u.getApellidos());
                objUsuario.put("email", u.getEmail());
                objUsuario.put("password", encryptPassword);
                objUsuario.put("rol", u.getRol());
                // añadimos el usuarios al array list de usuarios
                list.add(objUsuario);
            } catch (NullPointerException ex) {
                System.out.println("Token invalido");
            } catch (Exception ex) {
                System.out.println("Error inesperado relacionado con" + jsonFile + ".json");
            }
        }
        // añadimos el array list en el objeto json
        obj.put("usuarios", list);

        //escribimos en el archivo json el objeto json
        try ( FileWriter file = new FileWriter(jsonFile)) {
            file.write(obj.toJSONString());
        } catch (IOException ex) {
        }

    }

    // convierte un estring, en el caso de que la estring sea admin devuelve
    // un 2. Caso contrario un 1.
    private int rol(String s) {
        return s.equalsIgnoreCase("admin") ? 2 : 1;
    }
}
