/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;


public class FondoCapa implements Animable{

    private Image imagen;
    private int mover;
    private int posicionX, posicionY;

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


    public void dibujar(Graphics g) {
        posicionX+=this.mover;
                if((this.posicionX-240)<= -this.imagen.getWidth())
                    this.posicionX=0;
        g.drawImage(imagen,posicionX,posicionY, g.LEFT|g.TOP);
    }

}
