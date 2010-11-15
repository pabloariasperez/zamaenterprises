package samurai.juego;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import samurai.escenarios.Nivel;

/**
 * Clase MIDlet que controla los elementos que se muestran en el Display.
 * @author Pablo, Erik y Daniel
 * @version 1.2, octubre 2010
 */
public class SamuraiEnterprises extends MIDlet {

    //Atributos de nuestro midlet 
    //Crea un SplashCanvas que muestra el logo del Tec asi como el logo del equipo.
    private Actualizable pantallaActual;

    /**
     * Constructor del MIDlet que inicializa el SplashCanvas. =D
     */
    public SamuraiEnterprises() {
        Global.setFPS(60);
        pantallaActual=new SplashCanvas(this);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre estado activo.
     */
    public void startApp() {
        Display.getDisplay(this).setCurrent((Displayable)pantallaActual);
        pantallaActual.correr();
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este entre al estado  de pausa.
     */
    public void pauseApp() {
        if(this.pantallaActual.tipoCanvas().equals(Actualizable.JUEGO)){
            ((Juego) pantallaActual).pausarJuego();
        }

        pantallaActual.pausar();
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre al estado de destruido.
     * @param unconditional Booleano que indica si la aplicacion se debe detener o no.
     */
    public void destroyApp(boolean unconditional) {    
    }

    /**
     *
     */
    public void mostrarPuntajes() {
    }

    /**
     *
     */
    public void mostrarCreditos() {
        pantallaActual.destruir();
        pantallaActual=null;
        pantallaActual= new PresentacionCanvas(this,PresentacionCanvas.CREDITO);
        Global.setFPS(50);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     *
     */
    public void continuarJuego() {
    }

    /**
     *
     */
    public void mostrarMenu(){
        pantallaActual.destruir();
        pantallaActual=null;
        pantallaActual = new MenuCanvas(this);
        Global.setFPS(30);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     *
     */
    public void correrNivelUno() {
        pantallaActual.destruir();
        pantallaActual=null;
        pantallaActual = new Juego(this, Nivel.NIVEL_1);
        Global.setFPS(60);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }
    public void mostrarGameOver(){
        pantallaActual.destruir();
        pantallaActual=null;
        pantallaActual = new PresentacionCanvas(this,PresentacionCanvas.GAMEOVER);
        Global.setFPS(50);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }
}
