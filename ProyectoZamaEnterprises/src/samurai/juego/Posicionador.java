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

    /**
     *Vector que almacena números enteros referentes al ancho en pixeles de cada una de las regiones de camino.
     */
    public int posiciones[][];

    /**
     *
     * @param anchoInicial Cantidad en pixeles relacionada con el ancho en pixeles que debe de tener el camino desde la base de la pantalla.
     * @param porcentajeAnchoFinal Cantidad entre 0 y 100, refiriéndose a un porcentaje, que dice a qué porcentaje del ancho inicial debe de estar el ancho final para dar efecto de profundidad.
     * @param altoLinea Cantidad en pixeles que dice qué tan alto debe ser cada una de las líneas que forman el camino. La más alta resolución se logra con valor 1, pero demanda mayor procesador.
     * @param altoFondo Cantidad en pixeles que dice qué tan alto es el fondo del escenario, esto ayuda a delimitar el cálculo de la curva de camino en un rango absoluto de entre 0 y el alto de pantalla menos el alto del fondo.
     */
    public Posicionador( int anchoInicial, int porcentajeAnchoFinal, int altoLinea, int altoFondo ){
        this.anchoInicial = anchoInicial;
        this.porcentajeAnchoFinal = porcentajeAnchoFinal;
        this.altoLinea = altoLinea;
        this.altoFondo = altoFondo;

        generadorEje = new GeneradorEje(anchoInicial, porcentajeAnchoFinal, altoLinea, altoFondo);
        posiciones = new int[3][];
    }

    /**
     *Invoca al GeneradorEje que se ejectua en un nuevo thread. Con esto se solicita que se comience a generar un nuevo eje para posicionar los elementos.
     * @param nuevoParametro El parametro define el grado de curvatura del camino, abarcando todos los números enteros. El rango ideal es entre -150 a 150. Los números menores que cero generan curva a la izquierda, los mayores que cero generan curva a la derecha. Si el parametro vale cero se genera una recta.
     */
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

    /**
     *Método que pregunta si el GeneradorEje ya ha terminado de constriuir un nuevo eje. Sí ya hay un nuevo eje es asignado al vector de posiciones de la clase Posicionador, de lo contrario no hace nada. Con este método se evita el uso de información aún no sintetizada completamente.
     * @return Devuelve si hay nuevo eje o no.
     */
    public boolean hayNuevoEje(){
        if( generadorEje.getEstado() == GeneradorEje.TERMINADO ){
            posiciones = generadorEje.getEje();
            return true;
        }else{
            return false;
        }
    }

    /**
     *Se encarga de la lógica de dibujar el camino según las posiciones que se guardan.
     * @param g Recibe dónde se dibujará.
     */
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

    /**
     *Asigna al objeto posición, pasado como argumento, los valores X y Y que le corresponden según su altura relativa actual. Haciendo usao del centesimal el valor regresado abarca el porcentaje dado por el centesimal en la región dada.
     * @param posicion Objeto que será reasignado en sus valores. Contiene los pixeles finales para colocar en pantalla.
     * @param altura Altura relativa del objeto posicion que se recibe.
     * @param centesimal Indica un porcentaje entre 0 - 100 que se debe abarcar de la región dada.
     * @param region Indica en qué región se posicionará.
     */
    public void getPorcion( Posicion posicion, int altura, int centesimal, int region){
        posicion.setY((int)((float)((posiciones.length-1)*(altura)/(float)(Global.ALTO_PANTALLA - altoFondo))) );

        posicion.setX((posiciones[posicion.getY()][region]*centesimal)/100);
        while(region > 0){
            region--;
            posicion.setX(posicion.getX() + posiciones[posicion.getY()][region]);
        }
    }

    /**
     *
     * @param tiempoADormir
     */
    public void sleep(int tiempoADormir) {
         generadorEje.sleep(tiempoADormir);
    }

    /**
     *
     * @return
     */
    public int getAnchoFinal(){
        return (anchoInicial*porcentajeAnchoFinal)/100;
    }
}