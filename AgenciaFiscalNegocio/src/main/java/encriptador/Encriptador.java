/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptador;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author katia
 */
public class Encriptador {
    
    private static final String ALGORITMO = "AES";
    private static final String CLAVE = "clave-fija-para-";

    public static String encriptar(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE.getBytes(), ALGORITMO); 
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); 
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] cifrado = cipher.doFinal(input.getBytes());
            return Base64.getEncoder().encodeToString(cifrado);
        } catch (Exception e) {
            throw new RuntimeException("Error en la encriptación", e);
        }
    }

    public static String desencriptar(String input) {
        try {
            SecretKeySpec key = new SecretKeySpec(CLAVE.getBytes(), ALGORITMO);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decodificado = Base64.getDecoder().decode(input); 
            return new String(cipher.doFinal(decodificado)); 
        } catch (Exception e) {
            throw new RuntimeException("Error en la desencriptación", e);
        }
    }
}
