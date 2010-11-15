/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 * Clase que contiene variables globales
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class Global {
    /**
     * Ancho de la pantalla
     */
    public static int ANCHO_PANTALLA = 0;
    /**
     * alto de la pantalla
     */
    public static int ALTO_PANTALLA = 0;
    /**
     * los frames per second
     */
    public static int FPS;

    public static boolean SONIDO_ACTIVADO = false;

    /**
     * modifica ANCHO_PANTALLA solo una vez
     * @param nuevoAnchoPantalla nuevo valor
     */
    public static void setAnchoPantalla( int nuevoAnchoPantalla ){
        if( ANCHO_PANTALLA == 0){
            ANCHO_PANTALLA = nuevoAnchoPantalla;
        }
    }
    /**
     * modifica ALTO_PANTALLA solo una vez
     * @param nuevoAltoPantalla nuevo valor
     */
    public static void setAltoPantalla( int nuevoAltoPantalla ){
        if( ALTO_PANTALLA == 0 ){
            ALTO_PANTALLA = nuevoAltoPantalla;
        }
    }
    /**
     * Los FPS pueden ser moficados varias  veces en la aplicación según lo requiera cada Canvas.
     * @param nuevoFPS nuevo valor
     */
    public static void setFPS( int nuevoFPS ){
        FPS = nuevoFPS;
    }
    public static void sonidoOn(){
        SONIDO_ACTIVADO=true;
    }
    public static void sonidoOff(){
        SONIDO_ACTIVADO=false;
    }
}