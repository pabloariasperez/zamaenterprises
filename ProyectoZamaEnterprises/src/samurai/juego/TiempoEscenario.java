/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 *
 * @author Pablo, Erik, Daniel
 * @version 1.0, octubre 2010
 * Clase dedicada a llevar el tiempo transcurrido en cada escenario.
 */
public class TiempoEscenario {
    //El tiempo es un entero que abarca hasta 2^31 - 1 en FRAMES.
    //Aunque es un simple entero, lo metimos dentro de una clase para simular a modo de typedef.
    private int tiempo;

    
    /**
     * Constructor que inicializa el tiempo en Cero
     */
    public TiempoEscenario(){
        tiempo = 0;
    }

    
    /**
     * Metodo que incrementa el tiempo unitariamente
     */
    public void incrementar(){
        tiempo+=1;
    }

    /**
     *  Metodo que regresa el tiempo transcurrido.
     * @return Regresa el tiempo que ha transcurrido desde que se inicializo el tiempo.
     */
    public int actual(){
        return tiempo;
    }

   
    /**
     * Metodo que vuelve cero el tiempo
     */
    public void reiniciar(){
        tiempo = 0;
    }

    public void setTiempo(int tiempoNuevo) {
        tiempo = tiempoNuevo;
    }
}
