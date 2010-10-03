
public class Animador implements Runnable
{
    private Juego juego;
    private MenuCanvas menu;
    private boolean corriendo;
    private Thread thread;

    private final int FPS = 50;
    private final int RETARDO = 1000/FPS;
//
    public Animador(Juego juego) {

        this.juego = juego;
    }

    public Animador(MenuCanvas menu) {

        this.menu = menu;
    }

    public void iniciar() {

        thread = new Thread(this);
        thread.start();     
    }

    public void run() {

        corriendo = true;

        while ( corriendo ) {

            long ini = System.currentTimeMillis();
            try {
                if(this.juego!=null){
                juego.actualizar();
                }
            else{
             menu.actualizar();
             }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            if(this.juego!=null){
                juego.dibujar();
            }
            else{
            menu.dibujar();
            }
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
