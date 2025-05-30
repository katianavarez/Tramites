/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package encriptador;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase utilizada para encriptar y desencriptar cadenas de texto usando
 * el algoritmo AES con clave fija y condificación Base64.
 * 
 * @author katia
 */
public class Encriptador {
    
    private static final String ALGORITMO = "AES";
    private static final String CLAVE = "clave-fija-para-";

    /**
     * Encripta una cadena de texto usando AES y la retorna codificada en Base64.
     * 
     * @param input Texto a encriptar.
     * @return Texto encriptado.
     */
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

    /**
     * Desencripta una cadena codificada en Base64 que fue previamente encriptada.
     * 
     * @param input Texto encriptado en Base64.
     * @return Texto desencriptado en su forma original.
     */
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
