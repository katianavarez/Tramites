/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

import enums.EstadoPlaca;
import enums.TipoPlaca;
import java.time.LocalDate;

/**
 * DTO que simboliza la información de una placa de la agencia fiscal.
 * Hereda atributos de TramiteDTO y tiene datos extra del vehículo.
 * @author katia
 */
public class PlacaDTO extends TramiteDTO{
    private String numeroPlaca;
    private LocalDate fechaEmision;
    private LocalDate fechaRecepcion;
    private EstadoPlaca estado;
    private TipoPlaca tipo;
    private Long idVehiculo;
    
    private String numeroSerie;
    private String marca;
    private String linea;
    private String color;
    private int modelo;

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
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

    public String getMarca() {
        return marca;
    }

    public String getLinea() {
        return linea;
    }

    public String getColor() {
        return color;
    }

    public int getModelo() {
        return modelo;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public void setFechaEmision(LocalDate fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "PlacaDTO{" + "numeroPlaca=" + numeroPlaca + ", fechaEmision=" + fechaEmision + ", fechaRecepcion=" + fechaRecepcion + ", estado=" + estado + ", tipo=" + tipo + ", idVehiculo=" + idVehiculo + '}';
    }
    
    
}
