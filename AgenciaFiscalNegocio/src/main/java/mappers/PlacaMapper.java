/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mappers;

import dtos.PlacaDTO;
import entidades.Placa;
import entidades.Vehiculo;

/**
 *
 * @author katia
 */
public class PlacaMapper {
    
    public Placa toEntity(PlacaDTO dto) {
        Placa placa = new Placa();
        placa.setId(dto.getId());
        placa.setNumeroPlaca(dto.getNumeroPlaca());
        placa.setFechaEmision(dto.getFechaEmision());
        placa.setFechaRecepcion(dto.getFechaRecepcion());
        placa.setEstado(dto.getEstado());
        placa.setTipo(dto.getTipo());
        placa.setCosto(dto.getCosto());
        return placa;
    }

    public PlacaDTO toDTO(Placa placa) {
        PlacaDTO dto = new PlacaDTO();
        dto.setId(placa.getId());
        dto.setNumeroPlaca(placa.getNumeroPlaca());
        dto.setFechaEmision(placa.getFechaEmision());
        dto.setFechaRecepcion(placa.getFechaRecepcion());
        dto.setEstado(placa.getEstado());
        dto.setTipo(placa.getTipo());
        dto.setCosto(placa.getCosto());
        if (placa.getVehiculo() != null) {
            dto.setNumeroSerie(placa.getVehiculo().getNumeroSerie());
            dto.setIdVehiculo(placa.getVehiculo().getId());
        }
        return dto;
    }
}
