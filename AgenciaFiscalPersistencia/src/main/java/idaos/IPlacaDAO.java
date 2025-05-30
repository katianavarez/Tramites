/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Placa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con la entidad Placa
 * @author katia
 */
public interface IPlacaDAO {
    
    /**
     * Registra una nueva placa en la base de datos.
     *
     * @param placa La placa a registrar.
     * @return La placa registrada.
     * @throws PersistenciaException si ocurre un error durante el registro.
     */
    public Placa registrarPlaca(Placa placa) throws PersistenciaException;
    
    /**
     * Obtiene el historial de placas asociadas a un vehículo identificado por su número de serie.
     * El historial se devuelve en orden descendente.
     *
     * @param numeroSerie El número de serie del vehículo.
     * @return Lista de placas asociadas al vehículo.
     * @throws PersistenciaException si ocurre un error durante la consulta.
     */
    public List<Placa> obtenerHistorialPorNumSerie(String numeroSerie) throws PersistenciaException;
    
    /**
     * Desactiva las placas activas de un vehículo antes de emitir una nueva.
     * Cambia el estado de las placas con estado activa a inactiva.
     *
     * @param numeroSerie El número de serie del vehículo.
     * @throws PersistenciaException si ocurre un error durante la actualización.
     */
    public void desactivarPlacasAnteriores(String numeroSerie) throws PersistenciaException;
}
