package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Juego;

/**
 *
 * @author zama
 */
public class SpriteAmbiente extends Sprite{
    private static int[] secuenciaFondo = {0};
    private static int[] secuenciaMedia = {0};
    private static int[] secuenciaMediaFrente = {0};
    private static int[] secuenciaFrente = {0};
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;
    private int region;
    private int distancia;
    private int velocidad;
    private int avance;

    /**
     * Enum del ARBOL_1
     */
    public static final int ARBOL_1 = 0;

    /**
     * constructor que inicializa variables
     * @param archivoEnemigo direccion de la imagen del enemigo
     * @param centesimo
     * @param tipoEnemigo
     * @throws IOException Si no se encuentra el archivo
     */
    public SpriteAmbiente(Image imagenAmbiente, int centesimo, int region) throws IOException{
        super(imagenAmbiente,25,40);

       this.setFrameSequence(this.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);
        this.region = region;

        avance = 0;
        velocidad = 5;

        this.centesimo = centesimo;
        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
        this.distancia = posicion.getX();
        this.setPosition(posicion.getX(), posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight()/2);
    }

    /**
     * Dibuja el ambiente
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        if( region == 2){
            this.paint(g);
        }
    }

    /**
     * Cambia la secuencia del sprite dependiendo de su posicion
     */
    public void mover() {
//        if(this.getY() > Global.ALTO_PANTALLA/100){
//            this.setPosition(this.getX()+Juego.ALTO_LINEA*(region==0 ? -1: 1), this.getY()+Juego.ALTO_LINEA);
//        }else{
        if( region == 2 ){
            avance++;
            if(avance == velocidad){
                avance = 0;
                Juego.getPosicionador().getPorcion(posicion, alturaActual, 0, region );
                this.setPosition(posicion.getX() + distancia/2, posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight()/2);
                alturaActual+=1;
            }
        }
//        }        
    }
}
