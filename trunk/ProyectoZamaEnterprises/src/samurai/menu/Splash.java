package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;
import samurai.juego.Global;

//Clase de Splashes
/**
 * Crea la imagen del splash y la coloca
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class Splash implements Animable
{
    //Atributos básicos de un Splash
    private Image imagen;

    /**
     * crea la imagen del splash
     * @param archivo direccion de la imagen del splash
     */
    public Splash(String archivo) {
        //INtentamos crear nuestra imagen del Splash actual.
        try {
            imagen = Global.resizeImage(Image.createImage(archivo));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método para dibujar.
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        //Dibujamos la imagen exactamente en el centro respecto a su centro.
        g.drawImage(imagen, Global.ANCHO_PANTALLA/2, Global.ALTO_PANTALLA/2, Graphics.HCENTER|Graphics.VCENTER);
    }
}