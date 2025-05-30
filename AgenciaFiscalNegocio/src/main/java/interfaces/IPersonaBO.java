/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import excepciones.NegocioException;

/**
 * Interfaz que define las operaciones de negocio relacionadas con personas.
 * 
 * @author katia
 */
public interface IPersonaBO {
    
    /**
     * Registra una nueva persona en el sistema.
     *
     * @param personaDTO Objeto DTO con los datos de la persona.
     * @return La persona registrada.
     * @throws NegocioException si los datos no son válidos o hay un error al guardar.
     */
    public PersonaDTO registrarPersona(PersonaDTO personaDTO) throws NegocioException;
    
    /**
     * Busca una persona por su RFC.
     *
     * @param rfc RFC de la persona.
     * @return Persona correspondiente si se encuentra.
     * @throws NegocioException si el RFC no es válido o no se encuentra a la persona.
     */
    public PersonaDTO buscarPersonaPorRFC(String rfc) throws NegocioException;
    
    /**
     * Registra una persona junto con su licencia.
     *
     * @param personaDTO Datos de la persona.
     * @param licenciaDTO Datos de la licencia.
     * @return Persona registrada.
     * @throws NegocioException si los datos son inválidos o falla el registro.
     */
    public PersonaDTO registrarPersonaConLicencia(PersonaDTO personaDTO, LicenciaDTO licenciaDTO) throws NegocioException;
    
    /**
     * Busca una persona por su ID.
     *
     * @param id Identificador único de la persona.
     * @return La persona encontrada.
     * @throws NegocioException si no se encuentra la persona.
     */
    public PersonaDTO buscarPersonaPorId(Long id) throws NegocioException;
    
    /**
     * Inserta personas con licencias a la vez generadas automáticamente.
     *
     * @param cantidad Número de personas a insertar.
     * @throws NegocioException si la cantidad es inválida o ocurre un error.
     */
    public void insertarMasivamentePersonasConLicencia(int cantidad) throws NegocioException;
}
