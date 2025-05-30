/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.PersonaDTO;
import entidades.Persona;

/**
 * Mapper encargado de convertir entre objetos Persona y PersonaDTO
 * 
 * @author katia
 */
public class PersonaMapper{
    
    /**
     * Convierte un objeto PersonaDTO en una entidad Persona
     *
     * @param dto El DTO que contiene los datos de la persona.
     * @return La entidad Persona correspondiente, o null si el DTO es nulo.
     */
    public Persona toEntity(PersonaDTO dto) {
        Persona persona = new Persona();
        persona.setId(dto.getId());
        persona.setRfc(dto.getRfc());
        persona.setNombre(dto.getNombre());
        persona.setApellidoPaterno(dto.getApellidoPaterno());
        persona.setApellidoMaterno(dto.getApellidoMaterno());
        persona.setTelefono(dto.getTelefono());
        persona.setFechaNacimiento(dto.getFechaNacimiento());
        return persona;
    }

    /**
     * Convierte una entidad Persona a PersonaDTO.
     * 
     * @param entidad entidad Persona que se desea convertir.
     * @return DTO o null si la entidad es nula.
     */
    public PersonaDTO toDTO(Persona entidad) {
        PersonaDTO dto = new PersonaDTO();
        dto.setId(entidad.getId());
        dto.setRfc(entidad.getRfc());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setTelefono(entidad.getTelefono());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        return dto;
    }
}
