/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 *
 * @author Pablo
 */
public class Global {
    //Variables globales
    public static int ANCHO_PANTALLA = 0;
    public static int ALTO_PANTALLA = 0;
    public static int FPS;

    //Hacemos que la variable estática pueda ser modificada por un método estático.
    //El valor del ANCHO no puede ser modificado más que una sola vez para toda la ejecución.
    public static void setAnchoPantalla( int nuevoAnchoPantalla ){
        if( ANCHO_PANTALLA == 0){
            ANCHO_PANTALLA = nuevoAnchoPantalla;
        }
    }

    //El valor del ALTO no puede ser modificado más que una sola vez para toda la ejecución.
    public static void setAltoPantalla( int nuevoAltoPantalla ){
        if( ALTO_PANTALLA == 0 ){
            ALTO_PANTALLA = nuevoAltoPantalla;
        }
    }

    //Los FPS pueden ser moficados varias  veces en la aplicación según lo requiera cada Canvas.
    public static void setFPS( int nuevoFPS ){
        FPS = nuevoFPS;
    }
}