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
        private ManejadorColision colisionEnemigo;

        private SpriteEnemigo enemigo;
        private ManejadorEnemigos manejadorEnemigos;

        private ManejadorFondos manejadorFondos;
        private FondoCapa luna;

        int variante;
        int colorLaterales;
        Coordenada yAlto;
        Coordenada xDeFuncion;
        private Posicionador posicionador;
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
       manejadorFondos = new ManejadorFondos();
       manejadorFondos.agregarFondo(luna);


        manejadorTec = new ManejadorTeclado(this);
        manejadorEnemigos = new ManejadorEnemigos();
        variante = 10;
        colorLaterales = 0x0;
        yAlto = new Coordenada( 0 );
        try {
            enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png",60,60);
            posicionador = new Posicionador(this);
            sekai= new SpriteSekai("/samurai/imagenes/sekai.png",(this.getWidth()/2)-20, this.getHeight()-60);
            efectos= new SpriteEspada("/samurai/imagenes/SpritesEfectos.png",(this.getWidth()/2)-20, this.getHeight()-60);
            manejadorSekai= new ManejadorSekai(sekai, efectos, manejadorTec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        manejadorEnemigos=new ManejadorEnemigos();
        manejadorEnemigos.agregarEnemigo(enemigo);
        colisionEnemigo= new ManejadorColision(efectos, enemigo);
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

       
        
    }
    public Animador getAnimador(){
        return this.animador;
    }


    void dibujar() {
         g.setColor(0x00FFFFFF);

         g.fillRect(0, 0, ANCHO, ALTO);

         manejadorFondos.dibujar(g);
         manejadorSekai.dibujar(g);
         manejadorEnemigos.dibujar(g);


         colorLaterales = 0x33FF00;

         for( int y = 50; y < ALTO; y++ ){ //60 DEBERÍA SER ALTO_FONDO
            g.setColor( colorLaterales );
            colorLaterales += 0x1;
            if( variante < 60 ){
                variante++;
            }else{
                variante = 10;
            }
            yAlto.setValor( y );
            xDeFuncion = new Coordenada( posicionador.recta(  yAlto, (float) variante) + 30 );
            g.drawLine( 0 , y, xDeFuncion.valor , y);

            g.drawLine( ANCHO , yAlto.valor, posicionador.incrementoAncho( yAlto, 180, 40, 50) + xDeFuncion.valor , yAlto.valor);
        }
         
         flushGraphics();
    }

    void actualizar() throws InterruptedException {
          
        manejadorSekai.actualizar();
        if(colisionEnemigo.colisionArmaEnemigo()==true){
            manejadorEnemigos.kill(enemigo);
        }else{
        manejadorEnemigos.actualizar();
        }
        manejadorFondos.actualizar();
        this.dibujar();
    }
  

}