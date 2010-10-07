package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class SpriteEspada extends Sprite implements Animable {

    public int[] secuenciaIzq;
    int[] secuenciaDer;
    int[] secuenciaFrente;
    private int poscionX;
    private int poscionY;
    private int secuenciaSeleccionada;
    public SpriteEspada(String archivoEfectos, int posicionX, int posicionY) throws IOException{

        super(Image.createImage(archivoEfectos), 480/8, 180/3);

        this.poscionX=posicionX;
        this.poscionY=posicionY;
        this.setPosition(poscionX, poscionY);

        this.secuenciaIzq=new int[] {0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
        this.secuenciaDer=new int[] {8,9,9,10,10,11,11,12,12,13,13,14,14,15,15};
        this.secuenciaFrente=new int[] {16,17,17,18,18,18,19,19,20,20,21,21,21,22,22};
    }

    public void dibujar(Graphics g) {
        this.paint(g);
    }

   public void setAtaqueIzq(){
        this.setFrameSequence(secuenciaIzq);
        this.setPosition(poscionX-40, poscionY-20);
        this.secuenciaSeleccionada=1;
    }
   public void ataque(){
       this.nextFrame();      

   }
    public void setAtaqueDer(){
        this.setFrameSequence(secuenciaDer);
        this.setPosition(poscionX+20, poscionY-20);
        this.secuenciaSeleccionada=2;
    }

     public void setAtaqueFrontal(){
        this.setFrameSequence(secuenciaFrente);
        this.setPosition(poscionX-10, poscionY-40);
        this.secuenciaSeleccionada=3;
    }



   public int getSecuencia(){
       return this.secuenciaSeleccionada;
   }
}
