/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AutomovilDTO;
import excepciones.NegocioException;

/**
 * Interfaz que define las operaciones de la capa de negocio relacionadas con automóviles.
 * 
 * @author katia
 */
public interface IAutomovilBO {
    
    /**
     * Registra un nuevo automóvil en el sistema.
     *
     * @param dto Objeto AutomovilDTO con los datos del automóvil a registrar.
     * @return El automóvil registrado.
     * @throws NegocioException si ocurre un error en la lógica de negocio al registrar.
     */
    public AutomovilDTO registrarAutomovil(AutomovilDTO dto) throws NegocioException;
    
    /**
     * Busca un automóvil en el sistema por su número de serie.
     *
     * @param numeroSerie El número de serie del automóvil.
     * @return El automóvil encontrado como AutomovilDTO.
     * @throws NegocioException si el automóvil no existe o si ocurre un error en la consulta.
     */
    public AutomovilDTO buscarPorNumeroSerie(String numeroSerie) throws NegocioException;
}
