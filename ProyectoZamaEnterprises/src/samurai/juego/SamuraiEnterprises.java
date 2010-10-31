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
    //private MenuCanvas menuCanvas;
    private SplashCanvas splashCanvas;
    //private Juego juego;


    //Variable que guarda el estatus actual de la aplicación
    //private int estadoJuego;
    //Valores de los estatus que se pueden tener a lo largo de la aplicación
    //private final int INICIANDO_JUEGO = 0;

    Camino caminoPrueba;

    //
    /**
     * Constructor del MIDlet que inicializa el SplashCanvas. =D
     */
    public SamuraiEnterprises() {
        Global.setFPS(60);
        splashCanvas = new SplashCanvas(this);
        Global.setAltoPantalla(splashCanvas.getHeight());
        Global.setAnchoPantalla(splashCanvas.getWidth());
        System.out.println("Alto:"+Global.ALTO_PANTALLA);
        System.out.println("Ancho:"+Global.ANCHO_PANTALLA);
        caminoPrueba = new Camino(this);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre estado activo.
     * @throws MIDletStateChangeException Esta excepcion es lanzada si el MIDlet no puede iniciar.
      */
    public void startApp() {
        Display.getDisplay(this).setCurrent(caminoPrueba);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este entre al estado  de pausa.
     */
    public void pauseApp() {
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre al estado de destruido.
     * @param unconditional Booleano que indica si la aplicacion se debe detener o no.
     * @throws MIDletStateChangeException Se lanza esta excepcion si el MIDlet no ha entrado al estado de destruido
     */
    public void destroyApp(boolean unconditional) {
       caminoPrueba = null;
    }

    public void mostrarPuntajes() {
    }

    public void mostrarCreditos() {
    }

    public void continuarJuego() {
    }

    public void correrJuego() {
        Global.setFPS(60);
        //juego = new Juego(this);
    }
}
