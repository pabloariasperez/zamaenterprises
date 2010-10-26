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
 * @version 1.0
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
     * Metodo que agrega los enemigos al Vector.
     * @param enemigo recibe un SpriteEnemigo para agregarlo al Vector.
     */
    public void agregarEnemigo(SpriteEnemigo enemigo){
        enemigosEnPantalla.addElement(enemigo);
    }
    /**
     * Metodo que remueve del Vector el enemigo que se manda como parametro y lo declara como null.
     * @param enemigo Recibe el SpriteEnemigo que se desea borrar del Vector.
     */
    public void kill(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        enemigo=null;
        System.gc();
    }
    /**
     * Metodo que dibuja todos los elementos del vector en el el parametro que se le da.
     * @param g Graficos donde se dibujan los elementos del vector.
     */
    public void dibujar(Graphics g){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).dibujar(g);
        }
    }
    /**
     * Metodo que actualiza los Sprites de los enemigos.
     */
    public void actualizar(){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).mover();
        }
    }

    /**
     * Metodo que regresa un booleano que dice si el Vector enemigos esta vacio o no.
     * @return Booleano que dice si el Vector esta vacio o no.
     */
    public boolean isEmpty(){
        return this.enemigosEnPantalla.isEmpty();
    }
    /**
     * Metodo que regresa el tamaño del Vector enemigos.
     * @return Regresa un entero igual al tamaño del Vector.
     */
    public int getSize(){
        return enemigosEnPantalla.size();
    }

}
