/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.LicenciaDTO;
import excepciones.NegocioException;

/**
 *
 * @author katia
 */
public interface ILicenciaBO {
    public LicenciaDTO registrarLicencia(LicenciaDTO licenciaDTO) throws NegocioException;
    public LicenciaDTO licenciaVigente(Long idPersona) throws NegocioException;
}
