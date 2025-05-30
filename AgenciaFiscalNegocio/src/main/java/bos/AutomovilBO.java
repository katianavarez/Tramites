/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.AutomovilDTO;
import entidades.Automovil;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.IAutomovilDAO;
import interfaces.IAutomovilBO;
import java.time.LocalDate;
import mappers.AutomovilMapper;

/**
 *
 * @author katia
 */
public class AutomovilBO implements IAutomovilBO{
    private final IAutomovilDAO automovilDAO;
    private final AutomovilMapper automovilMapper;

    public AutomovilBO(IAutomovilDAO automovilDAO) {
        this.automovilDAO = automovilDAO;
        this.automovilMapper = new AutomovilMapper();
    }
    
    @Override
    public AutomovilDTO registrarAutomovil(AutomovilDTO dto) throws NegocioException {
        if (dto == null) {
            throw new NegocioException("Los datos del automóvil son obligatorios.");
        }
        if (dto.getNumeroSerie() == null || dto.getNumeroSerie().isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }
        if (dto.getMarca() == null || dto.getMarca().isBlank()) {
            throw new NegocioException("La marca es obligatoria.");
        }
        if (dto.getLinea() == null || dto.getLinea().isBlank()) {
            throw new NegocioException("La línea es obligatoria.");
        }
        if (dto.getColor() == null || dto.getColor().isBlank()) {
            throw new NegocioException("El color es obligatorio.");
        }
        if (dto.getModelo() == null) {
            throw new NegocioException("El modelo es obligatorio.");
        }
        if (dto.getModelo() > LocalDate.now().getYear() + 1) {
            throw new NegocioException("El modelo es inválido.");
        }
        try {
            Automovil existente = automovilDAO.buscarPorNumeroSerie(dto.getNumeroSerie());
            if (existente != null) {
                throw new NegocioException("Ya existe un automóvil con ese número de serie.");
            }
        } catch (PersistenciaException e) {
        }

        try {
            Automovil entidad = automovilMapper.toEntity(dto);
            entidad = automovilDAO.registrarAutomovil(entidad);
            return automovilMapper.toDTO(entidad);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al registrar el automóvil.", e);
        }
    }

    @Override
    public AutomovilDTO buscarPorNumeroSerie(String numeroSerie) throws NegocioException {
        if (numeroSerie == null || numeroSerie.isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }

        try {
            Automovil automovil = automovilDAO.buscarPorNumeroSerie(numeroSerie);
            return automovilMapper.toDTO(automovil);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el automóvil por número de serie.", e);
        }
    }
    
}
