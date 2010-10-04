package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Fondo extends Sprite
{
    private int posicionX;
    private int posicionY;

    public Fondo(String archivoFondo) throws IOException{
        super(Image.createImage(archivoFondo), 240,320);
        this.posicionX=0;
        this.posicionY=0;
        this.setPosition(posicionX, posicionY);
    }
    public void dibujar(Graphics g){
        this.paint(g);
    }
}
