
package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Indicador extends Sprite {

    private int posicionX;
    private int posicionY;
    public Indicador(String archivoIndicador, int posicionX, int posicionY) throws IOException{
        super(Image.createImage(archivoIndicador),135,40);
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.setPosition(posicionX,posicionY);
    }
    public void dibujar(Graphics g){
        this.paint(g);
    }
    public void cambiarPosicion(int nuevaPosicionX,int nuevaPosicionY){
        this.posicionX=nuevaPosicionX;
        this.posicionY=nuevaPosicionY;
        this.setPosition(posicionX, posicionY);
    }
}
