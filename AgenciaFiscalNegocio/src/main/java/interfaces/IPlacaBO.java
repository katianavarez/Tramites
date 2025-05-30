/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.PlacaDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio relacionadas con placas vehiculares.
 * 
 * @author katia
 */
public interface IPlacaBO {
    
    /**
     * Registra una nueva placa para un automóvil nuevo.
     *
     * @param dto Objeto con los datos de la placa y vehículo.
     * @param rfcPersona RFC de la persona.
     * @return La placa registrada con los datos completos.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en el registro.
     */
    public PlacaDTO registrarPlacaAutoNuevo(PlacaDTO dto, String rfcPersona) throws NegocioException;
    
    /**
     * Registra una nueva placa para un automóvil que ya esté en el sistema,
     * desactivando la placa anterior.
     *
     * @param dto Objeto con los datos de la nueva placa.
     * @param rfcPersona RFC de la persona.
     * @return La placa registrada.
     * @throws NegocioException Si los datos son inválidos o ocurre un error en el registro.
     */
    public PlacaDTO registrarPlacaAutoUsado(PlacaDTO dto, String rfcPersona) throws NegocioException;    
    
    /**
     * Obtiene el historial de placas de un vehículo por medio de su número de serie.
     *
     * @param numeroSerie Número de serie del vehículo.
     * @return Lista de placas que ha tenido el vehículo.
     * @throws NegocioException Si ocurre un error durante la búsqueda.
     */
    public List<PlacaDTO> obtenerHistorialPorNumSerie(String numeroSerie) throws NegocioException;
    
    /**
     * Genera una placa de forma aleatoria para autos nuevos.
     *
     * @return DTO con datos de la nueva placa.
     */
    public PlacaDTO generarPlacaAutoNuevo();
}
