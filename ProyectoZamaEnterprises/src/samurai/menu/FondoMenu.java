package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;

/**
 * Sprite del fondo a usar en el menu y se encarga de posicionarlo
 * @author Pablo, Erik, Daniel
 * @version 1.1 Octubre 2010
 */
public class FondoMenu extends Sprite
{
    private static final int ANCHO_SPRITE = 80;
    private static final int ALTO_SPRITE = 240;
    private static final int MARGEN_DERECHO = 10;
    private int retrasoAnimacion;

    /**
     * Recibe como parametro el nombre de la imagen a utilizar como fondo a la vez que su posición.
     * @param archivoFondo direccion de la imagen del fondo
     * @throws IOException no se encontro el archivo del fondo
     */
    public FondoMenu(String archivoFondo) throws IOException{
        super(Image.createImage(archivoFondo), FondoMenu.ANCHO_SPRITE, FondoMenu.ALTO_SPRITE);
        this.retrasoAnimacion = 0;
        Image imagen =Global.resizeSprite(Image.createImage(archivoFondo),3,1);
        super.setImage(imagen, imagen.getWidth()/3, imagen.getHeight());
    }

    /**
     * Dibuja nuestro Sprite
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g){
        //Lo posiciono =D
        this.setPosition( Global.ANCHO_PANTALLA - this.getWidth() - MARGEN_DERECHO, ( Global.ALTO_PANTALLA - this.getHeight() )/2);
        
        if(retrasoAnimacion%Global.FPS==0){
            if(this.getFrame()==0){
                this.nextFrame();
            }else{
                this.nextFrame();
            }
            retrasoAnimacion = 0;
        }
        retrasoAnimacion++;
        
        this.paint(g);
    }
}
