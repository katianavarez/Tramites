/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.VehiculoDTO;
import excepciones.NegocioException;

/**
 *
 * @author katia
 */
public interface IVehiculoBO {
    public VehiculoDTO registrarVehiculo(VehiculoDTO dto) throws NegocioException;
    public VehiculoDTO buscarVehiculoPorNumSerie(String numeroSerie) throws NegocioException;
}
