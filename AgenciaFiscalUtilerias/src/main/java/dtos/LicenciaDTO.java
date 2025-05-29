/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.Duracion;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class LicenciaDTO extends TramiteDTO{
    private LocalDate fechaExpedicion;
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
        return "LicenciaDTO{" + "fechaExpedicion=" + fechaExpedicion + ", duracion=" + duracion + '}';
    }
    
}
