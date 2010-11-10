/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;


/**
 * se encarga de mover el fondo y dibujarlo
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class FondoCapa implements Animable{

    private Image imagen;
    private int mover;
    private int posicionX, posicionY;

    /**
     * crea la imagen a ser utilizada, e inicializa variables
     * @param archivoImagen direccion de la imagen
     * @param velocidad : velocidad a la que se movera
     * @param posicionY : posicion y
     */
    public FondoCapa(String archivoImagen, int velocidad, int posicionY){
       
        try {
            this.imagen = Image.createImage(archivoImagen);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
;
        this.mover = velocidad;
        this.posicionY = posicionY;
        this.posicionX =0;

    }


    /**
     * mueve el fondo
     */
    public void actualizar() {
        posicionX+=this.mover;
        if(this.posicionX<=-imagen.getWidth())
            this.posicionX=0;
    }
    public void dibujar(Graphics g){
        g.drawImage(imagen, posicionX, posicionY, Graphics.LEFT|Graphics.TOP);
       if(posicionX<-(imagen.getWidth()-240))
            g.drawImage(imagen, posicionX+imagen.getWidth(), posicionY, Graphics.LEFT|Graphics.TOP);
   }

    /**
     * regresa el alto de la imagen
     * @return alto de la imagen
     */
    public int getAlto() {
        return imagen.getHeight();
    }

}
