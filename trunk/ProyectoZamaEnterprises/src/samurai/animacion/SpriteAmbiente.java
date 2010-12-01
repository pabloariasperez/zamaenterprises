package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Global;
import samurai.juego.Juego;
import samurai.presentacion.Timor;

/**
 *
 * @author zama
 */
public class SpriteAmbiente extends Sprite {

    private static int[] secuenciaFondo = {0};
    private static int[] secuenciaMedia = {1};
    private static int[] secuenciaMediaFrente = {2};
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;
    private int region;
    private int distancia;
    /**
     * Enum del ARBOL_1
     */
    public static final int ARBOL_1 = 0;
    public static final int PIEDRA_1 = 1;
    private final int ANCHO_PIEDRA = 20;

    /**
     * constructor que inicializa variables
     * @param imagenAmbiente imagen del sprite
     * @param centesimo lugar donde se dibuja
     * @param region area donde se dibuja
     * @throws IOException Si no se encuentra el archivo
     */
    public SpriteAmbiente(Image imagenAmbiente, int centesimo, int region) throws IOException {
        super(imagenAmbiente, imagenAmbiente.getWidth() / 4, imagenAmbiente.getHeight());

        this.setFrameSequence(secuenciaFondo);

        alturaActual = 1;
        posicion = new Posicion(0, 0);
        this.region = region;

        this.centesimo = 0;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, 0, region);
        if (region == 1) {
            distancia = (Juego.getPosicionador().getAnchoFinal() * centesimo )/ 100;
        } else {
            distancia = (Global.ANCHO_PANTALLA * centesimo) / 100 + ANCHO_PIEDRA;
        }
        this.setPosition(distancia, posicion.getY());
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
        if (region == 2) {
            Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
            this.setPosition(posicion.getX() + distancia - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
        } else if (region == 0) {
            Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region + 1);
            this.setPosition(posicion.getX() - distancia - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
        } else if (region == 1) {
            Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, region);
            this.setPosition(posicion.getX() + distancia - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
        }


        

        alturaActual += 2;
    }
}
