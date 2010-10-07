package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author mi16
 */
public class SpriteEnemigo  extends Sprite implements Animable {
    private int posicionX,posicionY;
    int[] secuenciaFondo, secuenciaMedia, secuenciaMediaFrente, secuenciaFrente;

    public SpriteEnemigo(String archivoEnemigo, int posicionX, int posicionY) throws IOException{

        super(Image.createImage(archivoEnemigo),240/4,240/4);
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.secuenciaFondo=new int[]{0,1,2,3};
        this.secuenciaMedia=new int[]{4,5,6,7};
        this.secuenciaMediaFrente=new int[]{8,9,10,11};
        this.secuenciaFrente = new int[]{12,13,14,15};
        this.setFrameSequence(this.secuenciaFondo);

       this.setPosition(posicionX, posicionY);
       this.posicionX = posicionX;
       this.posicionY = posicionY;
    }

    public void dibujar(Graphics g) {

        this.paint(g);
    }

    public void mover() {

        this.posicionY++;
        if(this.posicionY>70)
            this.setFrameSequence(secuenciaMedia);
        if(this.posicionY>90)
            this.setFrameSequence(secuenciaMediaFrente);

        this.nextFrame();


    }

}