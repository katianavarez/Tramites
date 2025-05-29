/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author katia
 */
public class Conexion {
    private static EntityManagerFactory emf;
    
    public static EntityManager crearConexion(){
        if (emf == null || !emf.isOpen()){
                emf = Persistence.createEntityManagerFactory("ConexionPU");
            }
        EntityManager entityManager = emf.createEntityManager();
        return entityManager;
    }
    
    public static void cerrarEntityManagerFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
