package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicionador;

/**
 * Crea el sprite del enemigo, cambia sus secuencia de frames y lo mueve
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteEnemigo  extends Sprite implements Animable {
    private int posicionX,posicionY;
    private int[] secuenciaFondo, secuenciaMedia, secuenciaMediaFrente, secuenciaFrente;
    private int parametroCamino, margenIzquierdo;
    private int tipoEnemigo;

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
    public SpriteEnemigo(String archivoEnemigo, int posicionX, int posicionY, int tipoEnemigo) throws IOException{
        super(Image.createImage(archivoEnemigo),240/4,240/4);
        
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.tipoEnemigo = tipoEnemigo;
        this.secuenciaFondo=new int[]{0,1,2,3};
        this.secuenciaMedia=new int[]{4,5,6,7};
        this.secuenciaMediaFrente=new int[]{8,9,10,11};
        this.secuenciaFrente = new int[]{12,13,14,15};
        this.setFrameSequence(this.secuenciaFondo);

       this.setPosition(this.posicionX, this.posicionY);
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

        this.posicionY++;
        this.posicionX = margenIzquierdo + Posicionador.recta(posicionY, parametroCamino);
        if(this.posicionY==90){
            this.setFrameSequence(secuenciaMedia);
        }else if(this.posicionY==120){
            this.setFrameSequence(secuenciaMediaFrente);
        }else if(this.posicionY==150){
            this.setFrameSequence(secuenciaFrente);
        }
        this.setPosition(posicionX, posicionY);
        this.nextFrame();
    }

    /**
     * Cambia la poscision del enemigo y la secuencia
     * @param posicionX nueva x
     * @param posicionY nueva y
     */
    public void cambiarPosicion(int posicionX, int posicionY){
        this.posicionX=posicionX;
        this.posicionY=posicionY;
        this.setPosition(posicionX, posicionY);
        this.setFrameSequence(secuenciaFondo);
    }
    public int getTipoEnemigo(){
        return this.tipoEnemigo;
    }
}