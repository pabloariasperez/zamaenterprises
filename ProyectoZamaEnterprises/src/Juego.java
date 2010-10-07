//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import samurai.menu.Fondo;
import samurai.menu.Boton;
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.*;
import samurai.juego.*;
import samurai.escenarios.*;

public class Juego extends GameCanvas {
       public static int ALTO;
       public static int ANCHO;
       private AppAnimacion midlet;
       private Animador animador; 
       private Graphics g;
       
       
        private ManejadorTeclado manejadorTec;

        private SpriteEnemigo enemigo;
        private ManejadorEnemigos manejadorEnemigos;


        private FondoCapa luna;

        private SpriteSekai sekai;
        private SpriteEspada efectos;
        private ManejadorSekai manejadorSekai;

    public Juego(AppAnimacion midlet) {

        super(true);

        this.midlet = midlet;
        this.setFullScreenMode(true);
        this.ANCHO = this.getWidth();
        this.ALTO = this.getHeight();

        g = this.getGraphics();
       luna = new FondoCapa("/samurai/imagenes/fondoLuna.png",-1,0);
        manejadorTec = new ManejadorTeclado(this);
        manejadorEnemigos = new ManejadorEnemigos();
        try {
            enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png",60,60);
            sekai= new SpriteSekai("/samurai/imagenes/sekai.png",(this.getWidth()/2)-20, this.getHeight()-60);
            efectos= new SpriteEspada("/samurai/imagenes/SpritesEfectos.png",(this.getWidth()/2)-20, this.getHeight()-60);
            manejadorSekai= new ManejadorSekai(sekai, efectos, manejadorTec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        manejadorEnemigos=new ManejadorEnemigos();
        manejadorEnemigos.agregarEnemigo(enemigo);
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

       
        
    }
    public Animador getAnimador(){
        return this.animador;
    }


    void dibujar() {
         g.setColor(0x00FFFFFF);
        // if(this.inicio){
      //       this.inicio = false;
       //  }
         
         //g.fillRect(0, 0, ANCHO, ALTO);
         luna.dibujar(g);
         manejadorSekai.dibujar(g);
         manejadorEnemigos.dibujar(g);
         flushGraphics();
    }

    void actualizar() throws InterruptedException {
          
        manejadorSekai.actualizar();
        manejadorEnemigos.actualizar();
        this.dibujar();
    }
  

}