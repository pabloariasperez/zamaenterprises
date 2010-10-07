
package samurai.juego;

import javax.microedition.lcdui.game.Sprite;

public class ManejadorColision {
    private Sprite spriteA;
    private Sprite spriteB;

    public ManejadorColision(Sprite spriteA, Sprite spriteB){
        this.spriteA=spriteA;
        this.spriteB=spriteB;
    }
    public boolean colisionArmaEnemigo(){
        if (this.spriteA.collidesWith(spriteB, true)){
            return true;
        }
        return false;
    }
}
