/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Placa;
import excepciones.PersistenciaException;
import idaos.IPlacaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class PlacaDAO implements IPlacaDAO{
    EntityManager em = Conexion.crearConexion();
    
    @Override
    public Placa registrarPlaca(Placa placa) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(placa);
            em.getTransaction().commit();
            return placa;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se ha podido registrar la placa.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    @Override
    public List<Placa> obtenerHistorialPorNumSerie(String numeroSerie) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Placa> query = em.createQuery(
                "SELECT p FROM Placa p WHERE p.vehiculo.numeroSerie = :numeroSerie ORDER BY p.fechaEmision DESC",
                Placa.class
            );
            query.setParameter("numeroSerie", numeroSerie);
            return query.getResultList();
        } catch (Exception e) {
            throw new PersistenciaException("No se ha podido obtener el historial de placas por número de serie.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
}
