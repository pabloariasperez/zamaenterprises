package samurai.juego;


import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import samurai.almacenamiento.AdministradorData;
import samurai.presentacion.Diapositiva;

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
    TextBox t;
    AdministradorData d;


    //Variable que guarda el estatus actual de la aplicación
    //private int estadoJuego;
    //Valores de los estatus que se pueden tener a lo largo de la aplicación
    //private final int INICIANDO_JUEGO = 0;


    //
    /**
     * Constructor del MIDlet que inicializa el SplashCanvas. =D
     */
    public SamuraiEnterprises() {
        Global.setFPS(60);
        t = new TextBox("Prueba Manejador de datos", null, 256, TextField.ANY);
        d = new AdministradorData("prueba1");
        d.agregarRegistro("prosopopeya");
        d.agregarRegistro("wola");
        String completo = d.regresarRegistroCompleto();
        String dato4 = d.regresarDato(2);

        d.borrarTodo();

        d.agregarRegistro("lafrance");
        d.agregarRegistro("borrego");
        String completo2 = d.regresarRegistroCompleto();
        String dato2 = d.regresarDato(2);
        d.cambiarRegistro("bifurcacion", 2);

        t.setString(completo + "\n"+ dato4 + "\n" +  completo2 + "\n" + dato2 + d.regresarDato(2));
       // splashCanvas=new SplashCanvas(this);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre estado activo.
     */
    public void startApp() {
       // Display.getDisplay(this).setCurrent(splashCanvas);
        Display.getDisplay(this).setCurrent(t);
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
        this.presentacionCanvas = new PresentacionCanvas(this,Diapositiva.CREDITO);
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
