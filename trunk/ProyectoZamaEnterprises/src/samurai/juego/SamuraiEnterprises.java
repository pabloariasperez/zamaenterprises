package samurai.juego;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;
import samurai.almacenamiento.AdministradorData;
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
    private AdministradorData data;
    private boolean guardado;
    /**
     * Constructor del MIDlet que inicializa el SplashCanvas. =D
     */
    public SamuraiEnterprises() {
        this.data = new AdministradorData("continuar");
        this.verificarGuardado();
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
        Global.setFPS(50);
        pantallaActual= new PresentacionCanvas(this,PresentacionCanvas.TUTORIAL);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     *
     */
    public void continuarJuego() {
        
        if(guardado){
            pantallaActual.destruir();
            pantallaActual=null;

            int score = data.regresarValorDato(AdministradorData.REGISTRO_SCORE_ACTUAL);
            int vida = data.regresarValorDato(AdministradorData.REGISTRO_VIDA);
            int nivel = data.regresarValorDato(AdministradorData.REGISTRO_NIVEL);
            int tiempo = data.regresarValorDato(AdministradorData.REGISTRO_TIEMPO);
            pantallaActual = new Juego(this, nivel,score, vida, tiempo);
            Global.setFPS(60);
            Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
        }
        
    }

    private void verificarGuardado(){
        if(data.regresarDato(AdministradorData.REGISTRO_SCORE_ACTUAL)!= AdministradorData.SVacio)
            this.guardado=true;
        else
            this.guardado = false;
    }
    /**
     *
     */
    public void mostrarMenu(){
        this.verificarGuardado();
        pantallaActual.destruir();
        pantallaActual=null;
        Global.setFPS(20);
        pantallaActual = new MenuCanvas(this);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     *
     *
     */
    public void correrNivelUno() {
        pantallaActual.destruir();
        pantallaActual=null;
        pantallaActual = new Juego(this, Nivel.NIVEL_1,0,50, 0);
        Global.setFPS(60);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }
    public void mostrarGameOver(){
        pantallaActual.destruir();
        pantallaActual=null;
        Global.setFPS(50);
        pantallaActual = new PresentacionCanvas(this,PresentacionCanvas.GAMEOVER);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    public void mostrarPrologo() {
        pantallaActual.destruir();
        pantallaActual=null;
        Global.setFPS(50);
        pantallaActual = new PresentacionCanvas(this,PresentacionCanvas.PROLOGO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    public void guardarScore(int score) {
        data = new AdministradorData("Scores");
        
    }
}
