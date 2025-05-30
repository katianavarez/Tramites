/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.AutomovilDTO;
import excepciones.NegocioException;

/**
 *
 * @author katia
 */
public interface IAutomovilBO {
    public AutomovilDTO registrarAutomovil(AutomovilDTO dto) throws NegocioException;
    public AutomovilDTO buscarPorNumeroSerie(String numeroSerie) throws NegocioException;
}
