/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.VehiculoDTO;
import entidades.Vehiculo;

/**
 * Mapper que se encarga de convertir entre objetos Vehiculo y VehiculoDTO.
 * 
 * @author katia
 */
public class VehiculoMapper {
    
    /**
     * Convierte un objeto DTO en una entidad Vehiculo.
     * 
     * @param dto DTO que contiene los datos del vehiculo.
     * @return La entidad Vehiculo.
     */
    public Vehiculo toEntity(VehiculoDTO dto) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(dto.getId());
        vehiculo.setNumeroSerie(dto.getNumeroSerie());
        vehiculo.setMarca(dto.getMarca());
        vehiculo.setLinea(dto.getLinea());
        vehiculo.setColor(dto.getColor());
        vehiculo.setModelo(dto.getModelo());
        return vehiculo;
    }

    /**
     * Convierte una entidad Vehiculo a VehiculoDTO.
     * 
     * @param entidad la entidad a convertir.
     * @return El DTO correspondiente.
     */
    public VehiculoDTO toDTO(Vehiculo entidad) {
        VehiculoDTO dto = new VehiculoDTO();
        dto.setId(entidad.getId());
        dto.setNumeroSerie(entidad.getNumeroSerie());
        dto.setMarca(entidad.getMarca());
        dto.setLinea(entidad.getLinea());
        dto.setColor(entidad.getColor());
        dto.setModelo(entidad.getModelo());
        return dto;
    }
}
