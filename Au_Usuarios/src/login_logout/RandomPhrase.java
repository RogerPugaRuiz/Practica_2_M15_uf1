/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login_logout;

import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 *
 * @author roger
 */
public class RandomPhrase {
    private static String phrase;
    /**
     * Method that return a String with a random between character 35 to 126 of encoded utf8 
     * @param lenght
     * @return 
     */
    public static void generateRandomPhrase(int lenght) {

        String generatedString = "";
        for (int i = 0; i < lenght; i++) {
            int code = new Random().nextInt(1 + 126) + 35;
            char c = (char) (code);
            generatedString += c;
        }
        phrase = new String(generatedString.getBytes(), StandardCharsets.UTF_8);
    }
    
    public static String getPhrase(){
        return phrase;
    }
}
