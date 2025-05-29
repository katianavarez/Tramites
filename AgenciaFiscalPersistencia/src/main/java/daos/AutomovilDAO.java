/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Automovil;
import excepciones.PersistenciaException;
import idaos.IAutomovilDAO;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class AutomovilDAO implements IAutomovilDAO{
    EntityManager em = Conexion.crearConexion();
    
    @Override
    public Automovil registrarAutomovil(Automovil automovil) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }

            em.getTransaction().begin();
            em.persist(automovil);
            em.getTransaction().commit();
            return automovil;

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se ha podido registrar el automóvil.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public Automovil buscarPorNumeroSerie(String numeroSerie) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Automovil> query = em.createQuery(
                "SELECT a FROM Automovil a WHERE a.numeroSerie = :numeroSerie", Automovil.class);
            query.setParameter("numeroSerie", numeroSerie);
            
            return query.getSingleResult();
            
        }catch (Exception e) {
            throw new PersistenciaException("No se ha podido encontrar el automóvil por número de serie.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
