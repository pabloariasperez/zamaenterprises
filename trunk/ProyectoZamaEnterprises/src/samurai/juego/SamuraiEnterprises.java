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
    private int puntajeObtenido = 0;

    private final int FPS_MUY_RAPIDO = 60;
    private final int FPS_RAPIDO = 50;
    private final int FPS_LENTO = 30;

    /**
     * Constructor del MIDlet que inicializa el SplashCanvas.
     */
    public SamuraiEnterprises() {
        establecerPuntajesDefault();
        Global.setFPS(FPS_MUY_RAPIDO);
        pantallaActual = new SplashCanvas(this);
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre estado activo.
     */
    public void startApp() {
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
        pantallaActual.correr();
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este entre al estado  de pausa.
     */
    public void pauseApp() {
        if (this.pantallaActual.tipoCanvas().equals(Actualizable.JUEGO)) {
            ((Juego) pantallaActual).pausarJuego();
        }

        pantallaActual.interrumpir();
    }

    /**
     * Metodo que manda una señal al MIDlet para avisarle a este que entre al estado de destruido.
     * @param unconditional Booleano que indica si la aplicacion se debe detener o no.
     */
    public void destroyApp(boolean unconditional) {
    }

    /**
     * Cambia la pantalla a la de mostrar puntajes
     */
    public void mostrarPuntajes() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_LENTO);
        pantallaActual = new MostrarPuntajesCanvas(this);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Cambia la pantalla a la de mostrar creditos
     */
    public void mostrarCreditos() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.CREDITO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Metodo que Cambia la pantalla a la de juego continuando donde se habia quedado
     */
    public void continuarJuego() {
        AdministradorData data = new AdministradorData(AdministradorData.STORE_AVANCE);
        pantallaActual.destruir();
        pantallaActual = null;

        int score = data.regresarValorDato(AdministradorData.REGISTRO_SCORE_ACTUAL);
        int vida = data.regresarValorDato(AdministradorData.REGISTRO_VIDA);
        int nivel = data.regresarValorDato(AdministradorData.REGISTRO_NIVEL);
        int tiempo = data.regresarValorDato(AdministradorData.REGISTRO_TIEMPO);
        pantallaActual = new Juego(this, nivel, score, vida, tiempo);
        Global.setFPS(FPS_MUY_RAPIDO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Cambia la pantalla a la del menu
     */
    public void mostrarMenu() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_LENTO);
        pantallaActual = new MenuCanvas(this, false);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Cambia la pantalla a la de mostrar GameOver
     */
    public void mostrarGameOver() {
        if (this.pantallaActual.tipoCanvas().equals(Actualizable.JUEGO)) {
            this.puntajeObtenido = ((Juego) pantallaActual).getPuntaje();
        }
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.GAMEOVER);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Cambia la pantalla a la de mostrar prologo
     */
    public void mostrarPrologo() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.PROLOGO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * verifica si el puntaje nuevo rompio algun record
     * si lo rompio Cambia la pantalla a la de captura de puntajes
     * si no a la de mostrar puntajes
     */
    public void verificarNuevoPuntaje() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_LENTO);
        if (CapturaPuntajesCanvas.esNuevoPuntajeAlto(puntajeObtenido)) {
            pantallaActual = new CapturaPuntajesCanvas(this, puntajeObtenido);
            Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
        } else {
            pantallaActual = new MostrarPuntajesCanvas(this);
            Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
        }

    }

    private void establecerPuntajesDefault() {
        AdministradorData puntajesEstablecidos = new AdministradorData(AdministradorData.STORE_PUNTAJES_ESTABLECIDOS);
        if (puntajesEstablecidos.regresarValorDato(1) == AdministradorData.VALOR_ERROR) {
            puntajesEstablecidos.agregarRegistro(1);
            puntajesEstablecidos = null;

            AdministradorData puntajesDefault = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + "1");
            puntajesDefault.agregarRegistro("ZAMA");
            puntajesDefault.agregarRegistro(2100);

            puntajesDefault = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + "2");
            puntajesDefault.agregarRegistro("LAMB");
            puntajesDefault.agregarRegistro(700);

            puntajesDefault = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + "3");
            puntajesDefault.agregarRegistro("JUNCO");
            puntajesDefault.agregarRegistro(300);

            for( int c=Global.NUMERO_PUNTAJES_ALMACENADOS - 3; c>0; c--){
                puntajesDefault = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + c);
                puntajesDefault.agregarRegistro("...");
                puntajesDefault.agregarRegistro(25*c);
            }
        }
    }

    /**
     * Cambia la pantalla a la de mostrar tutorial
     */
    public void mostrarTutorial() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.TUTORIAL);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Cambia la pantalla a la del menu sonido
     */
    public void mostrarMenuSonido() {
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_LENTO);
        pantallaActual = new MenuCanvas(this, true);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    /**
     * Corre en nivel 1
     */
    public void correrNivelUno() {
        pantallaActual.destruir();
        pantallaActual = null;
        pantallaActual = new Juego(this, Nivel.NIVEL_1, Juego.SCORE_INICIAL, ManejadorSekai.VIDA_TOTAL, Juego.TIEMPO_INICIAL);
        Global.setFPS(FPS_MUY_RAPIDO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    public void transicionNivelDos( int score ) {
        puntajeObtenido = score;
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.NIVEL_2);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
        
    }

    public void correrNivelDos() {
        pantallaActual.destruir();
        pantallaActual = null;
        pantallaActual = new Juego(this, Nivel.NIVEL_2, puntajeObtenido, ManejadorSekai.VIDA_TOTAL, Juego.TIEMPO_INICIAL);
        Global.setFPS(FPS_MUY_RAPIDO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    public void transicionNivelTres( int score ) {
        puntajeObtenido = score;
        pantallaActual.destruir();
        pantallaActual = null;
        Global.setFPS(FPS_RAPIDO);
        pantallaActual = new PresentacionCanvas(this, PresentacionCanvas.NIVEL_3);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }

    public void correrNivelTres() {
        pantallaActual.destruir();
        pantallaActual = null;
        pantallaActual = new BossArea();
        Global.setFPS(FPS_MUY_RAPIDO);
        Display.getDisplay(this).setCurrent((Displayable) pantallaActual);
    }
}