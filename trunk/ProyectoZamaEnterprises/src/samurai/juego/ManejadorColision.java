
package samurai.juego;

import javax.microedition.lcdui.game.Sprite;

/**
 *
 * @author mi16
 */
public class ManejadorColision {
   
    /**
     *
     */
    public ManejadorColision(){
        
    }
    /**
     *
     * @param spriteA
     * @param spriteB
     * @return
     */
    public boolean colisionArmaEnemigo(Sprite spriteA,Sprite spriteB){
        if (spriteA.collidesWith(spriteB, true)){
            return true;
        }
        return false;
    }
}
