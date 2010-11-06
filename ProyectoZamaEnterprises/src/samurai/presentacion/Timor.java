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
   

    public Timor(int intervalo){
        this.intervalo = intervalo*Global.FPS;
        
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
   
    public void tik(){
        this.reloj.incrementar();
    }
}
