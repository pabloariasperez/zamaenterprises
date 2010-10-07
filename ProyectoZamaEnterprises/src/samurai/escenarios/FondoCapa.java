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
//public void actualizar(){
//        x--;
//
//        if ( x<=-imagen.getWidth()) { // Si ya se salió completamente la imagen
//            x=0;    // Reinicia
//
//        }
//    }
//    public void dibujar(Graphics g){
//        g.drawImage(imagen, x, y, Graphics.LEFT|Graphics.TOP);
//        if(x<-(imagen.getWidth()-Juego1.ANCHO))
//            g.drawImage(imagen, x+imagen.getWidth(), y, Graphics.LEFT|Graphics.TOP);
//    }
}
