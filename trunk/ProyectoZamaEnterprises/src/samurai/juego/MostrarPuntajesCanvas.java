/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.almacenamiento.AdministradorData;

/**
 *
 * @author Pablo
 */
class MostrarPuntajesCanvas extends GameCanvas implements Actualizable{
    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private ManejadorTeclado teclado;

    public MostrarPuntajesCanvas(SamuraiEnterprises samuraiMidlet) {
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        this.g = this.getGraphics();
        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);

        AdministradorData puntaje;
        for( int c=Global.NUMERO_PUNTAJES_ALMACENADOS; c>0; c--){
            puntaje = new AdministradorData(AdministradorData.STORE_PUNTAJE_+c);
            System.out.println(puntaje.regresarDato(AdministradorData.REGISTRO_INICIALES));
            System.out.println(puntaje.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE));
        }
    }

    public void actualizar() {
    }

    public void dibujar() {
    }

    public String tipoCanvas() {
        return null;
    }

    public void destruir() {
    }

    public void pausar() {
    }

    public void correr() {
    }

}
