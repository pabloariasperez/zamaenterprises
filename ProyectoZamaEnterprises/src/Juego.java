
import java.io.IOException;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mi16
 */
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
        private boolean arribaPresionado = false;
        private boolean abajoPresionado = false;
        private boolean opcionPresionado = false;
        private int pantalla = 0;

    public Juego(AppAnimacion midlet) {

        super(true);

        this.midlet = midlet;
        this.setFullScreenMode(true);
        this.ANCHO = this.getWidth();
        this.ALTO = this.getHeight();

        g = this.getGraphics();

        menu = new Menu(8);
        menuOpciones = new Menu(3);
        try {
            this.creaBotones();
            this.creaBotonesOpciones();
            fondo = new Fondo("/tecsi.gif", 0, 0);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();
        
    }

    public void creaBotonesOpciones(){
        try {
            opcionSonido = new Boton("/Sonido.jpg", "/Sonido1.jpg");
            opcionIdioma = new Boton("/Idioma.jpg", "/Idioma1.jpg");
            opcionBorrar = new Boton("/Borrar.jpg", "/Borrar1.jpg");
           
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menuOpciones.agregarBoton(opcionSonido);
        menuOpciones.agregarBoton(opcionIdioma);
        menuOpciones.agregarBoton(opcionBorrar);
       
       
    }

    public void creaBotones() throws IOException{
        botonJugar = new Boton("/jugar.jpg", "/Jugar1.jpg");
        botonContinuar = new Boton("/Continuar.jpg", "/Continuar1.jpg");
        botonMultiplayer = new Boton("/Multiplayer.jpg", "/Multiplayer1.jpg");
        botonPuntajes = new Boton("/Puntajes.jpg", "/Puntajes1.jpg");
        botonOpciones = new Boton("/Opciones.jpg", "/Opciones1.jpg");
        botonTutorial = new Boton("/Tutorial.jpg", "/Tutorial1.jpg");
        botonCreditos = new Boton("/Creditos.jpg", "/Creditos1.jpg");
        botonSalir = new Boton("/Salir.jpg", "/Salir1.jpg");



        menu.agregarBoton(botonJugar);
        menu.agregarBoton(botonContinuar);
        menu.agregarBoton(botonMultiplayer);
        menu.agregarBoton(botonPuntajes);
        menu.agregarBoton(botonOpciones);
        menu.agregarBoton(botonTutorial);
        menu.agregarBoton(botonCreditos);
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

    void actualizar() {
        try {
            fondo.actualizar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        int teclado = getKeyStates();

        if(!( (teclado & UP_PRESSED)!= 0 ) )
                arribaPresionado = false;

        if(!( (teclado & FIRE_PRESSED)!= 0 ) )
                opcionPresionado = false;

        if(!( (teclado & DOWN_PRESSED)!= 0 ) )
                abajoPresionado = false;

        if( !arribaPresionado & (teclado & UP_PRESSED)!= 0  ){
            arribaPresionado = true;
            System.out.println("ARRIBA");
            menu.moverOpcion(-1);
            menuOpciones.moverOpcion(-1);
        }else if(!abajoPresionado & (teclado & DOWN_PRESSED)!= 0 ){
            abajoPresionado = true;
            System.out.println("ABAJO");
            menu.moverOpcion(1);
            menuOpciones.moverOpcion(1);
        }
        else if(!opcionPresionado & this.menu.getPosition() == 4  & (teclado & FIRE_PRESSED)!=0){
            opcionPresionado = false;
            this.cambiarPantalla();
        }
        
        else if(!opcionPresionado & this.menu.getPosition() == 7  & (teclado & FIRE_PRESSED)!=0){
            opcionPresionado = false;
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


