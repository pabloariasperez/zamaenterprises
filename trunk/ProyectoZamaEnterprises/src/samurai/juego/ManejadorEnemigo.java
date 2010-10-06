/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;
import samurai.animacion.Animable;


public class ManejadorEnemigo implements Animable {

    private SpriteEnemigo enemigo;
    private ManejadorTeclado manejadorTec;
    private boolean estoyAnimandome;


    public ManejadorEnemigo( SpriteEnemigo enemigo, ManejadorTeclado manejadorTec){

        this.enemigo = enemigo;
        this.manejadorTec = manejadorTec;
        
    }

    public void trayectoriaEnemigo(){

    }


    public void dibujar(Graphics g) {
        enemigo.dibujar(g);
    }

    public void actualizar(){
        enemigo.actualizar();

    }

}
