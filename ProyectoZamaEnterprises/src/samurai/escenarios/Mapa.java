/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.Animable;
import samurai.juego.Global;
import samurai.juego.TiempoEscenario;

/**
 *
 * @author Pablo
 */
public class Mapa implements Animable{
    private int tiempoUltimoEvento;
    private TiempoEscenario tiempo;
    private final int RADIO_CIRCULO = 5;
    
    public Mapa( int tiempoUltimoEvento, TiempoEscenario tiempo ){
        this.tiempoUltimoEvento = tiempoUltimoEvento;
        this.tiempo = tiempo;
    }
    
    public void dibujar(Graphics g) {
        g.setColor(0x00FFFFFF);
        g.fillArc(Global.ANCHO_PANTALLA/20-2*RADIO_CIRCULO, Global.ALTO_PANTALLA/5, 2*RADIO_CIRCULO, 2*RADIO_CIRCULO, 0, 360);
        g.fillRect(Global.ANCHO_PANTALLA/20-2*RADIO_CIRCULO, Global.ALTO_PANTALLA/5+RADIO_CIRCULO, RADIO_CIRCULO, Global.ALTO_PANTALLA*3/5);
        g.fillArc(Global.ANCHO_PANTALLA/20-2*RADIO_CIRCULO, Global.ALTO_PANTALLA*4/5, 2*RADIO_CIRCULO, 2*RADIO_CIRCULO, 0, 360);

        g.setColor(0x0022AA22);
        g.fillRect(Global.ANCHO_PANTALLA/20-RADIO_CIRCULO, Global.ALTO_PANTALLA/5+RADIO_CIRCULO + ((Global.ALTO_PANTALLA*3/5)*(100-tiempo.actual()*100/tiempoUltimoEvento))/100, RADIO_CIRCULO*2, RADIO_CIRCULO*2);
    }
    
}
