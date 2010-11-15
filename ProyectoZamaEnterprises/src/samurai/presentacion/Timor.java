/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.presentacion;

import samurai.juego.Global;
import samurai.juego.TiempoEscenario;

/**
 * Clase encargada mandar una señal cada vez que termine un tiempo determinado
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class Timor {

    private TiempoEscenario reloj;
    private final  int intervalo;
   

    /**
     * constructor inicializa variables
     * @param intervalo tiempo antes de dar señal
     */
    public Timor(int intervalo){
        this.intervalo = intervalo*Global.FPS;
        
        this.reloj = new TiempoEscenario();

    }

    /**
     * chaca si el intervalo se a cumplido
     * @return true si se cumple el tiempo de intevalo
     */
    public boolean activarIteracion(){
        if(this.reloj.actual() == this.intervalo){
           this.reloj.reiniciar();
           return true;
        }
        return false;
    }
   
    /**
     * incrementa el tiempo del reloj
     */
    public void tik(){
        this.reloj.incrementar();
    }
}
