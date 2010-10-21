/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.menu.Splash;

/**
 *
 * @author Pablo, Erik y Daniel
 * @version 0.1, octubre 2010
 */
public class SplashCanvas extends GameCanvas implements Actualizable {
    //Declaramos todo lo necesario para hacer funcionar nuestros MIDlets estándar.
    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private final int ALTO_PANTALLA;
    private final int ANCHO_PANTALLA;
    private ManejadorTeclado teclado;
    private Animador animador;
    private TiempoEscenario tiempo;     //Reutilizamos esta clase, aunque fue concebida para los escenarios.
    private final int TIEMPO_SPLASH;
    private boolean estoyMostrandome;


    //Declaramos los SPLASHES y Colores que vamos a mostrar.
    private Stack splashes;
    private Stack colores;

    
    /**
     * Constructor que recibe al Samurai
     * @param samuraiMidlet  midlet que se recibe.
     */
    public SplashCanvas(SamuraiEnterprises samuraiMidlet){
        //Se manda TRUE en el constructor de la clase padre (GameCanvas) para decir que cuando se quiera saber si una tecla está presionada
        //se preguntará.
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;

        //Asignamos a nuestro parámetro "g" el Graphic del GameCanvas
        this.g = this.getGraphics();

        //Asginamos el ALTO_PANTALLA y ANCHO_PANTALLA de nuestra pantalla
        this.ALTO_PANTALLA = this.getHeight();
        this.ANCHO_PANTALLA = this.getWidth();

        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);

        //Inicializamos nuestro STACK
        splashes = new Stack();
        colores = new Stack();

        //Metemos a nuestros STACK's de Splashes y colores los que queremos mostrar. Atención con el órden en que son ingresados.
        splashes.push( new Splash("/samurai/imagenes/sekai.png", this) );
        colores.push(new Integer(0x000000));
        splashes.push( new Splash("/samurai/imagenes/itesmcel.png", this) );
        colores.push(new Integer(0xFFFFFF));
       
        //splashes.push( new Splash("imagenes/splashes/equipoSplash.png", this));
        //splashes.push( new Splash("imagenes/splashes/logoTecCEMSplash.png", this));


        //Establecemos TRUE mi estoyMostrandome
        estoyMostrandome = true;

        //Creamos nuestra animador y lo iniciamos.
        animador = new Animador(this);

        //Inicializamos nuestro tiempo.
        tiempo = new TiempoEscenario();
        //Establecemos cuánto queremos que dure cada SPLASH.
        TIEMPO_SPLASH = 2 * animador.getFPS();      //Recuérdese que el tiempo funciona a base de FRAMES como tiempo.

        animador.iniciar();
    }

   
    /**
     * Método para actualizar el Canvas
     */
    public void actualizar() {
        //Verificamos que el usuario no haya presionado el botón de acción FIRE para interrumpir el SPLASH
        if( teclado.firePresionado() && tiempo.tiempoActual() > animador.getFPS()/4 ){
            splashes.pop();     //Hacemos el POP para que ya muestre la siguiente imagen.
            colores.pop();
            tiempo.reiniciarTiempo();
        }

        //VAmos incrementando el tiempo mientras el STACK contenga algo.
        if(! splashes.isEmpty() ){
            
            //Comparo el tiempo actual con el tiempo estándar para dejar un SPLASH.
            if( tiempo.tiempoActual() < TIEMPO_SPLASH ){
                tiempo.incrementarTiempo();
            }else{
                //Cuando se supera el tiempo de splash hacemos un pop sin guardar el elemento, para que sea el nuevo splash a dibujar
                splashes.pop();
                colores.pop();
                //Reiniciamos nuestro tiempo para comenzar de nuevo el conteo.
                tiempo.reiniciarTiempo();
            }
        }else{
            animador.terminar();
            estoyMostrandome = false;
        }
        
    }

    
    /**
     * Metodo que dibuja el SPLASH en punta.
     */
    public void dibujar() {


        if(!splashes.isEmpty()){

            //Establecemos nuestro color para dibujar. Lo que se obtenga del stack de colores.
            g.setColor(((Integer)colores.peek()).intValue());
            //Limpiamos la pantalla.
            g.fillRect(0, 0, ANCHO_PANTALLA, ALTO_PANTALLA);
            ((Splash)splashes.peek()).dibujar(g);
        }

        this.flushGraphics();
    }

    /**
     * Metodo que regresa un booleano el cual indica si ya se terminaron de mostrar todos los Splashes.
     *
     * @return Regresa un booleano el cual indica si ya se terminaron de mostrar todos los Splashes.
     */
    public boolean estoyMostrandome(){
        return estoyMostrandome;
    }
}