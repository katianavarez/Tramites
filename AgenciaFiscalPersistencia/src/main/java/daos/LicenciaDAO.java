/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package daos;

import conexion.Conexion;
import entidades.Licencia;
import static enums.Duracion.DOS;
import static enums.Duracion.TRES;
import static enums.Duracion.UNO;
import excepciones.PersistenciaException;
import idaos.ILicenciaDAO;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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

    @Override
    public Licencia licenciaVigente(Long idPersona) throws PersistenciaException {
        EntityManager em = Conexion.crearConexion();
        try {
            if (em == null || !em.isOpen()) {
                em = Conexion.crearConexion();
            }
            TypedQuery<Licencia> query = em.createQuery(
                "SELECT l FROM Licencia l WHERE l.persona.id = :idPersona ORDER BY l.fechaExpedicion DESC", Licencia.class
            );
            query.setParameter("idPersona", idPersona);
            List<Licencia> licencias = query.getResultList();
            
            for (Licencia licencia : licencias) {
                int años = 0;
                if (licencia.getDuracion()!= null) {
                    switch (licencia.getDuracion()) {
                        case UNO -> años = 1;
                        case DOS -> años = 2;
                        case TRES -> años = 3;
                    }
                }
                LocalDate fechaFin = licencia.getFechaExpedicion().plusYears(años);
                if (fechaFin.isAfter((LocalDate.now()))){
                    return licencia;
                }
            }
            
            return null;
        } catch (Exception e) {
            throw new PersistenciaException("No se pudo obtener la licencia vigente.", e);
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
}
