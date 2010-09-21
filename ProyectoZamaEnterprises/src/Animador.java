
public class Animador implements Runnable
{
    private Juego juego;       
    private boolean corriendo;
    private Thread thread;

    private final int FPS = 50;
    private final int RETARDO = 1000/FPS;
//
    public Animador(Juego juego) {

        this.juego = juego;
    }

    public void iniciar() {

        thread = new Thread(this);
        thread.start();     
    }

    public void run() {

        corriendo = true;

        while ( corriendo ) {

            long ini = System.currentTimeMillis();
            juego.actualizar();
            juego.dibujar();
            long fin = System.currentTimeMillis();

            try {
                if ( RETARDO - (fin-ini) >= 0 ) 
                    Thread.sleep(RETARDO - (fin-ini));
            } catch (InterruptedException ex) { }
        }
    }

    public void terminar() {
        corriendo = false;
    }
}
