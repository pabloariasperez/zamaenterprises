//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import samurai.menu.Fondo;
import samurai.menu.Boton;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.SpriteEfectos;
import samurai.animacion.SpriteSekai;
import samurai.juego.*;
import samurai.escenarios.*;

public class Juego extends GameCanvas {
       public static int ALTO;
       public static int ANCHO;
       private AppAnimacion midlet;
       private Animador animador; 
       private Graphics g;
       
        
        private int pantalla = 0;

        private ManejadorTeclado manejadorTec;
        
        private SpriteSekai sekai;
        private SpriteEfectos efectos;
        private ManejadorSekai manejadorSekai;

    public Juego(AppAnimacion midlet) {

        super(true);

        this.midlet = midlet;
        this.setFullScreenMode(true);
        this.ANCHO = this.getWidth();
        this.ALTO = this.getHeight();

        g = this.getGraphics();

       
        manejadorTec = new ManejadorTeclado(this);
        try {
            
            sekai= new SpriteSekai("/samurai/imagenes/sekai.png",(this.getWidth()/2)-20, this.getHeight()-60);
            efectos= new SpriteEfectos("/samurai/imagenes/SpritesEfectos.png",(this.getWidth()/2)-20, this.getHeight()-60);
            manejadorSekai= new ManejadorSekai(sekai, efectos, manejadorTec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

       
        
    }

    void dibujar() {
         g.setColor(0x00FFFFFF);
        // if(this.inicio){
      //       this.inicio = false;
       //  }
         g.fillRect(0, 0, ANCHO, ALTO);
         
         manejadorSekai.dibujar(g);
         flushGraphics();
    }

    void actualizar() throws InterruptedException {
          
        manejadorSekai.actualizar();
        if( manejadorTec.upPresionado()){

        }else if(manejadorTec.downPresionado() ){

        }
        else if(manejadorTec.firePresionado()){
        }
        
        else if(manejadorTec.firePresionado()){

    }
        this.dibujar();
    }

    public void cambiarPantalla(){
        this.pantalla = 1;
        this.dibujar();
    }

}