package samurai.menu;



import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.escenarios.Posicion;
import samurai.juego.Global;

/**
 * Clase encargada de la imagen del boton y su posicion
 * @author Pablo, Erik, Daniel
 * @version  1.0, Septiembre 2010
 */
public class Boton {

     private Image image;
     private Posicion posicion;

     /**
      * Recibe como argumento el nombre del archivo de la imagen
      * @param file nombre del archivo de la imagen del boton
      * @throws IOException cuando no se encuentra la imagen
      */
     public Boton(String file) throws IOException{
        this.image = Image.createImage(file);
       this.image = Global.resizeImage(image);
        this.posicion = new Posicion( 0, 0 );
     }

     /**
      *Dibuja el botón con las coordenadas e imagen actuales.
      * @param g Graficos donde se dibuja
      */
     public void dibujar(Graphics g) {
            g.drawImage(this.image, this.posicion.getX(), this.posicion.getY(), Graphics.LEFT|Graphics.TOP);
      }

      /**
       * cambia el paramentro x de la posicion
       * @param x nueva x
       */
      public void setX(int x){
          this.posicion.setX(x);
      }

      /**
       * cambia el parametro y de la posicion
       * @param y nueva y
       */
      public void setY(int y){
          this.posicion.setY(y);
      }

      /**
       * regresa el parametro x de la posicion
       * @return regresa el parametro x de la posicion
       */
      public int getX(){
          return this.posicion.getX();
      }

      /**
       * regresa el parametro y de la posicion
       * @return regresa el parametro y de la posicion
       */
      public int getY(){
          return this.posicion.getY();
      }

      /**
       * regresa el alto de la imagen del boton
       * @return regresa el alto de la imagen del boton
       */
      public int getAlto(){
          return this.image.getHeight();
      }

      
}