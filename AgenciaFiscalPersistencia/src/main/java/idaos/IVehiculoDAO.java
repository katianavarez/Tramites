/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Vehiculo;
import excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos para la entidad Vehiculo.
 * 
 * @author katia
 */
public interface IVehiculoDAO {
    
    /**
     * Registra un nuevo vehículo en la base de datos.
     * 
     * @param vehiculo El vehículo a registrar.
     * @return El objeto Vehiculo registrado.
     * @throws PersistenciaException Por i ocurre un error al guardar el vehículo.
     */
    public Vehiculo registrarVehiculo(Vehiculo vehiculo) throws PersistenciaException;
    
    /**
     * Busca un vehículo por su número de serie.
     *
     * @param numeroSerie El número de serie del vehículo a buscar.
     * @return El vehículo encontrado.
     * @throws PersistenciaException si no se encuentra o si ocurre un error en la consulta.
     */
    public Vehiculo buscarVehiculoPorNumSerie(String numeroSerie) throws PersistenciaException;
}
