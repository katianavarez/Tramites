/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Vehiculo;
import excepciones.PersistenciaException;
import idaos.IVehiculoDAO;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author katia
 */
public class VehiculoDAO implements IVehiculoDAO{
    EntityManager em = Conexion.crearConexion();
    
    @Override
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(vehiculo);
            em.getTransaction().commit();
            return vehiculo;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se ha podido registrar el vehículo.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    @Override
    public Vehiculo buscarVehiculoPorNumSerie(String numeroSerie) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Vehiculo> query = em.createQuery(
                "SELECT v FROM Vehiculo v WHERE v.numeroSerie = :numeroSerie", Vehiculo.class);
            query.setParameter("numeroSerie", numeroSerie);
            return query.getSingleResult();
        }catch (Exception e) {
            throw new PersistenciaException("No se ha podido encontrar el vehículo por número de serie.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
