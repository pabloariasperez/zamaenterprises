/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteAmbiente;

/**
 *
 * @author zama
 */
public class ManejadorAmbiente {
    private Vector ambienteEnPantalla;
    private Random rndm;
    private SpriteAmbiente elemento;
   /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorAmbiente(){
        ambienteEnPantalla= new Vector();
        rndm = new Random();
        elemento=null;
    }
    /**
     * Metodo que agrega los elementos del ambiente al Vector.
     * @param tipoAmbiente  elemento que se desea agregar
     */
    private void agregarElemento(int tipoAmbiente){
        try {
            switch (tipoAmbiente) {
                case SpriteAmbiente.ARBOL_1:
                    ambienteEnPantalla.addElement(new SpriteAmbiente("/samurai/imagenes/ambiente/arbol.png", rndm.nextInt(60)));
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
    private void desaparecer(SpriteAmbiente elemento){
        ambienteEnPantalla.removeElement(elemento);
        elemento=null;
    }
    /**
     * Metodo que dibuja todos los elementos del vector en el el parametro que se le da.
     * @param g Graficos donde se dibujan los elementos del vector.
     */
    public void dibujar(Graphics g){
        for(int i=0; i<ambienteEnPantalla.size();i++){
            ((SpriteAmbiente)ambienteEnPantalla.elementAt(i)).dibujar(g);
        }
    }
    /**
     * Metodo que actualiza los Sprites de los enemigos.
     */
    public void actualizar(){
        int size = ambienteEnPantalla.size();
        int rnd = rndm.nextInt(10);
        if (rnd == 0 && size < 10) {
            this.agregarElemento(0);
        }
        for(int i=0; i<ambienteEnPantalla.size(); i++){
            elemento=(SpriteAmbiente)ambienteEnPantalla.elementAt(i);
            if(elemento.getY()>=Global.ALTO_PANTALLA){
                this.desaparecer(elemento);
            }else{
                elemento.mover();
            }
        }
    }
    /**
     * regresa el vector que contiene a los enemigos
     * @return vector que contiene a los enemigos
     */
    public Vector getVectorAmbiente(){
        return this.ambienteEnPantalla;
    }

}
