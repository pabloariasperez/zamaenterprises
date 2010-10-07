package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class FondoMenu extends Sprite
{
    private int posicionX;
    private int posicionY;

    //Recibe como parametro el nombre de la imagen a utilizar como fondo
    public FondoMenu(String archivoFondo) throws IOException{
        super(Image.createImage(archivoFondo), 240,320);
        this.posicionX=0;
        this.posicionY=0;
        this.setPosition(posicionX, posicionY);
    }
    public void dibujar(Graphics g){
        this.paint(g);
    }
}
