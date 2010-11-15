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

    public static final String JUEGO="JUEGO";
    public static final String MENU="MENU";
    public static final String PRESENTACION="PRESENTACION";
    public static final String SPLASH="SPLASH";

    /**
     * Actualiza
     */
    public abstract void actualizar();
    /**
     * Dibuja
     */
    public abstract void dibujar();

    public abstract String tipoCanvas();

    public abstract void destruir();

    public void pausar();

    public void correr();
}
