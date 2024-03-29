/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.escenarios;

import java.io.IOException;
import java.util.Random;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.SpriteAmbiente;
import samurai.juego.Global;
import samurai.juego.Posicionador;

/**
 *
 * @author zama
 */
public class ManejadorAmbiente {

    private Vector ambienteEnPantalla;
    private Random rndm;
    private SpriteAmbiente elemento;
    private int elemetosEliminados;
    private Image arbol_1;
    private Image piedra_1;
    private Image arena_1;
    private Image arbol_2;
    /**
     * Constructor que inicializa el Vector como un Vector vacio.
     */
    public ManejadorAmbiente() {
        ambienteEnPantalla = new Vector();
        rndm = new Random();
        elemento = null;
        elemetosEliminados = 0;
        try {
            arbol_1 = Image.createImage("/samurai/imagenes/ambiente/spriteArbol.png");
            piedra_1 = Image.createImage("/samurai/imagenes/ambiente/spritePiedra.png");
            arena_1 = Image.createImage("/samurai/imagenes/ambiente/arenita.png");
            arbol_2 =Image.createImage("/samurai/imagenes/ambiente/spriteArbol2.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metodo que agrega los elementos del ambiente al Vector.
     * @param tipoAmbiente  elemento que se desea agregar
     */
    private void agregarElemento(int tipoAmbiente, int region) {
        try {
            switch (tipoAmbiente) {
                case SpriteAmbiente.ARBOL_1:
                    ambienteEnPantalla.addElement(new SpriteAmbiente(arbol_1, 10 + rndm.nextInt(60), region));
                    break;
                case SpriteAmbiente.PIEDRA_1:
                    if (region == 2) {
                        ambienteEnPantalla.addElement(new SpriteAmbiente(piedra_1, rndm.nextInt(5), region));
                    } else {
                        ambienteEnPantalla.addElement(new SpriteAmbiente(piedra_1, -20 + rndm.nextInt(40), region));
                    }
                    break;
                case SpriteAmbiente.ARENA_1:
                    ambienteEnPantalla.addElement(new SpriteAmbiente(arena_1, 100, region - 1));
                    break;
                 case SpriteAmbiente.ARBOL_2:
                    ambienteEnPantalla.addElement(new SpriteAmbiente(arbol_2, 10 + rndm.nextInt(60), region));
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
    private void desaparecer(SpriteAmbiente elemento) {
        ambienteEnPantalla.removeElement(elemento);
        elemento = null;
    }

    /**
     * Metodo que dibuja todos los elementos del vector en el el parametro que se le da.
     * @param g Graficos donde se dibujan los elementos del vector.
     */
    public void dibujar(Graphics g) {
        for (int i = ambienteEnPantalla.size() - 1; i >= 0; i--) {
            ((SpriteAmbiente) ambienteEnPantalla.elementAt(i)).dibujar(g);
        }
    }

    /**
     * Metodo que actualiza los Sprites de los enemigos.
     */
    public void actualizar() {
        int size = ambienteEnPantalla.size();

        int rnd = rndm.nextInt(1);
        if (rnd == 0 && size < 300) {
            this.agregarElemento(SpriteAmbiente.ARBOL_1, Posicionador.REGION_IZQUIERDA);
            this.agregarElemento(SpriteAmbiente.ARBOL_1, Posicionador.REGION_DERECHA);

            this.agregarElemento(SpriteAmbiente.ARBOL_2, Posicionador.REGION_IZQUIERDA);
            this.agregarElemento(SpriteAmbiente.ARBOL_2, Posicionador.REGION_DERECHA);

            this.agregarElemento(SpriteAmbiente.PIEDRA_1, Posicionador.REGION_CENTRO);
                this.agregarElemento(SpriteAmbiente.PIEDRA_1, Posicionador.REGION_DERECHA);



            rnd = rndm.nextInt(4);
            if (rnd == 0) {
                this.agregarElemento(SpriteAmbiente.ARENA_1, Posicionador.REGION_CENTRO);
            }
        }
        for (int i = 0; i < ambienteEnPantalla.size(); i++) {
            elemento = (SpriteAmbiente) ambienteEnPantalla.elementAt(i);
            if (elemento.getY() >= Global.ALTO_PANTALLA) {
                this.desaparecer(elemento);
                elemetosEliminados++;
            } else {
                elemento.mover();
            }
        }
        if (elemetosEliminados >= 10) {
            elemetosEliminados = 0;
            System.gc();
        }
    }

    /**
     * regresa el vector que contiene a los enemigos
     * @return vector que contiene a los enemigos
     */
    public Vector getVectorAmbiente() {
        return this.ambienteEnPantalla;
    }
}
