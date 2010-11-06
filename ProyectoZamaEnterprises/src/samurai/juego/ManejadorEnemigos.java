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
     * @param enemigo recibe un SpriteEnemigo para agregarlo al Vector.
     */
    public void agregarEnemigo(int tipoEnemigo){
        try {
            //Obligamos

            switch (tipoEnemigo) {
                case SpriteEnemigo.MURCIELAGO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", centesimo(rndm.nextInt(3)), tipoEnemigo));
                    break;
                case SpriteEnemigo.RATA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", centesimo(rndm.nextInt(3)), tipoEnemigo));
                    break;
                case SpriteEnemigo.FANTASMA:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", centesimo(rndm.nextInt(3)), tipoEnemigo));
                    break;
                case SpriteEnemigo.TOPO:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteTopo.png", centesimo(rndm.nextInt(3)),tipoEnemigo));
                    break;
                case SpriteEnemigo.CESAR:
                    enemigosEnPantalla.addElement(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", centesimo(rndm.nextInt(3)), tipoEnemigo));
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private int centesimo(int aleatorio){
        switch(aleatorio){
            case 0:
                return 0;
            case 1:
                return 50;
            case 2:
                return 85;
            default:
                return 0;
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
    public void desaparecer(SpriteEnemigo enemigo){
        enemigosEnPantalla.removeElement(enemigo);
        enemigo=null;
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
        int size = enemigosEnPantalla.size();
        for(int i=0; i<size; i++){
            ((SpriteEnemigo)enemigosEnPantalla.elementAt(i)).mover();
        }
    }
    public Vector getVectorEnemigo(){
        return this.enemigosEnPantalla;
    }
}
