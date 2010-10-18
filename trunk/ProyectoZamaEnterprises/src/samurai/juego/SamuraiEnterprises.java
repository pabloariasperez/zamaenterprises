package samurai.juego;


import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class SamuraiEnterprises extends MIDlet {

    //Atributos de nuestro midlet
    //Crea un MenuCanvas en primer lugar para que el usuario pueda elegir que quiere hacer.
    private MenuCanvas menuCanvas;
    private SplashCanvas splashCanvas;
    //private Juego juego;

    //Inicializa el MenuCanvas. =D
    public SamuraiEnterprises() {
        splashCanvas = new SplashCanvas(this);
    }

    public void startApp() {
        while( splashCanvas.estoyMostrandome() ){
            Display.getDisplay(this).setCurrent(splashCanvas);
        }
        menuCanvas = new MenuCanvas(this);
        Display.getDisplay(this).setCurrent(menuCanvas);
        splashCanvas = null;
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
       splashCanvas = null;
    }

    void mostrarPuntajes() {
    }

    void mostrarCreditos() {
    }

    void continuarJuego() {
    }

    void correrJuego() {
    }
}
