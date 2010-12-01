package samurai.animacion;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;

/**
 * Clase encargada de mostrar el score en juego
 * @author Pablo, Erik, Daniel
 * @version 1.0 Noviembre 2010
 */
public class Score {
    private Sprite spriteNumeros;
    
    private int[]numeros;
    private final int INDEX=28;
    private int x,y;
    
    /**
     * Constructor: inicializa la x y y, ademas de crear el sprite.
     * @param x coordenada x donde se dibuja
     * @param y coordenada y donde se dibuja
     */
    public Score(int x, int y){
        this.x = x;
        this.y = y;
        this.numeros = new int[Global.DIGITOS_PUNTAJES];
        
        try {
            this.spriteNumeros = new Sprite(Image.createImage("/samurai/imagenes/letrasScore.png"), 105/7, 120 / 6);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Actualiza el score
     * @param numero score actual
     */
    public void actualizar(int numero){
        int num = numero;
        for(int i=Global.DIGITOS_PUNTAJES-1;i>=0;i--){
            this.numeros[i]= num%10;
            num = num/10;
        }
    }

    /**
     * Dibuja al score
     * @param g Graficos donde se dibuja
     */
    public void dibujar(Graphics g){
        for(int i=0; i<Global.DIGITOS_PUNTAJES;i++){
            this.spriteNumeros.setPosition(x+(15*i), y);
            this.spriteNumeros.setFrame(this.INDEX+this.numeros[i]);
            this.spriteNumeros.paint(g);
        }
    }
}
