/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.Duracion;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author katia
 */
@Entity
@Table(name = "licencias")
@PrimaryKeyJoinColumn(name = "id")
public class Licencia extends Tramite implements Serializable {
    @Column(nullable = false)
    private LocalDate fechaExpedicion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Duracion duracion;

    public LocalDate getFechaExpedicion() {
        return fechaExpedicion;
    }

    public Duracion getDuracion() {
        return duracion;
    }

    public void setFechaExpedicion(LocalDate fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public void setDuracion(Duracion duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Licencia{" + "fechaExpedicion=" + fechaExpedicion + ", duracion=" + duracion + '}';
    }
    
    
    
}
