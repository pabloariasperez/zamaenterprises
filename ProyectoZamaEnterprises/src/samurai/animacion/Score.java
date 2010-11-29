/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;

/**
 *
 * @author mi16
 */
public class Score {
    private Sprite spriteNumeros;
    
    private int[]numeros;
    private final int INDEX;
    private int x,y;
    
    public Score(int x, int y){
        this.x = x;
        this.y = y;
        this.numeros = new int[Global.DIGITOS_PUNTAJES];
        this.INDEX=28;
        
        try {
            this.spriteNumeros = new Sprite(Image.createImage("/samurai/imagenes/letrasScore.png"), 105/7, 120 / 6);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    


    public void actualizar(int numero){
        int num = numero;
        for(int i=5;i>=0;i--){
            this.numeros[i]= num%10;
            num = num/10;
        }
    }

    public void dibujar(Graphics g){
        for(int i=0; i<Global.DIGITOS_PUNTAJES;i++){
            this.spriteNumeros.setPosition(x+(15*i), y);
            this.spriteNumeros.setFrame(this.INDEX+this.numeros[i]);
            this.spriteNumeros.paint(g);
        }
    }

}
