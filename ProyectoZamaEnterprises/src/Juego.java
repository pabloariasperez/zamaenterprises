//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.juego.*;
import samurai.escenarios.*;

public class Juego extends GameCanvas {
       public static int ALTO;
       public static int ANCHO;
       private AppAnimacion midlet;
       private Animador animador; 
       private Graphics g;
       
        private Boton botonJugar,botonContinuar,botonMultiplayer,botonPuntajes,botonOpciones,botonTutorial,botonCreditos, botonSalir;
         private Boton opcionSonido, opcionIdioma, opcionBorrar;
        private Menu menu, menuOpciones;
        private Fondo fondo;
        private int pantalla = 0;

        private ManejadorTeclado manejadorTec;

    public Juego(AppAnimacion midlet) {

        super(true);

        this.midlet = midlet;
        this.setFullScreenMode(true);
        this.ANCHO = this.getWidth();
        this.ALTO = this.getHeight();

        g = this.getGraphics();

        menu = new Menu(5);
        menuOpciones = new Menu(3);
        try {
            this.creaBotones();
            this.creaBotonesOpciones();
            fondo = new Fondo("/samurai/imagenes/tecsi.gif", 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();

        manejadorTec = new ManejadorTeclado(this);
        
    }

    public void creaBotonesOpciones(){
        try {
            opcionSonido = new Boton("/samurai/imagenes/Sonido.jpg", "/samurai/imagenes/Sonido1.jpg");
            opcionIdioma = new Boton("/samurai/imagenes/Idioma.jpg", "/samurai/imagenes/Idioma1.jpg");
            opcionBorrar = new Boton("/samurai/imagenes/Borrar.jpg", "/samurai/imagenes/Borrar1.jpg");
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menuOpciones.agregarBoton(opcionSonido);
        menuOpciones.agregarBoton(opcionIdioma);
        menuOpciones.agregarBoton(opcionBorrar);
       
       
    }

    public void creaBotones() throws IOException{
        botonJugar = new Boton("/samurai/imagenes/jugar.jpg", "/samurai/imagenes/Jugar1.jpg");
        botonContinuar = new Boton("/samurai/imagenes/Continuar.jpg", "/samurai/imagenes/Continuar1.jpg");
        botonMultiplayer = new Boton("/samurai/imagenes/Multiplayer.jpg", "/samurai/imagenes/Multiplayer1.jpg");
        botonPuntajes = new Boton("/samurai/imagenes/Puntajes.jpg", "/samurai/imagenes/Puntajes1.jpg");
        botonOpciones = new Boton("/samurai/imagenes/Opciones.jpg", "/samurai/imagenes/Opciones1.jpg");
        botonTutorial = new Boton("/samurai/imagenes/Tutorial.jpg", "/samurai/imagenes/Tutorial1.jpg");
        botonCreditos = new Boton("/samurai/imagenes/Creditos.jpg", "/samurai/imagenes/Creditos1.jpg");
        botonSalir = new Boton("/samurai/imagenes/Salir.jpg", "/samurai/imagenes/Salir1.jpg");



        menu.agregarBoton(botonJugar);
        menu.agregarBoton(botonContinuar);
        //menu.agregarBoton(botonMultiplayer);
        menu.agregarBoton(botonPuntajes);
        menu.agregarBoton(botonOpciones);
        //menu.agregarBoton(botonTutorial);
        //menu.agregarBoton(botonCreditos);
        menu.agregarBoton(botonSalir);


    }

     void dibujar() {
         g.setColor(0x00FFFFFF);
        // if(this.inicio){
      //       this.inicio = false;
       //  }
         g.fillRect(0, 0, ANCHO, ALTO);
         if(pantalla==0)
             this.menu.dibujar(g);
         else
             this.menuOpciones.dibujar(g);

         this.fondo.dibujar(g);

         flushGraphics();
    }

    void actualizar() throws InterruptedException {
          try {
            fondo.actualizar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
       
        if( manejadorTec.upPresionado()){
            //arribaPresionado = true;
            System.out.println("ARRIBA");
            menu.moverOpcion(-1);
        }else if(manejadorTec.downPresionado() ){
            //abajoPresionado = true;
            System.out.println("ABAJO");
            menu.moverOpcion(1);
        }
        else if(this.menu.getPosition() == 3  & manejadorTec.firePresionado()){
            this.cambiarPantalla();
        }
        
        else if(this.menu.getPosition() == 4 & manejadorTec.firePresionado()){
            midlet.destroyApp(true);
            midlet.notifyDestroyed();
    }
        this.dibujar();
    }

    public void cambiarPantalla(){
        this.pantalla = 1;
        this.dibujar();
    }

}