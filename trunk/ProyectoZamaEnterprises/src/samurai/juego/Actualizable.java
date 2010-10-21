/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 *
 * @author Pablo
 * Interface que obliga a que se deba actualizar y a que regresen un Graphics los "BICHOS" de una clase.
 */
public interface Actualizable {
    /**
     *
     */
    public abstract void actualizar();
    /**
     *
     */
    public abstract void dibujar();
}
