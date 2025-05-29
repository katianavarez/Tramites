/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Vehiculo;
import excepciones.PersistenciaException;

/**
 *
 * @author katia
 */
public interface IVehiculoDAO {
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) throws PersistenciaException;
    public Vehiculo buscarVehiculoPorNumSerie(String numeroSerie) throws PersistenciaException;
}
