/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.app.modelo;

import lombok.Data;

@Data

public class ResultadoTO {

    public int idResultado, punto;
    public String nombrePartida, nombreJugador1, nombreJugador2, ganador, estado;
}
