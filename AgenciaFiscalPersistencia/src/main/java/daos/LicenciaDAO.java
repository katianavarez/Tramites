/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Licencia;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import javax.persistence.EntityManager;

/**
 *
 * @author katia
 */
public class LicenciaDAO implements ILicenciaDAO{
    EntityManager em = Conexion.crearConexion();
    
    @Override
    public Licencia registrarLicencia(Licencia licencia) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(licencia);
            em.getTransaction().commit();
            
            return licencia;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se ha podido registrar la licencia.", e);
        } finally{
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
