package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class SpriteSekai extends Sprite implements Animable {
    private int posicionX,posicionY;
    int[] secuenciaCorrer;
    private int retrasoAnimacion;
    private final int salto;

    public SpriteSekai(String archivoSekai, int posicionX, int posicionY) throws IOException{

        super(Image.createImage(archivoSekai),40,60);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.secuenciaCorrer = new int[] {0,1};
        this.retrasoAnimacion=0;
        this.salto=10;

       //this.setFrameSequence(secuencia);

       this.setPosition(posicionX, posicionY);
    }

    public void dibujar(Graphics g) {

        this.paint(g);
    }

    public void actualizar() {
        if(retrasoAnimacion%8==0){
            if(this.getFrame()==0){
                this.setPosition(posicionX, posicionY+salto);
                this.nextFrame();
            }else{
                this.setPosition(posicionX, posicionY);
                this.nextFrame();
            }
        }
        retrasoAnimacion++;
    }

}
