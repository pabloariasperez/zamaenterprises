/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.SpriteItem;

/**
 *
 * @author zama
 */
public class ManejadorItems {

    private Vector itemEnPantalla;
    private Random rndm;
    private SpriteItem item;
    private Image item_corazon;

    /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorItems() {
        itemEnPantalla = new Vector();
        rndm = new Random();
        item = null;
        try {
            item_corazon = Image.createImage("/samurai/imagenes/items/corazon.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que agrega los elementos del ambiente al Vector.
     * @param tipoAmbiente  elemento que se desea agregar
     */
    public void agregarItem(Random rnd) {
        int tipoItem = rnd.nextInt(1);
        try {
            switch (tipoItem) {
                case SpriteItem.ITEM_CORAZON:
                    itemEnPantalla.addElement(new SpriteItem(item_corazon, rndm.nextInt(80)+10, tipoItem));
                    break;
                default:
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * hace desaparecer al elemento
     * @param elemento Sprite que se desea desaparecer
     */
    public void desaparecer(SpriteItem item) {
        itemEnPantalla.removeElement(item);
    }

    /**
     * Metodo que dibuja todos los elementos del vector en el el parametro que se le da.
     * @param g Graficos donde se dibujan los elementos del vector.
     */
    public void dibujar(Graphics g) {
        for (int i = itemEnPantalla.size() - 1; i >=0; i--) {
            ((SpriteItem) itemEnPantalla.elementAt(i)).dibujar(g);
        }
    }

    /**
     * Metodo que actualiza los Sprites de los enemigos.
     */
    public void actualizar() {

        for(int i=0; i<itemEnPantalla.size(); i++){
            item=((SpriteItem)itemEnPantalla.elementAt(i));
            item.mover();
            if(item.getY()>=Global.ALTO_PANTALLA){
                this.desaparecer(item);
            }
        }

    }

    /**
     * regresa el vector que contiene a los enemigos
     * @return vector que contiene a los enemigos
     */
    public Vector getVectorItem() {
        return this.itemEnPantalla;
    }
}
