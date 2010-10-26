package samurai.juego;

//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.*;
import samurai.escenarios.*;

/**
 *
 * @author mi16
 */
public class Juego extends GameCanvas implements Actualizable {
    
        private SamuraiEnterprises samuraiMidlet;
        private Animador animador;
        private Graphics g;
      
        private ManejadorTeclado manejadorTec;
        private ManejadorColision colisionEnemigo;

        Escenario escenario;

        /**
         *
         * @param midlet
         */
        public Juego(SamuraiEnterprises midlet) {

        super(true);

        this.samuraiMidlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();

        //manejadorEnemigos= new ManejadorEnemigos();

       escenario=new Escenario();
       escenario.agregarFondo(fondo);
        manejadorTec = new ManejadorTeclado(this);
        
        
        try {
            enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png", 60, 60);
            escenario.agregarEnemigo(enemigo);
            posicionador = new Posicionador(this);
            sekai= new SpriteSekai("/samurai/imagenes/sekai.png",(this.getWidth()/2)-20, this.getHeight()-60);
            efectos= new SpriteEspada("/samurai/imagenes/SpritesEfectos.png",(this.getWidth()/2)-20, this.getHeight()-60);
            manejadorSekai= new ManejadorSekai(sekai, efectos, manejadorTec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        colisionEnemigo= new ManejadorColision();
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

       
        
    }
        /**
         *
         * @return
         */
        public Animador getAnimador(){
        return this.animador;
    }


        /**
         *
         */
        public void dibujar() {
         g.setColor(0x00654321);

         g.fillRect(0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
         escenario.dibujar(g);
         manejadorSekai.dibujar(g);
         manejadorEnemigos.dibujar(g);

         //PRUEBA DE ESCENARIO
         colorLaterales = 0x336600;

         if( variante < 100 ){
            variante++;
        }else{
            variante = 00;
        }

        if(retrasoCamino%12==0){
            for( int y = 50; y < ALTO_PANTALLA; y++ ){ //60 DEBERÍA SER ALTO_FONDO
                yAlto =  y ;
                arregloCoordenadasX[y-50][0] =  posicionador.recta(  yAlto, (float) variante - (yAlto*yAlto)/ALTO_PANTALLA) + 30 ;
                arregloCoordenadasX[y-50][1] =  posicionador.incrementoAncho( yAlto, 180, 40, 50) +arregloCoordenadasX[y-50][0];
            }
            retrasoCamino = 0;
            System.gc();
        }
        retrasoCamino++;

         for( int y = 50; y < ALTO_PANTALLA; y++ ){ //60 DEBERÍA SER ALTO_FONDO
            g.setColor( colorLaterales );
            if( y%3 == 0){
                colorLaterales += 0x100;
            }
            yAlto = y;

            g.drawLine( 0 , y, arregloCoordenadasX[y-50][0] , y);
            g.drawLine( ANCHO_PANTALLA , yAlto, arregloCoordenadasX[y-50][1], yAlto);
        }

         //FIN PRUEBA DE ESCENARIO
         
         flushGraphics();
    }

    /**
     *
     */
    public void actualizar(){
        try {
            manejadorSekai.actualizar();
            if (colisionEnemigo.colisionArmaEnemigo(efectos, enemigo) == true) {
                manejadorEnemigos.kill(enemigo);
                try {
                    enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png", 60, 60);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                escenario.agregarEnemigo(enemigo);
            } else {
                manejadorEnemigos.actualizar();
            }
            escenario.actualizar();
            this.dibujar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
  

}