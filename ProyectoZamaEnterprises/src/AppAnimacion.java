
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class AppAnimacion extends MIDlet {

    private Juego juego;

    public AppAnimacion() {

        juego = new Juego(this);
    }
    public void startApp() {
        Display.getDisplay(this).setCurrent(juego);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}