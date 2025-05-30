/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package idaos;

import entidades.Persona;
import excepciones.PersistenciaException;

/**
 *
 * @author katia
 */
public interface IPersonaDAO {
    public Persona registrarPersona(Persona persona) throws PersistenciaException;
    public Persona buscarPersonaPorRFC(String rfc) throws PersistenciaException;
    public Persona buscarPersonaPorId(Long id) throws PersistenciaException;
}
