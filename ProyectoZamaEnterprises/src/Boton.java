
import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//prueba
/**
 *
 * @author mi16
 */
public class Boton {

     private Image imageSelected;
     private Image imageUnselected;
     private boolean isSelected;
     static final int DEF_X = 10;
     private int x, y;

     //Recibe como argumento la coordenada Y, y las direcciones de las imágenes del botón para cuando está seleccionado y para cuando no.
     public Boton(String fileSelected, String fileUnselected) throws IOException{
        this.imageSelected = Image.createImage(fileSelected);
        this.imageUnselected = Image.createImage(fileUnselected);
        this.x = Boton.DEF_X;
        this.isSelected = false;
     }

      public void dibujar(Graphics g) {
          if( isSelected )
            g.drawImage(imageSelected, x, y, Graphics.LEFT|Graphics.TOP);
          else
              g.drawImage(imageUnselected, x, y, Graphics.LEFT|Graphics.TOP);
      }

      public void setX(int x){
          this.x = x;
      }

      public void setY(int y){
          this.y = y;
      }

      
    void switchImage() {
        isSelected = !isSelected;
        if( isSelected )
            this.setX(60);
        else
            this.setX(DEF_X);
    }

}
