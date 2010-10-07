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

    private Boton botonNuevo,botonContinuar,botonPuntajes,botonOpciones,botonSalir;
    private Boton opcionSonido, opcionIdioma, opcionBorrar;
    public static int ALTO, ANCHO;
    private Logo logoTec;
    private FondoMenu fondo;
    private SamuraiEnterprises midlet;
    private Menu menu, menuActual;
    private Menu menuOpciones;
    private Animador animador; 
    private ManejadorTeclado manejadorTec;
    private Graphics g;
    private int pantallaActual;
    private final int PANTALLA_PRINCIPAL;
    private final int PANTALLA_OPCIONES;
    private boolean inicio;

    public MenuCanvas(SamuraiEnterprises midlet){
        super(true);
        this.midlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();
        this.ALTO = this.getHeight();
        this.ANCHO = this.getWidth();
        this.PANTALLA_PRINCIPAL = 1;
        this.PANTALLA_OPCIONES = 2;
        this.pantallaActual = this.PANTALLA_PRINCIPAL;

        manejadorTec = new ManejadorTeclado(this);
       this.inicio=true;
        
        

        try {
        menu = new Menu(5, this.ALTO,"/samurai/imagenes/tituloprincipal.png","/samurai/imagenes/slash.png");
        menuOpciones = new Menu(3,this.ALTO,"/samurai/imagenes/tituloprincipal.png","/samurai/imagenes/slash.png");
        fondo=new FondoMenu("/samurai/imagenes/fondo.png");
        this.creaBotones();
        this.creaBotonesOpciones();
        System.out.println("creando logo");
        logoTec = new Logo("/samurai/imagenes/tecsi.gif", -40, 0);
       System.out.println("logo creado");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.menuActual = this.menu;
        animador = new Animador(this);
        animador.iniciar();
        this.dibujar();
    }


     public void creaBotonesOpciones(){
        try {
            opcionSonido = new Boton("/samurai/imagenes/botonSonido.png");
            opcionIdioma = new Boton("/samurai/imagenes/botonCreditos.png");
            opcionBorrar = new Boton("/samurai/imagenes/botonAtras.png");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        menuOpciones.agregarBoton(opcionSonido);
        menuOpciones.agregarBoton(opcionIdioma);
        menuOpciones.agregarBoton(opcionBorrar);


    }

    public void creaBotones() throws IOException{
        botonNuevo = new Boton("/samurai/imagenes/botonNuevo.png");
        botonContinuar = new Boton("/samurai/imagenes/botonContinuar.png");
        botonPuntajes = new Boton("/samurai/imagenes/botonPuntajes.png");
        botonOpciones = new Boton("/samurai/imagenes/botonOpciones.png");
        botonSalir = new Boton("/samurai/imagenes/botonSalir.png");



        menu.agregarBoton(botonNuevo);
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

    public Animador getAnimador(){
        return this.animador;
    }


    public void dibujar(){

        g.setColor(0x00FFFFFF);
        g.fillRect(0, 0, ANCHO, ALTO);

        
        
        
            
        this.fondo.dibujar(g);
        this.menuActual.dibujar(g);
        this.logoTec.dibujar(g);
        this.flushGraphics();

    }
// ahi va
    public void actualizar(){
        try {
            logoTec.actualizar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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
