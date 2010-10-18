/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 *
 * @author Pablo
 * Clase dedicada a llevar el tiempo transcurrido en cada escenario.
 */
public class TiempoEscenario {
    //El tiempo es un entero que abarca hasta 2^31 - 1 en FRAMES.
    //Aunque es un simple entero, lo metimos dentro de una clase para simular a modo de typedef.
    private int tiempo;

    //El constructor siempre inicilizará el tiempo en CERO, el escenario nunca puede iniciar en algo distinto.
    public TiempoEscenario(){
        tiempo = 0;
    }

    //El tiempo debe poderse incrementar FRAME a FRAME.
    public void incrementarTiempo(){
        tiempo+=1;
    }

    //Las demás clases deben poder saber cuál es el tiempo actual en qué transcurre el ESCENARIO.
    public int tiempoActual(){
        return tiempo;
    }

    //Vuelve el tiempo a cero.
    public void reiniciarTiempo(){
        tiempo = 0;
    }
}
