package samurai.juego;


import samurai.juego.MenuCanvas;
import samurai.juego.Juego;
import java.lang.Thread;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;
import samurai.escenarios.Real;
import samurai.menu.Logo;

public class SamuraiEnterprises extends MIDlet {

    private Juego juego;
    private MenuCanvas menu;

    public SamuraiEnterprises() {

        menu = new MenuCanvas(this);

       
    }

    public void switchDisplay(){
        if(Display.getDisplay(this).getCurrent() == menu){
            juego = new Juego(this);
            Display.getDisplay(this).setCurrent(juego);
            this.menu=null;
            System.gc();
            

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