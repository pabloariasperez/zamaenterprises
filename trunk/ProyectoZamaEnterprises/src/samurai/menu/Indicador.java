
package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;

public class Indicador extends Sprite {
    private Posicion posicion;
    private int crecimientoAncho;
    private int crecimientoAlto;
    private int ANCHO_PANTALLA;
    private int altoBoton;

    //Dimensiones del SPRITE indicador
    private static final int ANCHO_SPRITE = 135;
    private static final int ALTO_SPRITE = 40;
    private static final int ALTO_FINAL = 6;
    private static final int INCREMENTO_ANCHO_LINEA = 16;



    //Recibe como parametros el nombre del archivo a utilizar como indicador, la posicion x y posicion y a utilizar como posiciones iniciales
    public Indicador(String archivoIndicador, int posicionX, int posicionY, int anchoPantalla) throws IOException{
        //El 135 representa el ancho de la imagen, mientras que el 40 representa lo alto de esta.
        super(Image.createImage(archivoIndicador),ANCHO_SPRITE, ALTO_SPRITE);
        this.posicion = new Posicion(posicionX, posicionY);
        this.setPosition(this.posicion.getX(), this.posicion.getY());
        this.crecimientoAncho=0;
        this.crecimientoAlto=0;
        this.ANCHO_PANTALLA = anchoPantalla;
        this.altoBoton=0;
    }
    //Dibuja una linea que crece desde x=0 hasta x=240, despues dibuja un rectangulo haciendo el efecto de que la linea se ensancha ,
    //y al final dibuja al sprite del indicador.
    public void dibujar(Graphics g){
        g.setColor(0x99FF0000);
        //Preguntamos si el crecimiento de nuestro incremento en el ancho es aún menor que el ANCHO DE PANTALLA
        if(this.crecimientoAncho + this.crecimientoAlto < ANCHO_PANTALLA + 2*ALTO_FINAL){
            g.fillRect(0, posicion.getY() + altoBoton/2 - crecimientoAlto/2, posicion.getX()+this.crecimientoAncho, crecimientoAlto );
            
            if( crecimientoAncho < ANCHO_PANTALLA  ){
                this.crecimientoAncho += INCREMENTO_ANCHO_LINEA;
            }else{
                this.crecimientoAlto++;
            }
        }else{
            this.paint(g);
        }
    }
    
    //Cambia la posición x y y del indicador
    public void cambiarPosicion(int nuevaPosicionX,int nuevaPosicionY){
        this.posicion.setX(nuevaPosicionX);
        this.posicion.setY(nuevaPosicionY);
        this.setPosition( nuevaPosicionX, nuevaPosicionY);
        this.crecimientoAncho=0;
        this.crecimientoAlto=1;

    }

    //Preguntamos si ya está definido el alto de botón para el indicador. Nos ayuda a centrar el indicador respecto a l botón.
    public boolean isDefiniedAltoBoton(){
        return this.altoBoton != 0;
    }

    //Obtemenos y guardamos el ALTO de los botones.
    public void setBotonAlto( int altoBoton ){
        this.altoBoton = altoBoton;
    }
}