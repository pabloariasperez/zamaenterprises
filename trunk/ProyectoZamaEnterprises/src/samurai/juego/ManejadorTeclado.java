package samurai.juego;

import javax.microedition.lcdui.game.GameCanvas;


public class ManejadorTeclado{
    private static boolean PRESIONADO = true;
    private static boolean NO_PRESIONADO = false;
    private boolean arribaPresionado;
    private boolean abajoPresionado;
    private boolean firePresionado;
    private boolean izqPresionado;
    private boolean derPresionado;
    private boolean aPresionado;
    private boolean bPresionado;
    private GameCanvas gmCanvas;


    public ManejadorTeclado(GameCanvas juego){
        this.arribaPresionado=false;
        this.abajoPresionado=false;
        this.firePresionado=false;
        this.izqPresionado=false;
        this.derPresionado=false;
        this.aPresionado=false;
        this.bPresionado=false;
        this.gmCanvas = (GameCanvas) juego;
    }
    public int checarPresionado(){
        //Obtengo el estado actual de las teclas presionadas del teclado del celular.
        int tecladoActual = gmCanvas.getKeyStates();

        //Pregunto si está presionado cada uno de los botones para cambiar los respectivos
        //estatus de presionado de cada botón.
        if(!((tecladoActual & GameCanvas.UP_PRESSED)!=0)){
            this.arribaPresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.DOWN_PRESSED)!=0)){
            this.abajoPresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.FIRE_PRESSED)!=0)){
            this.firePresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.LEFT_PRESSED)!=0)){
            this.izqPresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.RIGHT_PRESSED)!=0)){
            this.derPresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.GAME_A_PRESSED)!=0)){
            this.aPresionado = NO_PRESIONADO;
        }
        if(!((tecladoActual & GameCanvas.GAME_B_PRESSED)!=0)){
            this.bPresionado = NO_PRESIONADO;
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
    public boolean downPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !abajoPresionado &  ( tecladoActual & GameCanvas.DOWN_PRESSED)!=0){
           abajoPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    public boolean firePresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !firePresionado &  ( tecladoActual & GameCanvas.FIRE_PRESSED)!=0){
           firePresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    public boolean izqPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !izqPresionado &  ( tecladoActual & GameCanvas.LEFT_PRESSED)!=0){
           izqPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    public boolean derPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !derPresionado &  ( tecladoActual & GameCanvas.RIGHT_PRESSED)!=0){
           derPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    public boolean aPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !aPresionado &  ( tecladoActual & GameCanvas.GAME_A_PRESSED)!=0){
           aPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    public boolean bPresionado(){
        //Se verifica qué teclas están presionadas. Además se actualiza el estado actual de
        //presionado de cada tecla.
       int tecladoActual = checarPresionado();
       if( !bPresionado &  ( tecladoActual & GameCanvas.GAME_B_PRESSED)!=0){
           bPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
}
