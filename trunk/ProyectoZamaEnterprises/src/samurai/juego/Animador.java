package samurai.juego;


import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;


/**
 *
 * @author mi16
 */
public class Animador implements Runnable
{
    //Atributos
    private Actualizable gmCanvas;      //Necesitamos un GameCanvas que además implemente Actualizable: actualizar(), dibujar()
    private Thread thread;              //Nuestro hilo de ejecución.

    private boolean corriendo;          //Nos dirá si debe seguir corriendo el Animador, también nos sirve para detenerlo.

    //Atributos relacionados con el tiempo haciendo uso de FRAMES.
    long tiempoInicial, tiempoFinal;
    private final int FPS = 60;
    private final int DURACION_FRAME = 1000/FPS;

    //Nuestro constructor recibe el GameCanvas que estaremos actualizando y dibujando.
    /**
     *
     * @param gmCanvas
     */
    public Animador(GameCanvas gmCanvas) {
        //Preguntamos si el GameCanvas recibido está implementando la interface ACtualizable, que lo obliga a actualizar() y dibujar()
        if( gmCanvas instanceof Actualizable ){
            //Guardamos el GameCanvas del parámetro recibido casteado en el atributo local.
            this.gmCanvas = (Actualizable)gmCanvas;
        }else{
            //Por si se nos va uno que no cumpla devolvemos un errorcito en consola.
            System.out.println("Error:Casteando INTERFACES");
        }
    }

    //Arrancamos nuestro Animador con iniciar. Crea el Thread y lo arranca. Este Thread será quien use a el método run.
    /**
     *
     */
    public void iniciar() {
        thread = new Thread(this);
        thread.start();
        corriendo = true;       //Establecemos que el Animador estará corriendo.
    }

    //Método que se estará corriendo mientras Thread esté vivo.
    /**
     *
     */
    public void run() {
        //Mientras corriendo sea TRUE estarán ejecutándose las tareas dentro del bloque.
        while ( corriendo ) {
            //Obtenemos un tiempo actual en milisegundos que después habremos de restar al final.
            tiempoInicial = System.currentTimeMillis();
            //Actualizamos y dibujamos el GameCanvas, de aquí la importancia que implementen Actualizable.
            this.gmCanvas.actualizar();
            this.gmCanvas.dibujar();
            //Obtenemos el tiempo final, para comparar.
            tiempoFinal = System.currentTimeMillis();

            //Intentamos dormir el Thread si así es necesario.
            try {
                //Preguntamos si el DURACION_FRAME - TIEMPO EN QUE SE ACTUALIZÓ Y DIBUJÓ  es mayor o igual a cero; para dormir el THREAD.
                //Es decir, el tiempo que nos sobre nos permanecemos quietos. En caso de que estas acciones tomen más tiempo
                //se debe seguir de inmediato con la ejecución.
                if ( DURACION_FRAME - (tiempoFinal-tiempoInicial) >= 0 )
                    Thread.sleep(DURACION_FRAME - (tiempoFinal-tiempoInicial));
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    //Con este método terminamos con el Animador. Desde evitar que siga corriendo y hasta interrumpir el Thread que se estaba usando.
    /**
     *
     */
    public void terminar() {
        corriendo = false;
    }

    //Método para saber a cuántos FPS (Frames Per Second) se está corriendo la aplicación.
    /**
     *
     * @return
     */
    public int getFPS(){
        return FPS;
    }
}