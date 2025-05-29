/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author katia
 */
public class TramiteDTO {
    private Long id;
    private Double costo;
    private Long idPersona;

    public Long getId() {
        return id;
    }

    public Double getCosto() {
        return costo;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    @Override
    public String toString() {
        return "TramiteDTO{" + "id=" + id + ", costo=" + costo + ", idPersona=" + idPersona + '}';
    }
    
}
