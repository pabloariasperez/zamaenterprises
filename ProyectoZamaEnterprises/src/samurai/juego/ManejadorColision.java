
package samurai.juego;

import javax.microedition.lcdui.game.Sprite;

public class ManejadorColision {
   
    public ManejadorColision(){
        
    }
    public boolean colisionArmaEnemigo(Sprite spriteA,Sprite spriteB){
        if (spriteA.collidesWith(spriteB, true)){
            return true;
        }
        return false;
    }
}
