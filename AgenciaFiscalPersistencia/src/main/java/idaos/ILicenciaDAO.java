/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package idaos;

import entidades.Licencia;
import excepciones.PersistenciaException;

/**
 *
 * @author katia
 */
public interface ILicenciaDAO {
    public Licencia registrarLicencia(Licencia licencia) throws PersistenciaException;
    
}
