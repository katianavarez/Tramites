/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Automovil;
import excepciones.PersistenciaException;

/**
 * Interfaz que define las operaciones de acceso a datos de la entidad Automovil.
 * @author katia
 */
public interface IAutomovilDAO {
    /**
     * Registra un nuevo auto en l abase de datos.
     * @param automovil Automóvil a registrar.
     * @return Objeto Automovil registrado.
     * @throws PersistenciaException En caso de error durante la transacción.
     */
    public Automovil registrarAutomovil(Automovil automovil) throws PersistenciaException;
    
    /**
     * Busca un automóvil por medio de su número de serie.
     * @param numeroSerie número de serie del automóvil a buscar.
     * @return El automóvil encontrado, o una excepción si no existe.
     * @throws PersistenciaException si ocurre un error al consultar la base de datos.
     */
    public Automovil buscarPorNumeroSerie(String numeroSerie) throws PersistenciaException;
}
