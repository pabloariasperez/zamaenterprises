
package samurai.juego;

import javax.microedition.lcdui.game.Sprite;

/**
 * Clase que se encarga de revisar las colisiones que se realizan.
 * @author Pablo, Erik y Daniel
 * @version 1.2, octubre 2010
 *
 */
public class ManejadorColision {
   
    /**
     * El contrstuctor no realiza ninguna operación.
     */
    public ManejadorColision(){
        
    }
    /**
     * Metodo que regresa un booleano declarando si se realizo la condicion o no.
     * @param spriteA Sprite que colisiona.
     * @param spriteB Sprite que es colisionado.
     * @return Regresa el resultado de la colisión.
     */
    public boolean colisionArmaEnemigo(Sprite spriteA,Sprite spriteB){
        if (spriteA.collidesWith(spriteB, true)){
            return true;
        }
        return false;
    }
}
