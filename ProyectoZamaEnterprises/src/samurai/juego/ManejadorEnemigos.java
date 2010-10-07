/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;


public class ManejadorEnemigos {

    private Vector enemigosEnPantalla;

    public ManejadorEnemigos(){
        enemigosEnPantalla= new Vector();
    }
    public void agregarEnemigo(SpriteEnemigo enemigo){
        enemigosEnPantalla.addElement(enemigo);
    }
    public void kill(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        System.gc();
    }
    public void dibujar(Graphics g){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).dibujar(g);
        }
    }
    public void actualizar(){
        for(int i=0; i<enemigosEnPantalla.size();i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).mover();
        }
    }

    public boolean isEmpty(){
        return this.enemigosEnPantalla.isEmpty();
    }
}
