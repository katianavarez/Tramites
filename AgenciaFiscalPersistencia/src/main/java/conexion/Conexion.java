/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase encargada de gestionar la conexión con l abase de datos.
 * @author katia
 */
public class Conexion {
    private static EntityManagerFactory emf;
    
    /**
     * Crea y devuelve una nueva instancia EntityManager.
     * Si la fábrica de entidades no está inicializada o ha sido cerrada,
     * se crea una nueva instancia.
     * @return instancia de EntityManager
     */
    public static EntityManager crearConexion(){
        if (emf == null || !emf.isOpen()){
                emf = Persistence.createEntityManagerFactory("ConexionPU");
            }
        EntityManager entityManager = emf.createEntityManager();
        return entityManager;
    }
    
    /**
     * Cierra la fábrica de entidades si está abierta.
     */
    public static void cerrarEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
