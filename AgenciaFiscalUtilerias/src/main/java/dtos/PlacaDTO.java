/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoPlaca;
import enums.TipoPlaca;
import java.time.LocalDate;

/**
 *
 * @author katia
 */
public class PlacaDTO extends TramiteDTO{
    private String numeroPlaca;
    private LocalDate fechaEmision;
    private LocalDate fechaRecepcion;
    private EstadoPlaca estado;
    private TipoPlaca tipo;
    private Long idVehiculo;

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    public LocalDate getFechaRecepcion() {
        return fechaRecepcion;
    }

    public EstadoPlaca getEstado() {
        return estado;
    }

    public TipoPlaca getTipo() {
        return tipo;
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setFechaRecepcion(LocalDate fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public void setEstado(EstadoPlaca estado) {
        this.estado = estado;
    }

    public void setTipo(TipoPlaca tipo) {
        this.tipo = tipo;
    }

    public void setIdVehiculo(Long idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    @Override
    public String toString() {
        return "PlacaDTO{" + "numeroPlaca=" + numeroPlaca + ", fechaEmision=" + fechaEmision + ", fechaRecepcion=" + fechaRecepcion + ", estado=" + estado + ", tipo=" + tipo + ", idVehiculo=" + idVehiculo + '}';
    }
    
    
}
