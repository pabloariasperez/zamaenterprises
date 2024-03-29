package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;

/**
 * Crea el sprite de la espada y la anima
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteEspada extends Sprite implements Animable {

    /**
     * secuencia del ataque a la izquierda
     */
    public int[] secuenciaIzq;
    /**
     * secuencia del ataque a la derecha
     */
    public int[] secuenciaDer;
    /**
     * secuencia del ataque frontal
     */
    public int[] secuenciaFrente;
    private int poscionX;
    private int poscionY;
    private int secuenciaSeleccionada;
    /**
     * inicializa todos los parametros y crea el sprite
     * @param archivoEfectos direccion de la imagen de la espada
     * @param posicionX posicion inicial en x
     * @param posicionY posicion inicial en y
     * @throws IOException si no se encuentra el archivo de la imagen
     */
    public SpriteEspada(String archivoEfectos, int posicionX, int posicionY) throws IOException{
        super(Image.createImage(archivoEfectos), 360/6, 210/3);
        this.poscionX=posicionX;
        this.poscionY=posicionY;
        this.setPosition(poscionX, poscionY);

      // this.secuenciaIzq=new int[] {0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
       // this.secuenciaDer=new int[] {8,9,9,10,10,11,11,12,12};
       // this.secuenciaFrente=new int[] {16,17,17,18,18,18,19,19,};


        this.secuenciaIzq=new int[] {0,1,2,3,4};
        this.secuenciaDer=new int[] {6,7,8,9,10};
        this.secuenciaFrente=new int[] {12,14,15,16,17};
    }

    /**
     * Dibuja al sprite
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        this.paint(g);
    }

    /**
     * pasa al siguinte frame de la secuencia actual
     */
    public void ataque(){
       this.nextFrame();
   }

    /**
     * Cambia la secuancia a animar a la secuencia izquierda
     */
    public void setAtaqueIzq(){
        this.setFrameSequence(secuenciaIzq);
        this.setPosition(poscionX-getWidth()*9/8, poscionY - getHeight()*1/10);
        this.secuenciaSeleccionada=1;
    }
   /**
    * Cambia la secuancia a animar a la secuencia derecha
    */
   public void setAtaqueDer(){
        this.setFrameSequence(secuenciaDer);
        this.setPosition(poscionX+getWidth()/7, poscionY - getHeight()*1/10);
        this.secuenciaSeleccionada=2;
    }

    /**
     * Cambia la secuancia a animar a la secuencia frontal
     */
    public void setAtaqueFrontal(){
        this.setFrameSequence(secuenciaFrente);
        this.setPosition(poscionX-getWidth()/2, poscionY-getHeight()*2/5);
        this.secuenciaSeleccionada=3;
    }
    /**
     * Regresa la secuencia actual
     * @return Regresa la secuencia actual
     */
    public int getSecuencia(){
       return this.secuenciaSeleccionada;
   }
}
