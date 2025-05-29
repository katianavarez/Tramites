/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Automovil;
import excepciones.PersistenciaException;

/**
 *
 * @author katia
 */
public interface IAutomovilDAO {
    public Automovil registrarAutomovil(Automovil automovil) throws PersistenciaException;
    public Automovil buscarPorNumeroSerie(String numeroSerie) throws PersistenciaException;
}
