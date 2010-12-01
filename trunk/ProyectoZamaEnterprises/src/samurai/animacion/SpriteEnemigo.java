package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Juego;

/**
 * Crea el sprite del enemigo, cambia sus secuencia de frames y lo mueve
 * @author Pablo, Erik, Daniel
 * @version 1.0 Septiembre 2010
 */
public class SpriteEnemigo extends Sprite implements Animable {

    private static int[] secuenciaFondo = {0, 1, 2, 3};
    private static int[] secuenciaMedia = {4, 5, 6, 7};
    private static int[] secuenciaMediaFrente = {8, 9, 10, 11};
    private int tipoEnemigo;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;
    private Sprite spriteSombra;

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

    
    private final int REGION = 1;
    private int rapidez;

    /**
     * constructor que inicializa variables y crea la imagen del enemigo
     * @param archivoEnemigo direccion de la imagen del enemigo
     * @param centesimo lugar donde se coloca el enemigo
     * @param tipoEnemigo tipo de enemigo
     * @throws IOException Si no se encuentra el archivo
     */
    public SpriteEnemigo(Image enemigo, int centesimo, int tipoEnemigo) throws IOException {
        super(enemigo, enemigo.getWidth()/4, enemigo.getHeight()/4);
//        Image imagen = Global.resizeSprite(Image.createImage(archivoEnemigo), 4, 4);

        this.tipoEnemigo = tipoEnemigo;


        this.setFrameSequence(SpriteEnemigo.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);
        this.rapidez = (int) (System.currentTimeMillis() % 5 + 1);

        this.centesimo = centesimo;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, REGION);
        this.setPosition(posicion.getX(), posicion.getY());
    }

    /**
     * constructor que inicializa variables y crea la imagen del enemigo
     * @param archivoEnemigo direccion de la imagen del enemigo
     * @param centesimo lugar donde se coloca el enemigo
     * @param tipoEnemigo tipo de enemigo
     * @param alturaActual altura donde se coloca
     * @throws IOException si no se encuentra el archivo
     */
    public SpriteEnemigo(Image enemigo, int centesimo, int tipoEnemigo, int alturaActual ) throws IOException {
        this(enemigo, centesimo, tipoEnemigo);
        this.alturaActual = alturaActual;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, REGION);
        this.setPosition(posicion.getX() - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
    }

    public void agregarSombra(Image sombra){
        this.spriteSombra = new Sprite(sombra,sombra.getWidth()/4,sombra.getHeight()/4);
    }

    /**
     * Dibuja al enemigo
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
       if(this.spriteSombra!=null){
            this.spriteSombra.paint(g);
        }
        this.paint(g);
        
    }

    /**
     * Cambia la secuencia del sprite dependiendo de su posicion
     */
    public void mover() {

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, REGION);
        this.setPosition(posicion.getX() - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
        if(this.spriteSombra!=null){
            this.spriteSombra.setPosition(posicion.getX() - this.getWidth() / 2, posicion.getY() * Juego.ALTO_LINEA + Juego.altoFondo - this.getHeight() / 2);
            if(this.tipoEnemigo==SpriteEnemigo.FANTASMA){
                this.spriteSombra.setPosition(this.getX(), this.getY()+this.getHeight()/4);
            }
             if (this.spriteSombra.getY() < 80) {
                this.spriteSombra.setFrameSequence(SpriteEnemigo.secuenciaFondo);
            } else if (this.spriteSombra.getY() < 120) {
                this.spriteSombra.setFrameSequence(SpriteEnemigo.secuenciaMedia);
            } else if (this.spriteSombra.getY() < 160) {
                this.spriteSombra.setFrameSequence(SpriteEnemigo.secuenciaMediaFrente);
            }
            this.spriteSombra.nextFrame();
        }



            if (this.getY() < 80) {
                this.setFrameSequence(SpriteEnemigo.secuenciaFondo);
            } else if (this.getY() < 120) {
                this.setFrameSequence(SpriteEnemigo.secuenciaMedia);
            } else if (this.getY() < 160) {
                this.setFrameSequence(SpriteEnemigo.secuenciaMediaFrente);
            }


        this.nextFrame();

        alturaActual += rapidez;
    }

    /**
     * regresa el centesimo donde se ecuentra
     * @return el centesimo donde se ecuentra
     */
    public int getCentesimo() {
        return this.centesimo;
    }

    /**
     * regresa la altura actual
     * @return la altura actual
     */
    public int getAltura() {
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
