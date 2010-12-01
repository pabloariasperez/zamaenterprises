package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.escenarios.Posicion;
import samurai.juego.Juego;

/**
 * Clase encargada de animar los items
 * @author Pablo, Erik, Daniel
 * @version 1.0 Noviembre 2010
 */
public class SpriteItem extends Sprite implements Animable{
    private int[] secuenciaFondo= {0};
    private int[] secuenciaMedia= {0};
    private int[] secuenciaFrente= {0};
    private int tipoItem;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;

    /**
     *
     */
    public static final int ITEM_CORAZON=0;


    /**
     * Contructor: inicializa variables
     * @param imagenItem
     * @param centesimo
     * @param tipoItem
     * @throws IOException
     */
    public SpriteItem(Image imagenItem, int centesimo, int tipoItem) throws IOException{
        super(imagenItem,13,12);
//        Image imagen = imagenItem;
//        Image imagen = Global.resizeImage(imagenItem);
//        super.setImage(imagen, imagen.getWidth()/1, imagen.getHeight()/1);
        this.tipoItem = tipoItem;

        this.setFrameSequence(this.secuenciaFondo);

        alturaActual = 0;
        posicion = new Posicion(0, 0);

        this.centesimo = centesimo;

        Juego.getPosicionador().getPorcion(posicion, alturaActual, centesimo, 1);
        this.setPosition(posicion.getX(), posicion.getY());
    }

    /**
     * Dibuja al items
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


        if(this.getY() < 80){
            this.setFrameSequence(secuenciaFondo);
        }
        else if(this.getY() < 120)
        {
            this.setFrameSequence(secuenciaMedia);
        }else if(this.getY()<160){
            this.setFrameSequence(secuenciaFrente);
        }
        this.nextFrame();
        alturaActual+=3;
    }
    /**
     * regresa el centesimo donde se dibuja
     * @return centesimo
     */
    public int getCentesimo(){
        return this.centesimo;
    }
    /**
     * regresa la altura actual
     * @return la altura actual
     */
    public int getAltura(){
        return this.alturaActual;
    }

    /**
     * regresa el tipo de Item
     * @return tipo del item
     */
    public int getTipoItem() {
        return tipoItem;
    }
}
