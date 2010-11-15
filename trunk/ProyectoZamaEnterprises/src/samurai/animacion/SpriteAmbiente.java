package samurai.animacion;

import java.io.IOException;
import java.util.Random;
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
    private int[] secuenciaFondo, secuenciaMedia, secuenciaMediaFrente, secuenciaFrente;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;
    private int region;

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
    public SpriteAmbiente(String archivoAmbiente, int centesimo) throws IOException{
        super(Image.createImage(archivoAmbiente),25,40);

        this.secuenciaFondo=new int[]{0};
        this.secuenciaMedia=new int[]{0};
        this.secuenciaMediaFrente=new int[]{0};
        this.secuenciaFrente = new int[]{0};

        this.setFrameSequence(this.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);

        this.centesimo = centesimo;
        Random r=new Random();
        region=r.nextInt(2)== 0 ? 0 : 2;
        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
        this.setPosition(posicion.getX(), posicion.getY());
    }

    /**
     * Dibuja el ambiente
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        this.paint(g);
    }

    /**
     * Cambia la secuencia del sprite dependiendo de su posicion
     */
    public void mover() {

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
        this.setPosition(posicion.getX() - this.getWidth()/2, posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight()/2);


        if(this.getY() < 80){
            this.setFrameSequence(secuenciaFondo);
        }
        else if(this.getY() < 120)
        {
            this.setFrameSequence(secuenciaMedia);
        }else if(this.getY()<160){
            this.setFrameSequence(secuenciaMediaFrente);
        }
        alturaActual+=3;
    }
}
