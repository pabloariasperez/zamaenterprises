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
    private Menu menu, menuActual;
    private Menu menuOpciones;
     private Animador animador; 
    private ManejadorTeclado manejadorTec;
     private Graphics g;
     private int pantallaActual;
     private final int PANTALLA_PRINCIPAL;
     private final int PANTALLA_OPCIONES;
     private boolean banderaPresionado;

    public MenuCanvas(AppAnimacion midlet){
        super(true);
        this.midlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();
        this.ALTO = this.getHeight();
        this.ANCHO = this.getWidth();
        this.PANTALLA_PRINCIPAL = 1;
        this.PANTALLA_OPCIONES = 2;
        this.banderaPresionado = false;
        this.pantallaActual = this.PANTALLA_PRINCIPAL;

        manejadorTec = new ManejadorTeclado(this);
        menu = new Menu(5, this.ALTO);
        menuOpciones = new Menu(3,this.ALTO);
        this.menuActual = this.menu;

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
       
        if(this.pantallaActual==this.PANTALLA_PRINCIPAL){
            this.pantallaActual = this.PANTALLA_OPCIONES;
            this.menuActual = this.menuOpciones;

        }
        else{
            this.pantallaActual = this.PANTALLA_PRINCIPAL;
            this.menuActual = this.menu;
        }
        

    }


    public void dibujar(){

         g.setColor(0x00FFFFFF);
         g.fillRect(0, 0, ANCHO, ALTO);
         
        this.menuActual.dibujar(g);

        this.flushGraphics();

    }
// ahi va
    public void actualizar(){

        if( manejadorTec.upPresionado()){
            //arribaPresionado = true;
            System.out.println("ARRIBA");
            this.menuActual.moverOpcion(-1);

        }else if(manejadorTec.downPresionado() ){
            //abajoPresionado = true;
            System.out.println("ABAJO");
            this.menuActual.moverOpcion(1);
        }

         else if(this.menu.getPosition() == 0  && manejadorTec.firePresionado()){
            midlet.switchDisplay();
        }
        else if(this.menu.getPosition() == 3  && manejadorTec.firePresionado()){
            this.cambiarPantalla();
        }
          else if(this.menuOpciones.getPosition() == 2  && manejadorTec.firePresionado()){
            this.cambiarPantalla();

        }


        else if(this.menu.getPosition() == 4 && manejadorTec.firePresionado()){
            midlet.destroyApp(true);
            midlet.notifyDestroyed();
    }
        this.dibujar();
    }

}
