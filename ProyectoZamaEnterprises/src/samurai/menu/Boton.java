package samurai.menu;



import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Boton {

     private Image image;
     private boolean isSelected;
     static final int DEF_X = 10;
     private int x, y;

     //Recibe como argumento la coordenada Y, y las direcciones de las imágenes del botón para cuando está seleccionado y para cuando no.
     public Boton(String file) throws IOException{
        this.image = Image.createImage(file);
        this.x = Boton.DEF_X;
        this.isSelected = false;
     }

      public void dibujar(Graphics g) {
          if( isSelected )
            g.drawImage(this.image, x, y, Graphics.LEFT|Graphics.TOP);
          else
              g.drawImage(image, x, y, Graphics.LEFT|Graphics.TOP);
      }

      public void setX(int x){
          this.x = x;
      }

      public void setY(int y){
          this.y = y;
      }
      public int getX(){
          return this.x;
      }
      
    void switchImage() {
        isSelected = !isSelected;
        if( isSelected )
            this.setX(60);
        else
            this.setX(DEF_X);
    }

}
