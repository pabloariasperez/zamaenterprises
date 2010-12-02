/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.juego;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

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
    /**
     * los frames per second
     */
    public static int dificultad=Global.DIFICULTAD_MEDIA;
    /**
     * los frames per second
     */
    public static final int NUMERO_PUNTAJES_ALMACENADOS = 3;
    /**
     *
     */
    public static boolean SONIDO_ACTIVADO = false;
    /**
     *
     */
    public static final int DIGITOS_PUNTAJES = 6;
    /**
     *
     */
    public static final int AnchoSTD = 240;
    /**
     *
     */
    public static final int AltoSTD = 320;
    public static final int DIFICULTAD_DIFICIL = 2;
    public static final  int DIFICULTAD_MEDIA = 15;
    public static final int DIFICULTAD_FACIL = 20;

    /**
     * modifica ANCHO_PANTALLA solo una vez
     * @param nuevoAnchoPantalla nuevo valor
     */
    public static void setAnchoPantalla(int nuevoAnchoPantalla) {
        if (ANCHO_PANTALLA == 0) {
            ANCHO_PANTALLA = nuevoAnchoPantalla;
        }
    }

    /**
     * modifica ALTO_PANTALLA solo una vez
     * @param nuevoAltoPantalla nuevo valor
     */
    public static void setAltoPantalla(int nuevoAltoPantalla) {
        if (ALTO_PANTALLA == 0) {
            ALTO_PANTALLA = nuevoAltoPantalla;
        }
    }

    /**
     * Los FPS pueden ser moficados varias  veces en la aplicación según lo requiera cada Canvas.
     * @param nuevoFPS nuevo valor
     */
    public static void setFPS(int nuevoFPS) {
        FPS = nuevoFPS;
    }

    /**
     *
     */
    public static void sonidoOn() {
        SONIDO_ACTIVADO = true;
    }

    /**
     *
     */
    public static void sonidoOff() {
        SONIDO_ACTIVADO = false;
    }

//    private static int[] reescalaArray(int[] ini, int x, int y, int x2, int y2) {
//        int out[] = new int[x2 * y2];
//        for (int yy = 0; yy < y2; yy++) {
//            int dy = yy * y / y2;
//            for (int xx = 0; xx < x2; xx++) {
//                int dx = xx * x / x2;
//                out[(x2 * yy) + xx] = ini[(x * dy) + dx];
//            }
//        }
//        return out;
//    }
//
//    public static Image resizeImage(Image temp) {
//        int ancho = (int) ((temp.getWidth() * Global.ANCHO_PANTALLA) / Global.AnchoSTD);
//        int alto = (int) ((temp.getHeight() * Global.ALTO_PANTALLA) / Global.AltoSTD);
//        //Need an array (for RGB, with the size of original image)
//        int rgb[] = new int[temp.getWidth() * temp.getHeight()];
//        //Get the RGB array of image into "rgb"
//        temp.getRGB(rgb, 0, temp.getWidth(), 0, 0, temp.getWidth(), temp.getHeight());
//        //Call to our function and obtain RGB2
//        int rgb2[] = reescalaArray(rgb, temp.getWidth(), temp.getHeight(), ancho, alto);
//        //Create an image with that RGB array
//        Image temp2 = Image.createRGBImage(rgb2, ancho, alto, true);
//        return temp2;
//    }
//
//    public static Image resizeSprite(Image temp, int framesHorizontal, int framesVertical) {
//        int ancho = (int) ((temp.getWidth() * Global.ANCHO_PANTALLA) / Global.AnchoSTD);
//        while (ancho % framesHorizontal != 0) {
//            ancho++;
//        }
//        int alto = (int) ((temp.getHeight() * Global.ALTO_PANTALLA) / Global.AltoSTD);
//        while (alto % framesVertical != 0) {
//            alto++;
//        }
//        //Need an array (for RGB, with the size of original image)
//        int rgb[] = new int[temp.getWidth() * temp.getHeight()];
//        //Get the RGB array of image into "rgb"
//        temp.getRGB(rgb, 0, temp.getWidth(), 0, 0, temp.getWidth(), temp.getHeight());
//        //Call to our function and obtain RGB2
//        int rgb2[] = reescalaArray(rgb, temp.getWidth(), temp.getHeight(), ancho, alto);
//        //Create an image with that RGB array
//        Image temp2 = Image.createRGBImage(rgb2, ancho, alto, true);
//        return temp2;
//    }
}
