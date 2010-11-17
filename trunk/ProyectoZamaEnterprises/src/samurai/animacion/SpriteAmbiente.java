package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Global;
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
    public SpriteAmbiente(Image imagenAmbiente, int centesimo, int region) throws IOException{
        super(imagenAmbiente,25,40);

        this.secuenciaFondo=new int[]{0};
        this.secuenciaMedia=new int[]{0};
        this.secuenciaMediaFrente=new int[]{0};
        this.secuenciaFrente = new int[]{0};

        this.setFrameSequence(this.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);
        this.region = region;

        this.centesimo = centesimo;
//        Random r=new Random();
//        region=r.nextInt(2)== 0 ? 0 : 2;
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
        if(this.getY() > Global.ALTO_PANTALLA*3/4){
            this.setPosition(this.getX()+Juego.ALTO_LINEA*(region==0 ? -1: 1), this.getY()+Juego.ALTO_LINEA);
        }else{
            Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
            this.setPosition(posicion.getX() - this.getWidth()/2, posicion.getY()*Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight()/2);
        }


        if(this.getY() < 80){
            this.setFrameSequence(secuenciaFondo);
        }
        else if(this.getY() < 120)
        {
            this.setFrameSequence(secuenciaMedia);
        }else if(this.getY()<160){
            this.setFrameSequence(secuenciaMediaFrente);
        }
        alturaActual+=1;
    }
}
