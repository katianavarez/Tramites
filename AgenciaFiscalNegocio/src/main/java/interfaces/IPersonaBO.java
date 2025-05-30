/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import excepciones.NegocioException;

/**
 *
 * @author katia
 */
public interface IPersonaBO {
    public PersonaDTO registrarPersona(PersonaDTO personaDTO) throws NegocioException;
    public PersonaDTO buscarPersonaPorRFC(String rfc) throws NegocioException;
    public PersonaDTO registrarPersonaConLicencia(PersonaDTO personaDTO, LicenciaDTO licenciaDTO) throws NegocioException;
    public PersonaDTO buscarPersonaPorId(Long id) throws NegocioException;
    public void insertarMasivamentePersonasConLicencia(int cantidad) throws NegocioException;
}
