package samurai.menu;



import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Boton {

     private Image image;
     private boolean isSelected;
     static final int DEF_X = 10;
     private int x, y;

     //Recibe como argumento el nombre del archivo de la imagen
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
      //Cambia la x donde se dibuja el boton cuando este esta seleccionado
    void switchImage() {
        isSelected = !isSelected;
        if( isSelected )
            this.setX(60);
        else
            this.setX(DEF_X);
    }

}
