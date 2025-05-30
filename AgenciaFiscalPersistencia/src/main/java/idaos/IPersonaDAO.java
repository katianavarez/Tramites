/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idaos;

import entidades.Persona;
import excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Persona
 * @author katia
 */
public interface IPersonaDAO {
    
    /**
     * Registra una nueva persona en la base de datos.
     *
     * @param persona La persona a registrar.
     * @return El objeto Persona registrado, incluyendo su ID generado.
     * @throws PersistenciaException En caso de error durante la persistencia.
     */
    public Persona registrarPersona(Persona persona) throws PersistenciaException;
    
    /**
     * Busca una persona por su RFC.
     *
     * @param rfc El RFC de la persona.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encuentra la persona o si ocurre un error de consulta.
     */
    public Persona buscarPersonaPorRFC(String rfc) throws PersistenciaException;
    
    /**
     * Busca una persona por su identificador único.
     *
     * @param id El identificador de la persona.
     * @return La persona encontrada.
     * @throws PersistenciaException si no se encuentra la persona o si ocurre un error de consulta.
     */
    public Persona buscarPersonaPorId(Long id) throws PersistenciaException;
}
