package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Global;
import samurai.juego.Juego;

/**
 * Crea el sprite del enemigo, cambia sus secuencia de frames y lo mueve
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteEnemigo  extends Sprite implements Animable {
    private int[] secuenciaFondo, secuenciaMedia, secuenciaMediaFrente, secuenciaFrente;
    private int parametroCamino, margenIzquierdo;
    private int tipoEnemigo;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;

    public static final int MURCIELAGO = 0;
    public static final int RATA = 1;
    public static final int TOPO = 2;
    public static final int FANTASMA = 3;

    public static final int CESAR = 10;

    /**
     * constructor que inicializa variables
     * @param archivoEnemigo direccion de la imagen del enemigo
     * @param posicionX posicion inicial en x
     * @param posicionY posicion inicial en y
     * @throws IOException Si no se encuentra el archivo
     */
    public SpriteEnemigo(String archivoEnemigo, int centesimo, int tipoEnemigo) throws IOException{
        super(Image.createImage(archivoEnemigo),160/4,160/4);
        
        this.tipoEnemigo = tipoEnemigo;
        this.secuenciaFondo=new int[]{0,1,2,3};
        this.secuenciaMedia=new int[]{4,5,6,7};
        this.secuenciaMediaFrente=new int[]{8,9,10,11};
        this.secuenciaFrente = new int[]{12,13,14,15};
        this.setFrameSequence(this.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);

        this.centesimo = centesimo;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, 1);
        this.setPosition(posicion.getX(), posicion.getY());
    }

    /**
     * Dibuja al enemigo
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        this.paint(g);
    }

    /**
     * Cambia la secuencia del sprite dependiendo de su y
     */
    public void mover() {

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, 1);
        this.setPosition(posicion.getX(), posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo);

        /*
        if(this.getY() < 80){
            this.setFrameSequence(secuenciaFondo);
        }
        else if(this.getY() < 120)
        {
            this.setFrameSequence(secuenciaMedia);
        }else if(this.getY()<160){
            this.setFrameSequence(secuenciaMediaFrente);
        }
         * 
         */
        this.nextFrame();
        alturaActual+=5;
    }

    public int getTipoEnemigo() {
        return tipoEnemigo;
    }


}