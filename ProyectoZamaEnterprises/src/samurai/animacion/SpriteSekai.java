package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;

/**
 * crea el Sprite de Sekai, lo dibuja y lo mueve
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteSekai extends Sprite implements Animable {
    private int posicionX,posicionY;
    private int[] secuenciaCorrer;
    private int retrasoAnimacion;
    private final int salto;

    /**
     * inicializa las variables
     * @param archivoSekai direccion de la imagen de sekai
     * @param posicionX posicion inicial en x
     * @param posicionY posicion inicial en y
     * @throws IOException si no encuentra la imagen
     */
    public SpriteSekai(String archivoSekai, int posicionX, int posicionY) throws IOException{
        super(Image.createImage(archivoSekai),40,60);
        this.posicionX = posicionX - this.getWidth()/2;
        this.posicionY = posicionY - this.getHeight();
        this.secuenciaCorrer = new int[] {0,1};
        this.retrasoAnimacion=0;
        this.salto=10;

       //this.setFrameSequence(secuencia);

       this.setPosition(posicionX, posicionY);
    }

    /**
     * Dibuja a Sekai
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {

        this.paint(g);
    }

    /**
     * Actualiza a sekai para dar sensacion de movimiento
     */
    public void actualizar() {
        if(retrasoAnimacion%8==0){
            if(this.getFrame()==0){
                this.setPosition(posicionX, posicionY+salto);
                this.nextFrame();
            }else{
                this.setPosition(posicionX, posicionY);
                this.nextFrame();
            }
            retrasoAnimacion = 0;
        }
        retrasoAnimacion++;
    }
}
