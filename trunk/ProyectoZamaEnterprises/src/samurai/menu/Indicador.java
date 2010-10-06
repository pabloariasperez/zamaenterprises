
package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;

public class Indicador extends Sprite {

    private int posicionX;
    private int posicionY;
    private int crecimientoLinea;

    //Recibe como parametros el nombre del archivo a utilizar como indicador, la posicion x y posicion y a utilizar como posiciones iniciales
    public Indicador(String archivoIndicador, int posicionX, int posicionY) throws IOException{
        //El 135 representa el ancho de la imagen, mientras que el 40 representa lo alto de esta.
        super(Image.createImage(archivoIndicador),135,40);
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.setPosition(posicionX,posicionY);
        this.crecimientoLinea=0;
    }
    //Dibuja una linea que crece desde x=0 hasta x=240, despues dibuja un rectangulo haciendo el efecto de que la linea se ensancha ,
    //y al final dibuja al sprite del indicador.
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
    //Cambia la losicion x y y del indicador
    public void cambiarPosicion(int nuevaPosicionX,int nuevaPosicionY){
        this.posicionX=nuevaPosicionX;
        this.posicionY=nuevaPosicionY;
        this.setPosition(posicionX, posicionY);
        this.crecimientoLinea=0;
    }
}
