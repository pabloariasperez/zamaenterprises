/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;
import samurai.animacion.SpriteEspada;


/**
 *Clase que se encarga de manejar enemigos
 * @author Pablo, Erik y Daniel
 * @version 1.0
 */
public class ManejadorEnemigos {

    private Vector enemigosEnPantalla;
    private ManejadorSekai sekai;
    private SpriteEspada espada;
    private int enemigosMuertos;
    /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorEnemigos(){
        enemigosEnPantalla= new Vector();
        this.enemigosMuertos=0;
    }
    /**
     * Metodo que agrega los enemigos al Vector.
     * @param enemigo recibe un SpriteEnemigo para agregarlo al Vector.
     */
    public void agregarEnemigo(int tipoEnemigo){
        try {
            //Obligamos
            int posicionAleatoria = (int) System.currentTimeMillis() % 3;
            switch (tipoEnemigo) {
                case SpriteEnemigo.MURCIELAGO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, tipoEnemigo));
                    break;
                case SpriteEnemigo.RATA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, tipoEnemigo));
                    break;
                case SpriteEnemigo.FANTASMA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, tipoEnemigo));
                    break;
                case SpriteEnemigo.TOPO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60,tipoEnemigo));
                    break;
                case SpriteEnemigo.CESAR:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, tipoEnemigo));
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Metodo que remueve del Vector el enemigo que se manda como parametro y lo declara como null.
     * @param enemigo Recibe el SpriteEnemigo que se desea borrar del Vector.
     */
    public void kill(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        enemigo=null;
        this.enemigosMuertos++;
        if(this.enemigosMuertos==5){
            System.gc();
            this.enemigosMuertos=0;
        }
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
    public Vector getVectorEnemigo(){
        return this.enemigosEnPantalla;
    }
        private int posicionEnemigo(int posicionAleatoria) {
        //Dependerá de la función del escenario.
        return 0;
    }

}
