/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.LicenciaDTO;
import entidades.Licencia;
import entidades.Persona;

/**
 * Mapper encargado de convertir entre objetos Licencia y LicenciaDTO.
 * @author katia
 */
public class LicenciaMapper{
    
    /**
     * Convierte un LicenciaDTO en una entidad Licencia.
     * @param dto El DTO a convertir.
     * @return La entidad, o null si el dto es nulo.
     */
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
    
    /**
     * Convierte una entidad Licencia a LicenciaDTO
     * @param entidad La entidad a convertir.
     * @return El DTO correspondiente, o null si la entidad es nula.
     */
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
