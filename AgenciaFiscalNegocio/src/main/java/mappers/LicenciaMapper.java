/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.LicenciaDTO;
import dtos.PersonaDTO;
import entidades.Licencia;
import entidades.Persona;

/**
 *
 * @author katia
 */
public class LicenciaMapper{
    
    public Licencia toEntity(LicenciaDTO dto) {
        if (dto == null){ 
            return null;
        }
        Licencia entidad = new Licencia();
        entidad.setId(dto.getId());
        entidad.setCosto(dto.getCosto());
        entidad.setDuracion(dto.getDuracion());
        entidad.setFechaExpedicion(dto.getFechaExpedicion());
        Persona persona = new Persona();
        persona.setId(dto.getIdPersona());
        entidad.setPersona(persona);
        return entidad;
    }
    
    public LicenciaDTO toDTO(Licencia entidad) {
        if (entidad == null){
            return null;
        }
        LicenciaDTO dto = new LicenciaDTO();
        dto.setId(entidad.getId());
        dto.setCosto(entidad.getCosto());
        dto.setIdPersona(entidad.getPersona().getId());
        dto.setDuracion(entidad.getDuracion());
        dto.setFechaExpedicion(entidad.getFechaExpedicion());
        return dto;
    }
    
}
