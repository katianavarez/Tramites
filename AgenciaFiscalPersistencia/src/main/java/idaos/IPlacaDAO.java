/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Placa;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IPlacaDAO {
    public Placa registrarPlaca(Placa placa) throws PersistenciaException;
    public List<Placa> obtenerHistorialPorNumSerie(String numeroSerie) throws PersistenciaException;

}
