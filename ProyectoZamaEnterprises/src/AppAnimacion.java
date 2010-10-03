
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class AppAnimacion extends MIDlet {

    private Juego juego;
    private MenuCanvas menu;

    public AppAnimacion() {

        juego = new Juego(this);
        menu = new MenuCanvas(this);
    }
    public void startApp() {
        Display.getDisplay(this).setCurrent(menu);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
}
