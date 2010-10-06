package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class SpriteSekai extends Sprite implements Animable {
    private int posicionX,posicionY;

    public SpriteSekai(String archivoSekai, int posicionX, int posicionY) throws IOException{

        super(Image.createImage(archivoSekai),40,60);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        //int[] secuencia = {};


       //this.setFrameSequence(secuencia);

       this.setPosition(posicionX, posicionY);
    }

    public void dibujar(Graphics g) {

        this.paint(g);
    }

    public void actualizar() {

        this.nextFrame();

    }

}
