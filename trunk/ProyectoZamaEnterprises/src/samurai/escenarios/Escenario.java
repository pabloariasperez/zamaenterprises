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
    private Image fondoCamino;
    private int incremento;
    private int velocidad;
    private int estadoYActual;

    /**
     * la distancia que hay entre cada piedra
     */
    public int DISTANCIADOR_PIEDRAS = 4;
    private int correccionDesfaseXPiedra;
    private int correccionDesfaseYPiedra;
    private int razonCambioPiedra;

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
            fondoCamino = Image.createImage("/samurai/imagenes/fondoCamino.png");
            piedra = new Sprite(Image.createImage("/samurai/imagenes/spritePiedra.png"), 20, 20);
            piedra.setFrameSequence(new int[]{0,1,2,3});
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Cositas de las piedras
        correccionDesfaseXPiedra = piedra.getWidth()/2;
        correccionDesfaseYPiedra = piedra.getWidth()/2;
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
        this.dibujarFondoCamino(g);
        Juego.getPosicionador().dibujarCamino(g);
        this.dibujarFondos(g);
        this.dibujarPiedras(g);
    }

    private void dibujarPiedras(Graphics g){
        int posiciones[][] = Juego.getPosicionador().posiciones;
        int x1;
        piedra.setFrame(0);
        for(int lineaActual = 0, razonCambio = 0; lineaActual<posiciones.length && lineaActual+incremento<posiciones.length; lineaActual+= DISTANCIADOR_PIEDRAS){
            x1 =posiciones[lineaActual + incremento ][0] - correccionDesfaseXPiedra;
            piedra.setPosition(     x1,
                                    lineaActual*Juego.ALTO_LINEA + incremento*Juego.ALTO_LINEA + Juego.altoFondo - correccionDesfaseYPiedra);
            piedra.paint(g);
            
            piedra.setPosition(     x1 + posiciones[lineaActual + incremento ][1],
                                    lineaActual*Juego.ALTO_LINEA + incremento*Juego.ALTO_LINEA + Juego.altoFondo - correccionDesfaseYPiedra);
            piedra.paint(g);

            if( lineaActual >= razonCambioPiedra*(razonCambio+1) ){
                piedra.nextFrame();
                razonCambio++;
            }
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

    /**
     * Dibuja el fondo del camino
     * @param g Graficos
     */
    private void dibujarFondoCamino(Graphics g) {
        g.setColor(0x0000AA00);
        g.fillRect(0, Juego.altoFondo, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA - Juego.altoFondo);
        g.drawImage(fondoCamino, 0, Juego.altoFondo, Graphics.LEFT|Graphics.TOP);
    }

    public void setRazonCambioPiedra(int longitudPosiciones){
        //Este número representa el número de puntos que deberán ser representados por CADA frame de la piedra.
        // Al total de mis posiciones lo divido entre la distancia que deben guardar entre ellos, y luego entre el número de frames en piedra.
        razonCambioPiedra = longitudPosiciones/piedra.getFrameSequenceLength()/(DISTANCIADOR_PIEDRAS-1);
    }
}
