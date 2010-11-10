/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;
import samurai.juego.Juego;

/**
  Se encarga de colocar la ambientación y de dibujar el camino
 * @author Pablo, Erik, Daniel
 * @version 1.3 Noviebre 2010
 */
public class Escenario {
 
    private ManejadorFondos manejadorFondos;

    private int alturaFondo;
    private Sprite piedra;
    private int incremento;
    private int velocidad;
    private int estadoYActual;

    /**
     * la distancia que hay entre cada piedra
     */
    public int DISTANCIADOR_PIEDRAS = 8;

    /**
     * Constructor: no tiene argumentos porque cada uno de sus elementos será alimentado por otros métodos.
     */
    public Escenario(){
        //Inicializamos cada uno de los atributos.
        this.manejadorFondos = new ManejadorFondos();
        velocidad = 3;
        estadoYActual = 0;
        incremento = 0;
        try {
            piedra = new Sprite(Image.createImage("/samurai/imagenes/piedra.png"), 20, 18);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Se accede indirectamente al manejador de fondos del escenario. Se alimenta con la información del atributo.
     * @param fondo : fondo a agregar al escenario
     */
    public void agregarFondo(FondoCapa fondo){
        this.manejadorFondos.agregarFondo(fondo);
    }
    /**
     * Se dibujan los fondos
     * @param g Graficos
     */
    public void dibujarFondos(Graphics g){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.dibujar(g);
        }
    }

    /**
     * regresa el alto de los fondos
     * @return alto de los fondos
     */
    public int getAltoFondos() {
        return manejadorFondos.getAlto();
    }
    /**
     * Se dibuja el escenario.
     * Se dibuja el camino
     * @param g Graficos
     */
    public void dibujar(Graphics g){
        this.dibujarBackground(g);
        Juego.getPosicionador().dibujarCamino(g);
        this.dibujarFondos(g);
        this.dibujarPiedras(g);
    }

    private void dibujarPiedras(Graphics g){
        int correccionDesfaseX = piedra.getWidth()/3;
        int correccionDesfaseY = piedra.getWidth()/2;
        int posiciones[][] = Juego.getPosicionador().posiciones;
        int x1;
        for(int lineaActual = 0; lineaActual<posiciones.length && lineaActual+incremento<posiciones.length; lineaActual+= DISTANCIADOR_PIEDRAS){
            x1 =posiciones[lineaActual + incremento ][0] - correccionDesfaseX*2;
            piedra.setPosition(     x1,
                                    lineaActual*Juego.ALTO_LINEA + incremento*Juego.ALTO_LINEA + Juego.altoFondo - correccionDesfaseY);
            piedra.paint(g);
            
            piedra.setPosition(     x1 + posiciones[lineaActual + incremento ][1],
                                    lineaActual*Juego.ALTO_LINEA + incremento*Juego.ALTO_LINEA + Juego.altoFondo - correccionDesfaseY);
            piedra.paint(g);
        }
    }
    
    /**
     * Actualiza el escenario
     */
    public void actualizar(){
        estadoYActual++;
        if( estadoYActual == velocidad){
            estadoYActual = 0;
            incremento++;
            if( incremento == DISTANCIADOR_PIEDRAS - 1){
                incremento = 0;
            }
        }
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }
    }

    private void dibujarBackground(Graphics g) {
        g.setColor(0x00002200);
        g.fillRect(0, Juego.altoFondo, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA*1/10);
        g.setColor(0x00003300);
        g.fillRect(0, Juego.altoFondo + Global.ALTO_PANTALLA*1/10, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA*1/10);
        g.setColor(0x00004400);
        g.fillRect(0, Juego.altoFondo + Global.ALTO_PANTALLA*2/10, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA*2/10);
        g.setColor(0x00006600);
        g.fillRect(0, Juego.altoFondo + Global.ALTO_PANTALLA*4/10, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA*2/10);
        g.setColor(0x00008800);
        g.fillRect(0, Juego.altoFondo + Global.ALTO_PANTALLA*6/10, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA*4/10);
    }
}
