package samurai.escenarios;

import javax.microedition.lcdui.game.GameCanvas;
import samurai.juego.Global;

/******NOTAS DE USO******
 * Es importante que al usar cada una de las funcioens de posicionamiento se le agregue el margen izquierdo.
 */


public class Posicionador {
    //Desarrollamos el posicionador para casos recta
    public static int recta( int y, float pendiente){         //Parámetro marca que tan marcada es la pendiente de la recta
        //Mi súper mega función patentada por la UNAM, corolario 1.
        return Posicionador.redondearEntero( pendiente * (Global.ALTO_PANTALLA - y) / Global.ALTO_PANTALLA );
    }

    //Desarrollamos un posicionador en función de mla reducción del camino
    public static int incrementoAncho( int y, int anchoInicial, int porcentajeAnchoFinal, int alturaFondo){
        //Mi súper mega función patentada por el IPN. Sin corolarios.
        return Posicionador.redondearEntero( (float)anchoInicial*((float)1-(((float)100-(float)porcentajeAnchoFinal)/(float)100)*((float)(Global.ALTO_PANTALLA-y)/(float)(Global.ALTO_PANTALLA-alturaFondo))) );
    }

    //Función para redondear enteros
    public static int redondearEntero( float numeroReal ){
        numeroReal *= 10;       //Multiplicamos nuestro número por 10 para obtener a favor un decimal.
        return (int) (numeroReal-(numeroReal%10))/10;         //Aplicamos el algoritmo de la división para obtener el puro entero de división. Hay riesgo de perder precisión.;
    }

    
}
