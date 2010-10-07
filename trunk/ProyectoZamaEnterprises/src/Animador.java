
public class Animador implements Runnable
{
    private Juego juego;
    private MenuCanvas menu;
    private boolean corriendo;
    private Thread thread;
    long tiempoInicial,tiempoFinal;
    private final int FPS = 60;
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

            tiempoInicial = System.currentTimeMillis();
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
            tiempoFinal = System.currentTimeMillis();

            try {
                if ( RETARDO - (tiempoFinal-tiempoInicial) >= 0 )
                    Thread.sleep(RETARDO - (tiempoFinal-tiempoInicial));
            } catch (InterruptedException ex) { }
        }
    }

    public void terminar() {
        corriendo = false;
    }
}
