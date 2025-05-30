/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package inicio;

import control.Control;

/**
 *
 * @author katia
 */
public class Inicio {
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        Control.getInstancia().iniciar();
    });
}
}
