/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Persona;
import excepciones.PersistenciaException;
import idaos.IPersonaDAO;
import javax.persistence.EntityManager;

/**
 * 
 * Implementación de IPersonaDAO que gestiona las operaciones de persistencia de la
 * entidad Persona, usando JPA.
 * permite registrar personas, Y buscar por RFC o por ID.
 * 
 * @author katia
 */
public class PersonaDAO implements IPersonaDAO{

    EntityManager em = Conexion.crearConexion();
    
    /**
     * Registra una nueva persona en la base de datos.
     *
     * @param persona La persona a registrar.
     * @return El objeto Persona registrado, incluyendo su ID generado.
     * @throws PersistenciaException En caso de error durante la persistencia.
     */
    @Override
    public Persona registrarPersona(Persona persona) throws PersistenciaException{
        try {
            if (em == null || !em.isOpen()){
                em = Conexion.crearConexion();
            }
            em.getTransaction().begin();
            em.persist(persona);
            em.getTransaction().commit();
            
            return persona;
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            throw new PersistenciaException("No se ha podido registrar a la persona.", e);
        } finally{
            if (em != null && em.isOpen()){
                em.close();
            }
        }
    }

    /**
     * Busca una persona por su RFC.
     *
     * @param rfc El RFC de la persona.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encuentra la persona o si ocurre un error de consulta.
     */
    @Override
    public Persona buscarPersonaPorRFC(String rfc) throws PersistenciaException{
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            return em.createQuery("SELECT p FROM Persona p WHERE p.rfc = :rfc", Persona.class)
                     .setParameter("rfc", rfc)
                     .getSingleResult();
        } catch (Exception e) {
            throw new PersistenciaException("No se ha podido obtener una persona por su RFC.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    /**
     * Busca una persona por su identificador único.
     *
     * @param id El identificador de la persona.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encuentra la persona o si ocurre un error de consulta.
     */
    @Override
    public Persona buscarPersonaPorId(Long id) throws PersistenciaException {
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            return em.find(Persona.class, id);
        } catch (Exception e) {
            throw new PersistenciaException("No se ha podido obtener a la persona por su ID.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    
}
