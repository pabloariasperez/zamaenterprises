/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;
import java.io.IOException;
import java.util.Random;
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
    private int enemigosMuertos;
    private Random rndm;
    /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorEnemigos(){
        enemigosEnPantalla= new Vector();
        this.enemigosMuertos=0;
        rndm = new Random();
    }
    /**
     * Metodo que agrega los enemigos al Vector.
     * @param tipoEnemigo enemigo que se desea agregar
     */
    public void agregarEnemigo(int tipoEnemigo){
        try {
            switch (tipoEnemigo) {
                case SpriteEnemigo.MURCIELAGO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", rndm.nextInt(60) + 20, tipoEnemigo));
                    break;
                case SpriteEnemigo.TOPO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteTopo.png", rndm.nextInt(60) + 20,tipoEnemigo));
                    break;
                case SpriteEnemigo.RATA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteRata.png", rndm.nextInt(60) + 20, tipoEnemigo));
                    break;
                case SpriteEnemigo.FANTASMA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", rndm.nextInt(60) + 20, tipoEnemigo));
                    break;
                case SpriteEnemigo.CESAR:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", rndm.nextInt(60) + 20, tipoEnemigo));
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
     * hace desaparecer al enemigo
     * @param enemigo Sprite que se desea desaparecer
     */
    public void desaparecer(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        enemigo=null;
    }
    /**
     * Metodo que dibuja todos los elementos del vector en el el parametro que se le da.
     * @param g Graficos donde se dibujan los elementos del vector.
     */
    public void dibujar(Graphics g){
        for(int i=enemigosEnPantalla.size()-1; i>=0;i--){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).dibujar(g);
        }
    }
    /**
     * Metodo que actualiza los Sprites de los enemigos.
     */
    public void actualizar(){
        int size = enemigosEnPantalla.size();
        for(int i=0; i<size; i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).mover();
        }
    }
    /**
     * regresa el vector que contiene a los enemigos
     * @return vector que contiene a los enemigos
     */
    public Vector getVectorEnemigo(){
        return this.enemigosEnPantalla;
    }
}
