/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bos;

import dtos.VehiculoDTO;
import entidades.Vehiculo;
import excepciones.NegocioException;
import excepciones.PersistenciaException;
import idaos.IVehiculoDAO;
import interfaces.IVehiculoBO;
import java.time.LocalDate;
import mappers.VehiculoMapper;

/**
 * Implementación de la lógica de negocoi para las operaciones relacionadas
 * con vehículos.
 * Realiza validaciones.
 * 
 * @author katia
 */
public class VehiculoBO implements IVehiculoBO{
    private final IVehiculoDAO vehiculoDAO;
    private final VehiculoMapper vehiculoMapper;

    public VehiculoBO(IVehiculoDAO vehiculoDAO) {
        this.vehiculoDAO = vehiculoDAO;
        this.vehiculoMapper = new VehiculoMapper();
    }

    /**
     * Registra un nuevo vehículo.
     * Valida campos obligatorios.
     * Verifica que el número de serie no se repita.
     *
     * @param dto DTO con los datos del vehículo a registrar.
     * @return VehiculoDTO con los datos del vehículo registrado.
     * @throws NegocioException Si los datos son inválidos o si ya existe un vehículo con el mismo número de serie.
     */
    @Override
    public VehiculoDTO registrarVehiculo(VehiculoDTO dto) throws NegocioException{
        if (dto == null) {
            throw new NegocioException("Los datos del vehiculo son obligarios.");
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
        if (dto.getModelo() == null){
            throw new NegocioException("El modelo es obligatorio.");
        }
        if (dto.getModelo() > LocalDate.now().getYear() + 1) {
            throw new NegocioException("El modelo es inválido.");
        }
        try{
            Vehiculo existente = null;
            try {
                existente = vehiculoDAO.buscarVehiculoPorNumSerie(dto.getNumeroSerie());
            } catch (PersistenciaException e) {
            }
            if (existente != null) {
                throw new NegocioException("Ya se ha registrado un vehículo con el mismo número de serie.");
            }
            Vehiculo entidad = vehiculoMapper.toEntity(dto);
            entidad = vehiculoDAO.registrarVehiculo(entidad);
            return vehiculoMapper.toDTO(entidad);
            
        } catch (PersistenciaException e) {
            throw new NegocioException("No se ha podido registrar el vehículo.", e);
        }
    }
    
    /**
     * Busca un vehículo por su número de serie.
     *
     * @param numeroSerie Número de serie del vehículo a buscar.
     * @return VehiculoDTO con los datos del vehículo encontrado.
     * @throws NegocioException Si el número de serie es inválido o si ocurre un error durante la búsqueda.
     */
    @Override
    public VehiculoDTO buscarVehiculoPorNumSerie(String numeroSerie) throws NegocioException {
        if (numeroSerie == null || numeroSerie.isBlank()) {
            throw new NegocioException("El número de serie es obligatorio.");
        }
        try {
            Vehiculo vehiculo = vehiculoDAO.buscarVehiculoPorNumSerie(numeroSerie);
            return vehiculoMapper.toDTO(vehiculo);
        } catch (PersistenciaException e) {
            throw new NegocioException("Error al buscar el vehículo por número de serie.", e);
        }
    }

}
