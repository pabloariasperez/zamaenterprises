package samurai.menu;


import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Logo
{
    private Image imagen;
    private int x, y;
    private boolean inicio = true;

    public Logo(String archivo, int x, int y) throws IOException {

        imagen = Image.createImage(archivo);
        this.x = x;
        this.y = y;

    }

    public void actualizar() throws InterruptedException {

        if(inicio){

        Thread.sleep(3000);
        x= x-3000;
        this.inicio = false;
        

        }
       
    }

    public void dibujar(Graphics g) {

        
        g.drawImage(imagen, x, y, Graphics.LEFT|Graphics.TOP);
        
    }
}
