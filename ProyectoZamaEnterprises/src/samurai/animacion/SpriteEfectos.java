/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.ManejadorTeclado;

/**
 *
 * @author mi16
 */
public class SpriteEfectos extends Sprite implements Animable {

    public int[] secuenciaIzq;
    int[] secuenciaDer;
    int[] secuenciaFrente;
    private int poscionX;
    private int poscionY;
    private int secuenciaSeleccionada;
    public SpriteEfectos(String archivoEfectos, int posicionX, int posicionY) throws IOException{
        super(Image.createImage(archivoEfectos), 420/7, 180/3);

        this.poscionX=posicionX;
        this.poscionY=posicionY;

        this.secuenciaIzq=new int[] {0,0,1,1,2,2,3,3,4,4,5,5,6,6};
        this.secuenciaDer=new int[] {7,7,8,8,9,9,10,10,11,11, 12,12,13,13};
        this.secuenciaFrente=new int[] {14,14,14,14,15,15,15,15,15,15,16,16,16,16};
    }

    public void dibujar(Graphics g) {
        this.paint(g);
    }

   public void setAtaqueIzq(){
        this.setFrameSequence(secuenciaIzq);
        this.secuenciaSeleccionada=1;
    }
   public void ataque(){
       this.nextFrame();      

   }
    public void setAtaqueDer(){
        this.setFrameSequence(secuenciaDer);
        this.secuenciaSeleccionada=2;
    }

     public void setAtaqueFrontal(){
        this.setFrameSequence(secuenciaFrente);
        this.secuenciaSeleccionada=3;
    }



   public int getSecuencia(){
       return this.secuenciaSeleccionada;
   }
}
