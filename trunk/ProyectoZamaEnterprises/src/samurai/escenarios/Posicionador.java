package samurai.escenarios;

import javax.microedition.lcdui.game.GameCanvas;
import samurai.juego.Coordenada;

/******NOTAS DE USO******
 * Es importante que al usar cada una de las funcioens de posicionamiento se le agregue el margen izquierdo.
 */


public class Posicionador {

    private final int  ALTO_PANTALLA;
    //private final int  ANCHO_PANTALLA;        Creo que no se necesita.

    //Constructor del método
    public Posicionador(GameCanvas gmCanvas){
        this.ALTO_PANTALLA = gmCanvas.getHeight();       //Obtenemos el ALTO de pantalla
        //this.ANCHO_PANTALLA = gmCanvas.getWidth();       //Obtenemos el ANCHO de pantalla         No es requerido por ahora.
    }

    //Desarrollamos el posicionador para casos exponenciales
    //Parámetro "parametro" marca que tanto se marca la curva exponencial.
    public int exponencial( Coordenada y, float parametro){
        //Mi súper mega función patentada por la UNAM.
        float soyExponente = ((float)ALTO_PANTALLA - (float)y.valor) / (float)ALTO_PANTALLA;
        Real soyReal = new Real( String.valueOf( soyExponente )) ;
        soyReal.exp();
        soyReal.mul(new Real( String.valueOf(parametro * ( ALTO_PANTALLA - y.valor ) / ALTO_PANTALLA)));

        return soyReal.toInteger();
    }

    //Desarrollamos el posicionador para casos recta
    public int recta( Coordenada y, float pendiente){         //Parámetro marca que tan marcada es la pendiente de la recta
        //Mi súper mega función patentada por la UNAM, corolario 1.
        return Posicionador.redondearEntero( pendiente * (ALTO_PANTALLA - y.valor) / ALTO_PANTALLA );
    }

    //Desarrollamos el posicionador para casos potenciales
    public int potencial( Coordenada y, float parametro, int exponente){
        //Mi súper mega función patentada por la UNAM, corolario 2.
        return Posicionador.redondearEntero( ( parametro * Posicionador.potencia( (double) (ALTO_PANTALLA - y.valor / ALTO_PANTALLA ) , exponente ) * ( ALTO_PANTALLA - y.valor ) / ALTO_PANTALLA ) );
    }

    //Desarrollamos un posicionador en función de mla reducción del camino
    public int incrementoAncho( Coordenada y, int anchoInicial, int porcentajeAnchoFinal, int alturaFondo){
        //Mi súper mega función patentada por el IPN. Sin corolarios.
        return Posicionador.redondearEntero( (float)anchoInicial*((float)1-(((float)100-(float)porcentajeAnchoFinal)/(float)100)*((float)(ALTO_PANTALLA-y.valor)/(float)(ALTO_PANTALLA-alturaFondo))) );
    }

    //Función para redondear enteros
    public static int redondearEntero( float numeroReal ){
        numeroReal *= 10;       //Multiplicamos nuestro número por 10 para obtener a favor un decimal.
        return (int) (numeroReal-(numeroReal%10))/10;         //Aplicamos el algoritmo de la división para obtener el puro entero de división. Hay riesgo de perder precisión.;
    }

    //Función potencia que cubrirá con las necesidades de las funciones de posicion
    private static float potencia( double base, int exponente){
        //Se pregunta si es positivo o negativo. Si es positivo se procede con el algoritmo común.
        if( exponente >= 0){
            float potencia = 1;         //Guardaremos el acumulado de la potencia. El que valga 1 es importante. { a^0 = 1 | a != 0 }
            for( int i = 0; i < exponente; i++){
                potencia *= base;       //Vamos multiplicando una tras otra.
            }
            return potencia;
        //Si es negativo entonces devolvemos 1 entre el valor obtenico con exponente positivo.
        }else{
            return 1 / potencia(base, (-1)*exponente);
        }
    }
}
