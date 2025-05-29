/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 *
 * @author katia
 */
public class VehiculoDTO {
    private Long id;
    private String numeroSerie;
    private String marca;
    private String linea;
    private String color;
    private Integer modelo;

    public Long getId() {
        return id;
    }

    public String getNumeroSerie() {
        return numeroSerie;
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

    public Integer getModelo() {
        return modelo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
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

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return "VehiculoDTO{" + "id=" + id + ", numeroSerie=" + numeroSerie + ", marca=" + marca + ", linea=" + linea + ", color=" + color + ", modelo=" + modelo + '}';
    }
    
    
}
