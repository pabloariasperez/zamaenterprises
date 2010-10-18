package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.Animable;

//Clase de Splashes
public class Splash implements Animable
{
    //Atributos básicos de un Splash
    private Image imagen;

    //Dimensiones de pantallas.
    private final int ALTO_PANTALLA;
    private final int ANCHO_PANTALLA;

    //Constructor de Splash, recibe el archivo y las coordenadas.
    public Splash(String archivo, GameCanvas gmCanvas) {
        //INtentamos crear nuestra imagen del Splash actual.
        try {
            imagen = Image.createImage(archivo);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Guardamos las dimensiones de la pantalla del dispositivo.
        ALTO_PANTALLA = gmCanvas.getHeight();
        ANCHO_PANTALLA = gmCanvas.getWidth();
    }

    //Método para dibujar.
    public void dibujar(Graphics g) {
        //Dibujamos la imagen exactamente en el centro respecto a su centro.
        g.drawImage(imagen, ANCHO_PANTALLA/2, ALTO_PANTALLA/2, Graphics.HCENTER|Graphics.VCENTER);
    }
}