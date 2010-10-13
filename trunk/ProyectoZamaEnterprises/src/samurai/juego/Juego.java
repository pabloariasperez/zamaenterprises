package samurai.juego;

//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.*;
import samurai.escenarios.*;

public class Juego extends GameCanvas {
       public static int ALTO_PANTALLA;
       public static int ANCHO_PANTALLA;
       private SamuraiEnterprises midlet;
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
        Coordenada yAlto;
        Coordenada xDeFuncion;
        Coordenada[][] arregloCoordenadasX;
        int retrasoCamino;

        private Posicionador posicionador;
        private SpriteSekai sekai;
        private SpriteEspada efectos;
        private ManejadorSekai manejadorSekai;

    public Juego(SamuraiEnterprises midlet) {

        super(true);

        this.midlet = midlet;
        this.setFullScreenMode(true);
        Juego.ANCHO_PANTALLA = this.getWidth();
        Juego.ALTO_PANTALLA = this.getHeight();

        g = this.getGraphics();


       fondo = new FondoCapa("/samurai/imagenes/fondoLuna.png",-1,0);
        manejadorEnemigos= new ManejadorEnemigos();

       escenario=new Escenario(manejadorEnemigos);
       escenario.agregarFondo(fondo);
        manejadorTec = new ManejadorTeclado(this);
        variante = 0;
        colorLaterales = 0x0;
        yAlto = new Coordenada( 0 );
        retrasoCamino = 0;
        arregloCoordenadasX = new Coordenada[ALTO_PANTALLA - 50][2];
        for( int y=0; y < ALTO_PANTALLA - 50; y++ ){
            arregloCoordenadasX[y][0] = new Coordenada( 0 );
            arregloCoordenadasX[y][1] = new Coordenada( 0 );
        }
        
        try {
            enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png", 60, 60);
            escenario.leerEnemigos(enemigo);
            posicionador = new Posicionador(this);
            sekai= new SpriteSekai("/samurai/imagenes/sekai.png",(this.getWidth()/2)-20, this.getHeight()-60);
            efectos= new SpriteEspada("/samurai/imagenes/SpritesEfectos.png",(this.getWidth()/2)-20, this.getHeight()-60);
            manejadorSekai= new ManejadorSekai(sekai, efectos, manejadorTec);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        colisionEnemigo= new ManejadorColision(efectos, enemigo);
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

       
        
    }
    public Animador getAnimador(){
        return this.animador;
    }


    void dibujar() {
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
                yAlto.setValor( y );
                arregloCoordenadasX[y-50][0].setValor( posicionador.recta(  yAlto, (float) variante - (yAlto.valor*yAlto.valor)/ALTO_PANTALLA) + 30 );
                arregloCoordenadasX[y-50][1].setValor( posicionador.incrementoAncho( yAlto, 180, 40, 50) +arregloCoordenadasX[y-50][0].valor );
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
            yAlto.setValor( y );

            g.drawLine( 0 , y, arregloCoordenadasX[y-50][0].valor , y);
            g.drawLine( ANCHO_PANTALLA , yAlto.valor, arregloCoordenadasX[y-50][1].valor, yAlto.valor);
        }

         //FIN PRUEBA DE ESCENARIO
         
         flushGraphics();
    }

    void actualizar() throws InterruptedException {

        manejadorSekai.actualizar();
        if(colisionEnemigo.colisionArmaEnemigo()==true){
            manejadorEnemigos.kill(enemigo);
            try {
                enemigo = new SpriteEnemigo("/samurai/imagenes/spriteZubat.png", 60, 60);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            escenario.leerEnemigos(enemigo);
        }else{
            manejadorEnemigos.actualizar();
        }
        escenario.actualizar();
        this.dibujar();
    }
  

}