/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Pablo
 */
public class Camino extends GameCanvas implements Actualizable{
    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private ManejadorTeclado teclado;
    private Animador animador;

    public int ALTO_LINEA = 20;       //Pixeles de altura de la línea
    public int ALTO_FONDO = 60;
    public int PARAMETRO = 0;
    public int MARGEN_IZQUIERDO = 35;
    private int ANCHO_INICIAL = 150;
    private int PORCENTAJE_FINAL = 20;

    private int numeroLineas;
    private int[] posicionLineas;


    public Camino(SamuraiEnterprises samuraiMidlet){
        //Se manda TRUE en el constructor de la clase padre (GameCanvas) para decir que cuando se quiera saber si una tecla está presionada
        //se preguntará.
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;

        //Asignamos a nuestro parámetro "g" el Graphic del GameCanvas
        this.g = this.getGraphics();

        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);


        ALTO_LINEA = 10;       //Pixeles de altura de la línea
        ALTO_FONDO = 60;
        PARAMETRO = 0;
        MARGEN_IZQUIERDO = (Global.ANCHO_PANTALLA-ANCHO_INICIAL)/2;
        //Inicializo lo que requiera para el camino
        numeroLineas = (Global.ALTO_PANTALLA - ALTO_FONDO)/ALTO_LINEA;
        //Agregamos uno en caso de que haya residuo.
        if( (Global.ALTO_PANTALLA - ALTO_FONDO) - numeroLineas*ALTO_LINEA != 0){
            numeroLineas++;
         }
        posicionLineas = new int[numeroLineas];


        //Creamos nuestra animador y lo iniciamos.
        animador = new Animador(this);
        animador.iniciar();
    }

    public void posicionador(){
        for(  int lineaActual=0; lineaActual<numeroLineas; lineaActual++ ){
            posicionLineas[lineaActual] = MARGEN_IZQUIERDO + Camino.posicion(lineaActual*ALTO_LINEA + ALTO_FONDO, ALTO_FONDO, ANCHO_INICIAL, PORCENTAJE_FINAL, PARAMETRO);
        }
        System.gc();
    }

    public static int posicion( int alturaActual, int altoFondo, int anchoInicial, int porcentajeAnchoFinal, int parametro ){
        int pendienteRecta = (anchoInicial*(100-porcentajeAnchoFinal))/200;     //100*2    100 por le porcentaje; entre dos para obtener mitad.
        int numerador = Global.ALTO_PANTALLA - alturaActual;
        int denominador = Global.ALTO_PANTALLA - altoFondo;
        float yCubica = (float)(numerador*numerador*numerador)/(float)(denominador*denominador*denominador);
        int posicion = (int)(pendienteRecta*numerador/denominador + (float)parametro*yCubica);
        return (int)(posicion);
    }

    //Desarrollamos un posicionador en función de la reducción del camino
    public int incrementoAncho( int alturaActual, int altoFondo, int anchoInicial, int porcentajeAnchoFinal ){
        float y = (float)(Global.ALTO_PANTALLA - alturaActual)/(float)(Global.ALTO_PANTALLA - altoFondo);
        return (int)((1+(float)(y*(float)(porcentajeAnchoFinal-100)/(float)100))*(float)anchoInicial);
    }


    public void actualizar() {
        if( teclado.izqPresionado() ){
            PARAMETRO -=10;
        }
        if( teclado.derPresionado() ){
            PARAMETRO+=10;
        }
        if( teclado.upPresionado() ){
            ALTO_LINEA +=1;
        }
        if( teclado.downPresionado() && ALTO_LINEA!=1){
            ALTO_LINEA -=1;
        }
        //Inicializo lo que requiera para el camino
        numeroLineas = (Global.ALTO_PANTALLA - ALTO_FONDO)/ALTO_LINEA;
        //Agregamos uno en caso de que haya residuo.
        if( (Global.ALTO_PANTALLA - ALTO_FONDO) - numeroLineas*ALTO_LINEA != 0){
            numeroLineas++;
        }
        posicionLineas = new int[numeroLineas];
        System.gc();
        posicionador(  );
    }

    public void dibujar() {
        //Establecemos nuestro color para dibujar. NEGRO
        g.setColor(0x0);
        //Limpiamos la pantalla.
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);

        g.setColor(0x00ffffff);
        for(  int lineaActual=0; lineaActual<numeroLineas; lineaActual++ ){
            g.fillRect(
                    0,
                    lineaActual*ALTO_LINEA+ALTO_FONDO,
                    posicionLineas[lineaActual],
                    ALTO_LINEA
                     );
        }
        int incrementoX;
        for(  int lineaActual=0; lineaActual<numeroLineas; lineaActual++ ){
            incrementoX = posicionLineas[lineaActual] + incrementoAncho(lineaActual*ALTO_LINEA + ALTO_FONDO, ALTO_FONDO, ANCHO_INICIAL, PORCENTAJE_FINAL);
            g.fillRect(
                    incrementoX,
                    lineaActual*ALTO_LINEA+ALTO_FONDO,
                    Global.ANCHO_PANTALLA - incrementoX,
                    ALTO_LINEA
                     );
        }
        flushGraphics();
    }
}
