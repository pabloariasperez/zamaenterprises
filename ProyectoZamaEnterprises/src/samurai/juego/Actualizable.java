/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

/**
 * Interface que obliga a que se deba actualizar y a que regresen un Graphics.
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public interface Actualizable {
    /**
     * Actualiza
     */
    public abstract void actualizar();
    /**
     * Dibuja
     */
    public abstract void dibujar();
}
