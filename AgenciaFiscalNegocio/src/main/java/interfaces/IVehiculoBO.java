/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.VehiculoDTO;
import excepciones.NegocioException;

/**
 * Interfaz que define las operaciones de negocio relacionadas con vehículos.
 * @author katia
 */
public interface IVehiculoBO {
    
    /**
     * Registra un nuevo vehículo en el sistema.
     *
     * @param dto Objeto DTO con los datos del vehículo a registrar.
     * @return VehiculoDTO con los datos del vehículo registrado.
     * @throws NegocioException Si los datos son inválidos o si ocurre un error al registrar.
     */
    public VehiculoDTO registrarVehiculo(VehiculoDTO dto) throws NegocioException;
    
    /**
     * Busca un vehículo existente por su número de serie.
     *
     * @param numeroSerie El número de serie del vehículo.
     * @return VehiculoDTO con los datos del vehículo encontrado.
     * @throws NegocioException Si no se encuentra el vehículo o ocurre un error durante la búsqueda.
     */
    public VehiculoDTO buscarVehiculoPorNumSerie(String numeroSerie) throws NegocioException;
}
