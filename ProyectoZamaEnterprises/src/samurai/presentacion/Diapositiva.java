package samurai.presentacion;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;
import samurai.juego.Global;

/**
 * Clase encargada de crear una diapositiva para usarce en una presentacion
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */


public class Diapositiva implements Animable{
    private Image titulo;
    private Image imagenRelativa;
    private int tituloX, imagenX;
    private final int tituloY,imagenY;
    private Vector textos;
    private final int SALTO=18;
    private int Ystring, Xstring, Xcuadro;
    private Image imagenCuadro;
    /**
     * Enum de credito
     */
    public static final int CREDITO = 0;
    /**
     * Enum de tutorial
     */
    public static final int TUTORIAL = 1;
    /**
     * Enum de prologo
     */
    public static final int PROLOGO = 2;
    /**
     * Enum de epilogo
     */
    public static final int EPILOGO = 3;
    


    /**
     * Constructor que inicializa variables
     * @param rutaNombre direccion de la imagen para el titulo
     * @param rutaImagen direccion para la imagen representativa
     * @param textos vector que contiene los textos a ser usados
     */
    public Diapositiva(String rutaNombre, String rutaImagen, Vector textos){
        try {
            this.titulo = Image.createImage(rutaNombre);
            this.imagenRelativa = Image.createImage(rutaImagen);
            this.imagenCuadro = Image.createImage("/samurai/imagenes/creditos/Cuadro.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.tituloX = Global.ANCHO_PANTALLA+titulo.getWidth()/2;
        this.tituloY = (titulo.getHeight()*2)/3;
        this.imagenX = -(imagenRelativa.getWidth());
        this.imagenY = Global.ALTO_PANTALLA/2;
        this.textos=textos;
        this.Ystring=this.imagenY+this.imagenRelativa.getHeight()-20;
        this.Xstring = Global.ANCHO_PANTALLA;
        this.Xcuadro = -(this.imagenCuadro.getWidth());
    }

    public void dibujar(Graphics g) {
      
            g.drawImage(titulo, this.tituloX, tituloY, Graphics.HCENTER|Graphics.VCENTER);
            g.drawImage(imagenRelativa, this.imagenX, imagenY, Graphics.HCENTER|Graphics.VCENTER);
            g.drawImage(imagenCuadro, this.Xcuadro, Ystring-10, Graphics.LEFT | Graphics.TOP);
            g.setColor(0xFFFFFF);
            int tamañoVec=textos.size();
            for(int i=0; i<tamañoVec;i++){
            g.drawString((String)(textos.elementAt(i)),10+this.Xstring,Ystring , Graphics.LEFT|Graphics.TOP);
            this.Ystring +=this.SALTO;
            }
            this.Ystring=this.imagenY+this.imagenRelativa.getHeight()-20;
    }

    /**
     * Cambia la x del titulo, la imagen y el texto
     */
    public void cambiarX(){
        this.tituloX-=4;
       
        this.imagenX +=4;
         this.Xstring-=4;
         this.Xcuadro+=4;
    }


    /**
     * actualiza el estado del titulo, la imagen y el texto
     */
    public void actualizar(){
        this.cambiarX();
    }

    /**
     * regresa la posicion x del titulo
     * @return la posicion x del titulo
     */
    public int posicionXImagen(){
        return this.tituloX;
    }
    /**
     * regresa si se esta mostrando
     * @return si se esta mostrando
     */
    public boolean estoyMostrandome(){
        if(this.Xcuadro<=Global.ANCHO_PANTALLA){
            return true;
        }
        return false;
    }
}