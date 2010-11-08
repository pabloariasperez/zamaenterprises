/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import javax.microedition.lcdui.Graphics;
import samurai.escenarios.Posicion;

/**
 *
 * @author Pablo
 */
public class Posicionador {
    private GeneradorEje generadorEje;
    private int anchoInicial;
    private int porcentajeAnchoFinal;
    private int altoLinea;
    private int altoFondo;

    private int colorCamino;

    public int posiciones[][];

    public Posicionador( int anchoInicial, int porcentajeAnchoFinal, int altoLinea, int altoFondo ){
        this.anchoInicial = anchoInicial;
        this.porcentajeAnchoFinal = porcentajeAnchoFinal;
        this.altoLinea = altoLinea;
        this.altoFondo = altoFondo;

        generadorEje = new GeneradorEje(anchoInicial, porcentajeAnchoFinal, altoLinea, altoFondo);
        posiciones = new int[3][];
    }

    public void generarNuevoEje( int nuevoParametro ){
        if( generadorEje.getEstado() == GeneradorEje.PARADO ){
            generadorEje.generarEje(nuevoParametro);
        }else if( generadorEje.getEstado() == GeneradorEje.GENERANDO ){
            generadorEje.pararGenerador();
            generadorEje.generarEje(nuevoParametro);
        }else{
            System.out.println("Estoy terminado.");
        }
    }

    public boolean hayNuevoEje(){
        if( generadorEje.getEstado() == GeneradorEje.TERMINADO ){
            posiciones = generadorEje.getEje();
            return true;
        }else{
            return false;
        }
    }

    public void dibujarCamino(Graphics g){
        colorCamino = 0x00900B00;

        for(  int lineaActual=0; lineaActual<posiciones.length; lineaActual++ ){
            g.setColor(colorCamino);
            g.fillRect(
                    posiciones[lineaActual][0],
                    lineaActual*altoLinea+altoFondo,
                    posiciones[lineaActual][1],
                    altoLinea
                     );
            colorCamino += 0x00000100;
        }
    }

    public void getPorcion( Posicion posicion, int altura, int centesimal, int region){
        posicion.setY((int)((float)((posiciones.length-1)*(altura)/(float)(Global.ALTO_PANTALLA - altoFondo))) );

        //Evitar que se desborde
        if( posicion.getY() > posiciones.length - 1){
            posicion.setY(0);
        }
        posicion.setX((posiciones[posicion.getY()][region]*centesimal)/100);
        while(region > 0){
            region--;
            posicion.setX(posicion.getX() + posiciones[posicion.getY()][region]);
        }
    }
}