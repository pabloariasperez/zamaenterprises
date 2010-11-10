package samurai.juego;

import javax.microedition.lcdui.game.GameCanvas;


/**
 * Clase que se encarga de recibir e interpretar todas las señales del teclado
 * @author Pablo, Erik y Daniel
 * @version 1.0, septiembre 2010
 */
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


    /**
     * Constructor que inicializa todas las variables, establaciendo a gmCanvas como el canvas que utilizara al manejador
     * @param gmCanvas
     */
    public ManejadorTeclado(GameCanvas gmCanvas){
        this.arribaPresionado=false;
        this.abajoPresionado=false;
        this.firePresionado=false;
        this.izqPresionado=false;
        this.derPresionado=false;
        this.aPresionado=false;
        this.bPresionado=false;
        this.gmCanvas = gmCanvas;
    }
    /**
     * Metodo que obtiene el estado actual de las teclas presionadas del teclado.
     * @return Regresa el estado fisico del teclado
     */
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

    /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla ARRIBA esta presionada o no.
     */
    public boolean upPresionado(){
       
       int tecladoActual = checarPresionado();
       if( !arribaPresionado &  ( tecladoActual & GameCanvas.UP_PRESSED)!=0){
           arribaPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
      /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla ABAJO esta presionada o no.
     */
    public boolean downPresionado(){
        
       int tecladoActual = checarPresionado();
       if( !abajoPresionado &  ( tecladoActual & GameCanvas.DOWN_PRESSED)!=0){
           abajoPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
     /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla FIRE esta presionada o no.
     */
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
    /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla IZQUIERDA esta presionada o no.
     */
    public boolean izqPresionado(){
        
       int tecladoActual = checarPresionado();
       if( !izqPresionado &  ( tecladoActual & GameCanvas.LEFT_PRESSED)!=0){
           izqPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
    /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla DERECHA esta presionada o no.
     */
    public boolean derPresionado(){
       
       int tecladoActual = checarPresionado();
       if( !derPresionado &  ( tecladoActual & GameCanvas.RIGHT_PRESSED)!=0){
           derPresionado=PRESIONADO;
           return true;
        }
           return false;
    }
     /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla A esta presionada o no.
     */
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
   /**
     *Metodo que verifica que teclas estan presionadas. Tambien actualiza el estado actual de presionada de cada tecla
     * @return Regresa un booleano que especifica si la tecla B esta presionada o no.
     */
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
