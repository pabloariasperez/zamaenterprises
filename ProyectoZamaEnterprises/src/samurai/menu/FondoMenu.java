package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class FondoMenu extends Sprite
{
    private static final int ANCHO_SPRITE = 80;
    private static final int ALTO_SPRITE = 240;
    private final int ANCHO_PANTALLA;
    private final int ALTO_PANTALLA;

    //Recibe como parametro el nombre de la imagen a utilizar como fondo a la vez que su posición.
    public FondoMenu(String archivoFondo, int anchoPantalla, int altoPantalla) throws IOException{
        super(Image.createImage(archivoFondo), FondoMenu.ANCHO_SPRITE, FondoMenu.ALTO_SPRITE);
        ANCHO_PANTALLA = anchoPantalla;
        ALTO_PANTALLA = altoPantalla;
        this.setPosition( 0, 0);
    }

    //Dibuja nuestro Sprite
    public void dibujar(Graphics g){
        //Aquí mismo se posiciona al Fondo Menú porque no tiene caso crearle más atributos y métodos.
        this.setPosition( ANCHO_PANTALLA - this.getWidth() - 10, ( ALTO_PANTALLA - this.getHeight() )/2);
        this.paint(g);
    }
}
