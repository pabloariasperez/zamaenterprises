
package samurai.menu;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Global;

/**
 * Crea el sprite del indicador, lo mueve, y lo dibuja
 * @author Pablo, Erik, Daniel
 * @version 1.1 Octubre 2010
 */
public class Indicador extends Sprite {
    private Posicion posicion;
    private int crecimientoAncho;
    private int crecimientoAlto;
    private int altoBoton;
    private int INCREMENTO_ANCHO_LINEA;

    //Dimensiones del SPRITE indicador
    private static final int ANCHO_SPRITE = 135;
    private static final int ALTO_SPRITE = 40;
    private static final int ALTO_FINAL = 6;
    private static final int TIEMPO_LLENADO_HORIZONTAL = 150;    //En milisegundos



    /**
     * Crea el sprite
     * Inicializa la posicion, el crecimiento en ancho y alto y el alto del boton
     * coloca el indicador en su posicion inicial
     * @param archivoIndicador direccion de la imagen del indicador
     * @param posicionX posicion inicial en x
     * @param posicionY posicion inical en y
     * @throws IOException si no se encuentra el archivo de la imagen
     */
    public Indicador(String archivoIndicador, int posicionX, int posicionY ) throws IOException{
        //El 135 representa el ancho de la imagen, mientras que el 40 representa lo alto de esta.
        super(Image.createImage(archivoIndicador),ANCHO_SPRITE, ALTO_SPRITE);
        this.posicion = new Posicion(posicionX, posicionY);
        this.setPosition(this.posicion.getX(), this.posicion.getY());
        this.crecimientoAncho=0;
        this.crecimientoAlto=0;
        this.INCREMENTO_ANCHO_LINEA = Indicador.TIEMPO_LLENADO_HORIZONTAL * Global.FPS;
        this.altoBoton=0;
    }

    /**
     * Dibuja una linea que crece desde x=0 hasta x=240,
     * despues dibuja un rectangulo haciendo el efecto de que la linea se ensancha,
     * y al final dibuja al sprite del indicador.
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g){
        g.setColor(0x99FF0000);
        //Preguntamos si el crecimiento de nuestro incremento en el ancho es aún menor que el ANCHO DE PANTALLA
        if(this.crecimientoAncho + this.crecimientoAlto < Global.ANCHO_PANTALLA + 2*ALTO_FINAL){
            g.fillRect(0, posicion.getY() + altoBoton/2 - crecimientoAlto/2, posicion.getX()+this.crecimientoAncho, crecimientoAlto );
            
            if( crecimientoAncho < Global.ANCHO_PANTALLA  ){
                this.crecimientoAncho += INCREMENTO_ANCHO_LINEA;
            }else{
                this.crecimientoAlto++;
            }
        }else{
            this.paint(g);
        }
    }
    
    /**
     * Cambia la posición x y y del indicador
     * @param nuevaPosicionX nueva x
     * @param nuevaPosicionY nueva y
     */
    public void cambiarPosicion(int nuevaPosicionX,int nuevaPosicionY){
        this.posicion.setX(nuevaPosicionX);
        this.posicion.setY(nuevaPosicionY);
        this.setPosition( nuevaPosicionX, nuevaPosicionY);
        this.crecimientoAncho=0;
        this.crecimientoAlto=1;

    }

    /**
     * Preguntamos si ya está definido el alto de botón para el indicador.
     * Nos ayuda a centrar el indicador respecto a l botón.
     * @return un boolean indicando si ya esta definido el alto del botón
     */
    public boolean isDefiniedAltoBoton(){
        return this.altoBoton != 0;
    }

    /**
     * Obtemenos y guardamos el ALTO de los botones.
     * @param altoBoton alto del boton
     */
    public void setBotonAlto( int altoBoton ){
        this.altoBoton = altoBoton;
    }
}