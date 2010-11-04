/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.presentacion;

import samurai.juego.Global;
import samurai.juego.TiempoEscenario;

/**
 *
 * @author mi16
 */
public class Timor {

    private TiempoEscenario reloj;
    private final  int intervalo;
    private final int iteraciones;
    private int iteracionesActuales;

    public Timor(int intervalo, int iteraciones){
        this.intervalo = intervalo*Global.FPS;
        this.iteraciones = iteraciones;
        this.iteracionesActuales=0;
        this.reloj = new TiempoEscenario();

    }

    public boolean activarIteracion(){

        if(this.reloj.actual() == this.intervalo){
           this.reloj.reiniciar();
           //this.iteracionesActuales++;
           return true;
        }
        return false;

    }
    public boolean timerTerminado(){
        if(this.iteracionesActuales>=this.iteraciones)
            return true;
        return false;

    }
    public void tik(){
        this.reloj.incrementar();
    }
}
