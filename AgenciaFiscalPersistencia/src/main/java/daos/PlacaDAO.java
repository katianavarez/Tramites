/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Placa;
import enums.EstadoPlaca;
import excepciones.PersistenciaException;
import idaos.IPlacaDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Implementación de IPlacaDAO que gestiona las operaciones de epersistencia
 * relacionadas con la entidad Placa.
 * Permite registrar nuevas placas, obtener historial de placas, desactivar placas.
 * 
 * @author katia
 */
public class PlacaDAO implements IPlacaDAO{
    EntityManager em = Conexion.crearConexion();
    
    /**
     * Registra una nueva placa en la base de datos.
     *
     * @param placa La placa a registrar.
     * @return La placa registrada.
     * @throws PersistenciaException si ocurre un error durante el registro.
     */
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
    
     /**
     * Obtiene el historial de placas asociadas a un vehículo identificado por su número de serie.
     * El historial se devuelve en orden descendente.
     *
     * @param numeroSerie El número de serie del vehículo.
     * @return Lista de placas asociadas al vehículo.
     * @throws PersistenciaException si ocurre un error durante la consulta.
     */
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
    
    /**
     * Desactiva las placas activas de un vehículo antes de emitir una nueva.
     * Cambia el estado de las placas con estado activa a inactiva.
     *
     * @param numeroSerie El número de serie del vehículo.
     * @throws PersistenciaException si ocurre un error durante la actualización.
     */
    @Override
    public void desactivarPlacasAnteriores(String numeroSerie) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            
            List<Placa> placas = em.createQuery(
                "SELECT p FROM Placa p WHERE p.vehiculo.numeroSerie = :numSerie AND p.estado = :estadoActiva", Placa.class)
                .setParameter("numSerie", numeroSerie)
                .setParameter("estadoActiva", EstadoPlaca.ACTIVA)
                .getResultList();

            for (Placa placa : placas) {
                placa.setEstado(EstadoPlaca.INACTIVA);
                em.merge(placa);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            throw new PersistenciaException("No se han podido desactivar las placas anteriores.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
