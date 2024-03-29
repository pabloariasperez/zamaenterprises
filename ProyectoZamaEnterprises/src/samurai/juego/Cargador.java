package samurai.juego;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Pablo
 */
public class Cargador extends GameCanvas implements Runnable {
    private Thread hiloDeCarga;
    private int estado;
    private Graphics g;

    /**
     *Valor de estado del Cargador cuando no está trabajando, está en su inicio.
     */
    public static int COMENZANDO = 0;
    /**
     *Valor de estado del Cargador cuando está CARGANDO, es decir, espera que el constructor se termine.
     */
    public static int CARGANDO = 1;
    /**
     *Valor de estado del Cargador cuando se ha terminado de calcular un nuevo eje.
     */
    public static int TERMINADO = 2;

    private boolean corriendo;
    private String mensaje;
    private String mensajeGeneral;
    private String puntitos;

    /**
     *
     * @param mensajeGeneral
     */
    public Cargador(String mensajeGeneral){
        super(true);
        setFullScreenMode(true);
        this.g = this.getGraphics();
        this.mensajeGeneral = mensajeGeneral;
        mensaje = "...";
        puntitos = "";
        estado = COMENZANDO;
    }

    /**
     * corre el thread
     */
    public void run() {
        corriendo = true;
        estado = CARGANDO;
        while( corriendo ){
            if(puntitos.length() == 3){
                puntitos = "";
            }else{
                puntitos += ".";
            }


            //Limpiamos pantalla
            g.setColor(0x0);
            g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);

            //Dibujamos mensajes
            g.setColor(0x00ffffff);
            g.drawString(mensajeGeneral + puntitos, 10, Global.ALTO_PANTALLA - 30, Graphics.LEFT | Graphics.BOTTOM);
            g.drawString("--"+mensaje + puntitos, 10, Global.ALTO_PANTALLA - 10, Graphics.LEFT|Graphics.BOTTOM);

            try {
                hiloDeCarga.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            flushGraphics();
       }
        estado = TERMINADO;
        
        mensaje = null;
        g = null;
        hiloDeCarga = null;
    }

    /**
     *Devuelve el estado actual del Thread Cargador.
     * @return Devuelve alguno de los estado actual del Cargador.
     */
    public int getEstado() {
        return estado;
    }

    /**
     * inicia el hilo de carga
     */
    public void iniciar() {
        if(estado==COMENZANDO){
            hiloDeCarga = new Thread(this);
            hiloDeCarga.start();
        }
    }

    /**
     * termina el run
     */
    public void terminar(){
        if(corriendo){
            corriendo=false;
        }
    }

    /**
     * cambia el mensaje que se muestra
     * @param mensaje nuevo mensaje
     */
    public void cambiarMensaje(String mensaje){
        this.mensaje = mensaje;
    }
}
