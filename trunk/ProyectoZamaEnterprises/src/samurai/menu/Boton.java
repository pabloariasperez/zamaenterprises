package samurai.menu;



import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.escenarios.Posicion;

public class Boton {

     private Image image;
     private Posicion posicion;

     //Recibe como argumento el nombre del archivo de la imagen
     public Boton(String file) throws IOException{
        this.image = Image.createImage(file);
        this.posicion = new Posicion( 0, 0 );
     }

     //Dibuja el botón con las coordenadas e imagen actuales.
      public void dibujar(Graphics g) {
            g.drawImage(this.image, this.posicion.getX(), this.posicion.getY(), Graphics.LEFT|Graphics.TOP);
      }

      //SETTERS
      public void setX(int x){
          this.posicion.setX(x);
      }

      public void setY(int y){
          this.posicion.setY(y);
      }

      //GETTERS
      public int getX(){
          return this.posicion.getX();
      }

      public int getY(){
          return this.posicion.getY();
      }

      public int getAlto(){
          return this.image.getHeight();
      }
}