/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
public class SpriteItem extends Sprite implements Animable{
    private int[] secuenciaFondo, secuenciaMedia, secuenciaMediaFrente;
    private int tipoItem;
    private Posicion posicion;
    private int alturaActual;
    private int centesimo;

    public static final int ITEM_CORAZON=0;


    public SpriteItem(Image imagenItem, int centesimo, int tipoItem) throws IOException{
        super(imagenItem,13,12);
        Image imagen = Global.resizeImage(imagenItem);
        super.setImage(imagen, imagen.getWidth()/1, imagen.getHeight()/1);
        this.tipoItem = tipoItem;
        this.secuenciaFondo=new int[]{0};
        this.secuenciaMedia=new int[]{0};
        this.secuenciaMediaFrente=new int[]{0};

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
            this.setFrameSequence(secuenciaMediaFrente);
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
     * regresa el tipo de Item
     * @return tipo del item
     */
    public int getTipoItem() {
        return tipoItem;
    }
}
