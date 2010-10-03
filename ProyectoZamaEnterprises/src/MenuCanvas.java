/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.juego.ManejadorTeclado;
import samurai.menu.*;
/**
 *
 * @author mi16
 */
public class MenuCanvas extends GameCanvas {

    private Boton botonJugar,botonContinuar,botonMultiplayer,botonPuntajes,botonOpciones,botonTutorial,botonCreditos, botonSalir;
    private Boton opcionSonido, opcionIdioma, opcionBorrar;
    public static int ALTO, ANCHO;
    private Fondo fondo;
    private AppAnimacion midlet;
    private Menu menu;
    private Menu menuOpciones;
     private Animador animador; 
    private ManejadorTeclado manejadorTec;
     private Graphics g;
     private int pantallaActual;
     private final int PANTALLA_PRINCIPAL;
     private final int PANTALLA_OPCIONES;

    public MenuCanvas(AppAnimacion midlet){
        super(true);
        this.ALTO = this.getHeight();
        this.ANCHO = this.getWidth();
        this.midlet = midlet;
        g = this.getGraphics();
        this.PANTALLA_PRINCIPAL = 1;
        this.PANTALLA_OPCIONES = 2;
        this.pantallaActual = this.PANTALLA_PRINCIPAL;

        manejadorTec = new ManejadorTeclado(this);
        menu = new Menu(5, this.ALTO);
        menuOpciones = new Menu(3,this.ALTO);


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
    public void cambiarPantalla(){

    }


    public void dibujar(){
        if(this.pantallaActual==this.PANTALLA_PRINCIPAL)
             this.menu.dibujar(g);
         else
             this.menuOpciones.dibujar(g);

         this.fondo.dibujar(g);
    }

    public void actualizar(){
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

}
