/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.presentacion;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import samurai.animacion.Animable;
import samurai.juego.Global;

/**
 *
 * @author mi16
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
    public static final int CREDITO = 0;
    public static final int TUTORIAL = 1;
    public static final int PROLOGO = 2;
    public static final int EPILOGO = 3;
    


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

    public void cambiarX(){
        this.tituloX-=4;
       
        this.imagenX +=4;
         this.Xstring-=4;
         this.Xcuadro+=4;
    }


    public void actualizar(){
        this.cambiarX();
    }

    public int posicionXImagen(){
        return this.tituloX;
    }
    public boolean estoyMostrandome(){
        if(this.Xcuadro<=Global.ANCHO_PANTALLA){
            return true;
        }
        return false;
    }
}
