/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.AutomovilDTO;
import dtos.VehiculoDTO;
import entidades.Automovil;
import entidades.Vehiculo;

/**
 * Mapper que convierte entre objetos Automovil y AutomovilDTO
 * @author katia
 */
public class AutomovilMapper {
    private final VehiculoMapper vehiculoMapper = new VehiculoMapper();

    /**
     * Convierte un objeto AutomovilDTO  auna entidad Automovil.
     * @param dto DTO de automóvil a convertir.
     * @return La entidad Automovil o null si el DTO es nulo.
     */
    public Automovil toEntity(AutomovilDTO dto) {
        if (dto == null){
            return null;
        }
        Automovil automovil = new Automovil();
        Vehiculo vehiculo = vehiculoMapper.toEntity(dto);
        
        automovil.setId(vehiculo.getId());
        automovil.setNumeroSerie(vehiculo.getNumeroSerie());
        automovil.setMarca(vehiculo.getMarca());
        automovil.setLinea(vehiculo.getLinea());
        automovil.setColor(vehiculo.getColor());
        automovil.setModelo(vehiculo.getModelo());
        return automovil;
    }

    /**
     * Convierte una entidad Automovil a AutomovilDTO
     * @param automovil La entidad de automóvil a convertir.
     * @return El DTO correspondiente o null si la entidad es nula.
     */
    public AutomovilDTO toDTO(Automovil automovil) {
        if (automovil == null){
            return null;
        }
        VehiculoDTO vehiculo = vehiculoMapper.toDTO(automovil);
        
        AutomovilDTO dto = new AutomovilDTO();
        dto.setId(vehiculo.getId());
        dto.setNumeroSerie(vehiculo.getNumeroSerie());
        dto.setMarca(vehiculo.getMarca());
        dto.setLinea(vehiculo.getLinea());
        dto.setColor(vehiculo.getColor());
        dto.setModelo(vehiculo.getModelo());
        return dto;
    }
}
