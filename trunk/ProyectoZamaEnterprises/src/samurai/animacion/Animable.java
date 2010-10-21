package samurai.animacion;

import javax.microedition.lcdui.Graphics;

/**
 * Interface que obliga a las clases a que tengan un método Dibujar.
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public interface Animable  {
    /**
     * Dibuja
     * @param g Graficos donde se dibuja
     */
    public abstract void dibujar(Graphics g);
}