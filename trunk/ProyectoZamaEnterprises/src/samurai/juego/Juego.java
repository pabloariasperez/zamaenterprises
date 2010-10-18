package samurai.juego;

//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.*;
import samurai.escenarios.*;

public class Juego extends GameCanvas implements Actualizable {
       public static int ALTO_PANTALLA;
       public static int ANCHO_PANTALLA;
       private SamuraiEnterprises samuraiMidlet;
       private Animador animador; 
       private Graphics g;
      
       ManejadorEnemigos manejadorEnemigos;
        private ManejadorTeclado manejadorTec;
        private ManejadorColision colisionEnemigo;

        private SpriteEnemigo enemigo;

        Escenario escenario;
        FondoCapa fondo;

        int variante;
        int colorLaterales;
        int yAlto;
        int xDeFuncion;
        int[][] arregloCoordenadasX;
        int retrasoCamino;

        private Posicionador posicionador;
        private SpriteSekai sekai;
        private SpriteEspada efectos;
        private ManejadorSekai manejadorSekai;

    public Juego(SamuraiEnterprises midlet) {

        super(true);

        this.samuraiMidlet = midlet;
        this.setFullScreenMode(true);
        Juego.ANCHO_PANTALLA = this.getWidth();
        Juego.ALTO_PANTALLA = this.getHeight();

        g = this.getGraphics();


       fondo = new FondoCapa("/samurai/imagenes/fondoLuna.png",-1,0);
        //manejadorEnemigos= new ManejadorEnemigos();

       escenario=new Escenario();
       escenario.agregarFondo(fondo);
        manejadorTec = new ManejadorTeclado(this);
        variante = 0;
        colorLaterales = 0x0;
        yAlto = 0;
        retrasoCamino = 0;
        arregloCoordenadasX = new int[ALTO_PANTALLA - 50][2];
        for( int y=0; y < ALTO_PANTALLA - 50; y++ ){
            arregloCoordenadasX[y][0] = 0;
            arregloCoordenadasX[y][1] = 0;
        }
        
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
    public Animador getAnimador(){
        return this.animador;
    }


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