package samurai.juego;

import javax.microedition.lcdui.game.GameCanvas;


public class ManejadorTeclado{
    private static boolean PRESIONADO = true;
    private static boolean NO_PRESIONADO = false;
    private boolean arribaPresionado;
    private boolean abajoPresionado;
    private boolean firePresionado;
    private GameCanvas gmCanvas;


    public ManejadorTeclado(GameCanvas juego){
        this.arribaPresionado=false;
        this.abajoPresionado=false;
        this.firePresionado=false;
        this.gmCanvas = (GameCanvas) juego;
    }
    public int checarPresionado(){
        //Obtengo el estado actual de las teclas presionadas del teclado del celular.
        int tecladoActual = gmCanvas.getKeyStates();

        //Pregunto si está presionado cada uno de los botones para cambiar los respectivos
        //estatus de presionado de cada botón.
        if(!((tecladoActual & GameCanvas.UP_PRESSED)!=0)){
            System.out.print(arribaPresionado);
            this.arribaPresionado = NO_PRESIONADO;
        }

        return tecladoActual;
    }

    public boolean upPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !arribaPresionado &  ( tecladoActual & GameCanvas.UP_PRESSED)!=0){
           arribaPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
}
