/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.LicenciaDTO;
import excepciones.NegocioException;

/**
 * Interfaz que define las operaciones de la capa de negocio relacionadas con las licencias de conducir.
 * 
 * @author katia
 */
public interface ILicenciaBO {
    
    /**
     * Registra una nueva licencia para una persona.
     *
     * @param licenciaDTO Objeto con los datos necesarios para registrar la licencia.
     * @return El objeto LicenciaDTO registrado.
     * @throws NegocioException si ocurre un error de validación o persistencia.
     */
    public LicenciaDTO registrarLicencia(LicenciaDTO licenciaDTO) throws NegocioException;
    
    /**
     * Obtiene la licencia vigente de una persona.
     *
     * @param idPersona El identificador de la persona.
     * @return La licencia vigente como LicenciaDTO.
     * @throws NegocioException si la persona no tiene una licencia vigente o si ocurre un error de consulta.
     */
    public LicenciaDTO licenciaVigente(Long idPersona) throws NegocioException;
}
