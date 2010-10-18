/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.animacion;

import javax.microedition.lcdui.Graphics;

/**
 *
 * @author mi16
 * Interface que obliga a nuestros "BICHOS" a que tengan un método Dibujar.
 */
public interface Animable  {
    public abstract void dibujar(Graphics g);
}