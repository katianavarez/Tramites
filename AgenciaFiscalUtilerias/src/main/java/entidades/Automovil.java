/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidad que representa un automóvil, extiende Vehículo. 
 * Por el momento no tiene atributos extra.
 * @author katia
 */
@Entity
@Table (name = "automoviles")
@PrimaryKeyJoinColumn(name = "id")
public class Automovil extends Vehiculo implements Serializable{
    
    
    
    
}
