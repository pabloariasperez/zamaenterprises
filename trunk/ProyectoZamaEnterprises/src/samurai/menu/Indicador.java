
package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Indicador extends Sprite {

    private int posicionX;
    private int posicionY;
    private int crecimientoLinea;
    public Indicador(String archivoIndicador, int posicionX, int posicionY) throws IOException{
        super(Image.createImage(archivoIndicador),135,40);
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.setPosition(posicionX,posicionY);
        this.crecimientoLinea=0;
    }
    public void dibujar(Graphics g){
        if(this.crecimientoLinea<=240){
            g.drawLine(0, posicionY+20, posicionX+this.crecimientoLinea, posicionY+20);
            this.crecimientoLinea+=16;
        }else if(this.crecimientoLinea>240&&this.crecimientoLinea<260){
            g.fillRect(0, posicionY+17, posicionX+this.crecimientoLinea,6 );
            this.crecimientoLinea++;
        }else{
            this.paint(g);
        }
    }
    public void cambiarPosicion(int nuevaPosicionX,int nuevaPosicionY){
        this.posicionX=nuevaPosicionX;
        this.posicionY=nuevaPosicionY;
        this.setPosition(posicionX, posicionY);
        this.crecimientoLinea=0;
    }
}
