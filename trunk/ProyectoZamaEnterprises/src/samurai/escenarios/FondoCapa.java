package samurai.escenarios;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;
import samurai.juego.Global;

/**
 * se encarga de mover el fondo y dibujarlo
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class FondoCapa implements Animable {

    private Image imagen;
    private int velocidad;
    private int posicionX, posicionY;
    private int parametroVelocidad;

    /**
     * crea la imagen a ser utilizada, e inicializa variables
     * @param archivoImagen direccion de la imagen
     * @param velocidad : velocidad a la que se movera
     * @throws IllegalArgumentException
     */
    public FondoCapa(String archivoImagen, int velocidad) throws IllegalArgumentException{

        try {
            this.imagen = Image.createImage(archivoImagen);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.velocidad = velocidad;
        posicionX = 0;
        posicionY = 0;
    }

    /**
     * Mueve el fondo
     */
    public void actualizar() {
        posicionX += (-parametroVelocidad*velocidad)/100;
        if ( -parametroVelocidad > 0 && this.posicionX > Global.ANCHO_PANTALLA) {
            this.posicionX -= imagen.getWidth();
        }else if(  -parametroVelocidad < 0 && this.posicionX  < - imagen.getWidth() ){
            this.posicionX = Global.ANCHO_PANTALLA;
        }
    }
    /**
     * Dibuja el fondo
     * @param g Graficos
     */
    public void dibujar(Graphics g) {
        g.drawImage(imagen, posicionX, posicionY, Graphics.LEFT | Graphics.TOP);
        if( posicionX > 0 ){
            g.drawImage(imagen, posicionX - imagen.getWidth(), posicionY, Graphics.LEFT | Graphics.TOP);
        }else if( posicionX < 0 ){
            g.drawImage(imagen, posicionX + imagen.getWidth(), posicionY, Graphics.LEFT | Graphics.TOP);
        }
    }

    /**
     * regresa el alto de la imagen
     * @return alto de la imagen
     */
    public int getAlto() {
        return imagen.getHeight();
    }

    /**
     * cambia el parametro de velocidad
     * @param parametroVelocidad nuevo parametro
     */
    public void setParametro(int parametroVelocidad) {
        this.parametroVelocidad = parametroVelocidad;
    }
}