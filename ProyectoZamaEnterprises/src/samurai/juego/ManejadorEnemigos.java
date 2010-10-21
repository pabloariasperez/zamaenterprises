/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;


/**
 *Clase que se encarga de manejar enemigos
 * @author Pablo, Erik y Daniel
 */
public class ManejadorEnemigos {

    private Vector enemigosEnPantalla;

    /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorEnemigos(){
        enemigosEnPantalla= new Vector();
    }
    /**
     * Clase que agrega los enemigos al Vector.
     * @param enemigo recibe 
     */
    public void agregarEnemigo(SpriteEnemigo enemigo){
        enemigosEnPantalla.addElement(enemigo);
    }
    /**
     *
     * @param enemigo
     */
    public void kill(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        enemigo=null;
        System.gc();
    }
    /**
     *
     * @param g
     */
    public void dibujar(Graphics g){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).dibujar(g);
        }
    }
    /**
     *
     */
    public void actualizar(){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).mover();
        }
    }

    /**
     *
     * @return
     */
    public boolean isEmpty(){
        return this.enemigosEnPantalla.isEmpty();
    }
    /**
     *
     * @return
     */
    public int getSize(){
        return enemigosEnPantalla.size();
    }
}
