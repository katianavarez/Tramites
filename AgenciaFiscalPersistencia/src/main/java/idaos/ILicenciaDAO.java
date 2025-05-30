/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Licencia;
import excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos relacionados con la entidad Licencia
 * @author katia
 */
public interface ILicenciaDAO {
    
    /**
     * Registra una licencia nueva en la base de datos.
     * @param licencia La licencia que se desea registrar.
     * @return La licencia registrada.
     * @throws PersistenciaException Por si ocurre un error al guardar la licencia.
     */
    public Licencia registrarLicencia(Licencia licencia) throws PersistenciaException;
    
    /**
     * Busca la licencia vigente más reciente de una persona.
     * @param idPersona ID de la persona de quien se desea consultar la licencia vigente.
     * @return La licencia vigente si existe. Null si no tiene vigente.
     * @throws PersistenciaException En caso de error al consultar la bd.
     */
    public Licencia licenciaVigente(Long idPersona) throws PersistenciaException;
}
