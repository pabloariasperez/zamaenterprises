package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Juego;
import samurai.presentacion.Timor;

/**
 * Crea el sprite del enemigo, cambia sus secuencia de frames y lo mueve
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteEnemigo  extends Sprite implements Animable {
    private static int[] secuenciaFondo = {0,1,2,3};
    private static int[] secuenciaMedia = {4,5,6,7};
    private static int[] secuenciaMediaFrente = {8,9,10,11};
    private static int[] secuenciaFrente = {12,13,14,15};

    private int tipoEnemigo;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;
    private Timor timer;


    /**
     * Enum del Murcielago
     */
    public static final int MURCIELAGO = 0;

    /**
     * Enum del Rata
     */
    public static final int RATA = 1;

    /**
     * Enum del Topo
     */
    public static final int TOPO = 2;

    /**
     * Enum del Fantasma
     */
    public static final int FANTASMA = 3;

    /**
     * Enum del Cesar
     */
    public static final int CESAR = 4;

    /**
     * constructor que inicializa variables
     * @param archivoEnemigo direccion de la imagen del enemigo
     * @param centesimo
     * @param tipoEnemigo
     * @throws IOException Si no se encuentra el archivo
     */
    public SpriteEnemigo(String archivoEnemigo, int centesimo, int tipoEnemigo) throws IOException{
        super(Image.createImage(archivoEnemigo),160/4,160/4);
        this.timer = new Timor(1);
        this.tipoEnemigo = tipoEnemigo;
                
        
        this.setFrameSequence(SpriteEnemigo.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);

        this.centesimo = centesimo;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, 1);
        this.setPosition(posicion.getX(), posicion.getY());
    }
      public SpriteEnemigo(String archivoEnemigo, int centesimo, int tipoEnemigo, int altura, int x, int y) throws IOException{
        super(Image.createImage(archivoEnemigo),160/4,160/4);
                this.timer = new Timor(1);
                this.tipoEnemigo = tipoEnemigo;


        this.setFrameSequence(SpriteEnemigo.secuenciaFondo);

        this.centesimo = centesimo;
        this.alturaActual = altura;
        posicion = new Posicion(x, y);
       

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
     * Cambia la secuencia del sprite dependiendo de su posicion
     */
    public void mover() {

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, 1);
        this.setPosition(posicion.getX() - this.getWidth()/2, posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight()/2);
        timer.tik();
        if(timer.activarIteracion()){
            if(this.getY() < 80){
                this.setFrameSequence(SpriteEnemigo.secuenciaFondo);
            }
            else if(this.getY() < 120)
            {
                this.setFrameSequence(SpriteEnemigo.secuenciaMedia);
            }else if(this.getY()<160){
                this.setFrameSequence(SpriteEnemigo.secuenciaMediaFrente);
            }
        }
        this.nextFrame();
        alturaActual+=3;
    }
    public int getCentesimo(){
        return this.centesimo;
    }
    public int getAltura(){
        return this.alturaActual;
    }

    /**
     * regresa el tipoCanvas de enemigo
     * @return tipoCanvas del enemigo
     */
    public int getTipoEnemigo() {
        return tipoEnemigo;
    }
}
