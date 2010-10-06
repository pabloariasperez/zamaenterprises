
import java.lang.Thread;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class AppAnimacion extends MIDlet {

    private Juego juego;
    private MenuCanvas menu;

    public AppAnimacion() {

        
        menu = new MenuCanvas(this);

    }

    public void switchDisplay(){
        if(Display.getDisplay(this).getCurrent() == menu){
            juego = new Juego(this);
            Display.getDisplay(this).setCurrent(juego);
            

        }
        else{
            
            Display.getDisplay(this).setCurrent(menu);
           
        }
    }
    public void startApp() {
        Display.getDisplay(this).setCurrent(menu);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
       
    }
}
