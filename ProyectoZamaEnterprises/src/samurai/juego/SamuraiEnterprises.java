package samurai.juego;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

/**
 * Clase MIDlet que controla los elementos que se muestran en el Display.
 * @author Pablo, Erik y Daniel
 * @version 1.2, octubre 2010
 */
public class SamuraiEnterprises extends MIDlet {

    //Atributos de nuestro midlet 
    //Crea un SplashCanvas que muestra el logo del Tec asi como el logo del equipo.
    private MenuCanvas menuCanvas;
    private SplashCanvas splashCanvas;
    private PresentacionCanvas creditos;
    private Actualizable pantallaActual;
    private PresentacionCanvas presentacionCanvas;
    private Juego juego;

    /**
     * Constructor del MIDlet que inicializa el SplashCanvas. =D
     */
    public SamuraiEnterprises() {
        Global.setFPS(60);
        splashCanvas=new SplashCanvas(this);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre estado activo.
     */
    public void startApp() {
        Display.getDisplay(this).setCurrent(splashCanvas);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este entre al estado  de pausa.
     */
    public void pauseApp() {
        if(this.pantallaActual==this.juego){
            this.juego.pausarJuego();
        }
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
        this.presentacionCanvas = new PresentacionCanvas(this,PresentacionCanvas.CREDITO);
        Global.setFPS(50);
        Display.getDisplay(this).setCurrent(this.presentacionCanvas);
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
        presentacionCanvas = null;
        splashCanvas = null;
        if(menuCanvas==null){
            menuCanvas = new MenuCanvas(this);
        }
        Global.setFPS(30);
        Display.getDisplay(this).setCurrent(menuCanvas);
    }

    /**
     *
     */
    public void correrJuego() {
        menuCanvas = null;
        if(juego==null){
            juego = new Juego(this);
        }
        Global.setFPS(60);
        Display.getDisplay(this).setCurrent(juego);
    }
}
