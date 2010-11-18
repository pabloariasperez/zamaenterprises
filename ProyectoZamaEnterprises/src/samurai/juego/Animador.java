package samurai.juego;

import javax.microedition.lcdui.game.GameCanvas;

/**
 * Clase que anima los GameCanvas
 * @author Pablo, Erik, Daniel
 * @version 1.2 Octubre 2010
 */
public class Animador implements Runnable {
    //Atributos

    private Actualizable gmCanvas;      //Necesitamos un GameCanvas que además implemente Actualizable: actualizar(), dibujar()
    private Thread thread;              //Nuestro hilo de ejecución.
    private boolean corriendo;          //Nos dirá si debe seguir corriendo el Animador, también nos sirve para detenerlo.
    //Atributos relacionados con el tiempo haciendo uso de FRAMES.
    private long tiempoInicial, tiempoFinal;
    private final int DURACION_FRAME;

    /**
     * Constructor que inicializa las variables
     * @param gmCanvas GameCanvas que se va a animar
     */
    public Animador(GameCanvas gmCanvas) {
        DURACION_FRAME = 1000 / Global.FPS;
        //Preguntamos si el GameCanvas recibido está implementando la interface ACtualizable, que lo obliga a actualizar() y dibujar()
        if (gmCanvas instanceof Actualizable) {
            //Guardamos el GameCanvas del parámetro recibido casteado en el atributo local.
            this.gmCanvas = (Actualizable) gmCanvas;
        } else {
            //Por si se nos va uno que no cumpla devolvemos un errorcito en consola.
            System.out.println("Error:Casteando INTERFACES");
        }
    }

    /**
     * Crea el Thread y lo arranca.
     * Este Thread será quien use a el método run.
     */
    public void iniciar() {
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Método que se estará corriendo mientras Thread esté vivo.
     * Dibuja y actualiza el GameCanvas
     */
    public void run() {
        corriendo = true;       //Establecemos que el Animador estará corriendo.
        //Mientras corriendo sea TRUE estarán ejecutándose las tareas dentro del bloque.
        while (corriendo) {
            //Obtenemos un tiempo actual en milisegundos que después habremos de restar al final.
            tiempoInicial = System.currentTimeMillis();

            //Actualizamos y dibujamos el GameCanvas, de aquí la importancia que implementen Actualizable.
            this.gmCanvas.actualizar();
            if (!corriendo) {
                //Nos salimos =D!!
                return;
            }
            this.gmCanvas.dibujar();

            //Obtenemos el tiempo final, para comparar.
            tiempoFinal = System.currentTimeMillis();

            //Intentamos dormir el Thread si así es necesario.
            try {
                //Preguntamos si el DURACION_FRAME - TIEMPO EN QUE SE ACTUALIZÓ Y DIBUJÓ  es mayor o igual a cero; para dormir el THREAD.
                //Es decir, el tiempo que nos sobre nos permanecemos quietos. En caso de que estas acciones tomen más tiempo
                //se debe seguir de inmediato con la ejecución.
                if (DURACION_FRAME - (tiempoFinal - tiempoInicial) >= 0) {
                    Thread.sleep(DURACION_FRAME - (tiempoFinal - tiempoInicial));
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Metodo con el que se termina el run
     */
    public void terminar() {
        corriendo = false;
    }

    public boolean estaCorriendo(){
        return thread.isAlive();
    }
}