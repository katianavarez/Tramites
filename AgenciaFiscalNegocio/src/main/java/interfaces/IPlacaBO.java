/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PlacaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author katia
 */
public interface IPlacaBO {
    public PlacaDTO registrarPlacaAutoNuevo(PlacaDTO dto, String rfcPersona) throws NegocioException;
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto, String rfcPersona) throws NegocioException;    
    public List<PlacaDTO> obtenerHistorialPorNumSerie(String numeroSerie) throws NegocioException;
    public PlacaDTO generarPlacaAutoNuevo();
}
