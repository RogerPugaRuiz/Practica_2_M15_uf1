/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package login_logout;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author roger
 */
public class CryptAndDecrypt {
    private String phrase;
    
    
    public void setPhrase(String phrase){
        this.phrase = phrase;
    }
    public String getPhrase(){
        return this.phrase;
    }
    public byte[] crypt(String sinCifrar) throws Exception {
        final byte[] bytes = sinCifrar.getBytes("UTF-8");
        final Cipher aes = obtieneCipher(true,phrase);
        final byte[] cifrado = aes.doFinal(bytes);
        return cifrado;
    }

    public String decrypt(byte[] cifrado) throws Exception {
        final Cipher aes = obtieneCipher(false,phrase);
        final byte[] bytes = aes.doFinal(cifrado);
        final String sinCifrar = new String(bytes, "UTF-8");
        return sinCifrar;
    }

    private Cipher obtieneCipher(boolean paraCifrar,String phrase) throws Exception {
        final String frase = phrase;
        final MessageDigest digest = MessageDigest.getInstance("SHA");
        digest.update(frase.getBytes("UTF-8"));
        final SecretKeySpec key = new SecretKeySpec(digest.digest(), 0, 16, "AES");

        final Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
        if (paraCifrar) {
            aes.init(Cipher.ENCRYPT_MODE, key);
        } else {
            aes.init(Cipher.DECRYPT_MODE, key);
        }

        return aes;
    }
    
    public static String generateRandomKey(int lenght){
        
        String generatedString = "";
        for (int i=0; i<lenght; i++){
            int code = new Random().nextInt(1+126)+35;
            char c = (char)(code);
            generatedString += c;
        }
        return new String (generatedString.getBytes(),StandardCharsets.UTF_8);
    }

}
