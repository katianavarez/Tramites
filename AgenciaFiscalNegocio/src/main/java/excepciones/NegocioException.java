/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepciones;

/**
 * Excepción para los errores en la lógica de negocio del sistema.
 * 
 * @author katia
 */
public class NegocioException extends Exception{

    /**
     * Crea una nueva instancia de NegocioException con un mensaje detallado.
     * 
     * @param message mensaje que describe el error.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Crea una nueva instancia de NegocioException con un mensaje detallado y causa.
     * 
     * @param message mensaje que describe el error.
     * @param cause causa de la excepción.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
