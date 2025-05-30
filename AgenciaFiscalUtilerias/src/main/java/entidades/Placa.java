/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import enums.EstadoPlaca;
import enums.TipoPlaca;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entidad que representa una placa vehicular registrada en la agencia fiscal.
 * Hereda de Trámite, comparte el id.
 * Un vehículo puede tener distintas placas (pero solo 1 a la vez)
 * @author katia
 */
@Entity
@Table(name = "placas")
@PrimaryKeyJoinColumn(name = "id")
public class Placa extends Tramite implements Serializable {

    @Column(nullable = false, unique = true)
    private String numeroPlaca;

    @Column(nullable = false)
    private LocalDate fechaEmision;

    @Column
    private LocalDate fechaRecepcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPlaca estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoPlaca tipo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo;

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

    public Vehiculo getVehiculo() {
        return vehiculo;
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

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public String toString() {
        return "Placa{" + "numeroPlaca=" + numeroPlaca + ", fechaEmision=" + fechaEmision + ", fechaRecepcion=" + fechaRecepcion + ", estado=" + estado + ", tipo=" + tipo + ", vehiculo=" + vehiculo + '}';
    }
    
    
}
