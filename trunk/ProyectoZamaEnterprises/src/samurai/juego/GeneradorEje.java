/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.juego;

/**
 *
 * @author Pablo
 */
public class GeneradorEje implements Runnable {

    private Thread hiloSegundoPlano;
    private int estado;
    public static int PARADO = 0;
    public static int GENERANDO = 1;
    public static int TERMINADO = 2;
    public static int TIEMPO_DORMIDO = 1;
    public static int OPERACIONES_PARA_DORMIR = 30;
    private int anchoInicial;
    private int porcentajeAnchoFinal;
    private int altoLinea;
    private int altoFondo;
    private int parametro;
    private int margenIzquierdo;
    private int numeroLineas;
    private int[][] posiciones;

    public GeneradorEje(int anchoInicial, int porcentajeAnchoFinal, int altoLinea, int altoFondo) {
        this.anchoInicial = anchoInicial;
        this.porcentajeAnchoFinal = porcentajeAnchoFinal;
        this.altoLinea = altoLinea;
        this.altoFondo = altoFondo;

        margenIzquierdo = (Global.ANCHO_PANTALLA - anchoInicial) / 2;

        estado = GeneradorEje.PARADO;
    }

    public void run() {
        //Inicializo lo que requiera para el camino
        numeroLineas = (Global.ALTO_PANTALLA - altoFondo) / altoLinea;
        //Agregamos uno en caso de que haya residuo.
        if ((Global.ALTO_PANTALLA - altoFondo) - numeroLineas * altoLinea != 0) {
            numeroLineas++;
        }
        numeroLineas *= 3;

        posiciones = new int[numeroLineas][3];
        int denominador = Global.ALTO_PANTALLA - altoFondo;

        int numerador;
        int alturaActual = altoFondo;

        float parametroDividido = (float) parametro / (float) (denominador * denominador * denominador);
        int constantePrimerGrado = (int) ((anchoInicial * (100 - porcentajeAnchoFinal)) / 200);

        //Cosas necesarios para generar la segunda línea que se va decrementando según la distancia.
        float constanteParaIncremento = (float) (anchoInicial * (100 - porcentajeAnchoFinal)) / (float) (100);

        for (int lineaActual = 0; lineaActual < numeroLineas; lineaActual++) {
            alturaActual += altoLinea;
            numerador = Global.ALTO_PANTALLA - alturaActual;
            posiciones[lineaActual][0] = margenIzquierdo + (int) ((float) (constantePrimerGrado * numerador / denominador) + (float) (numerador * numerador * numerador) * parametroDividido);
            //(((anchoInicial*(100-porcentajeAnchoFinal))/200)*numerador/denominador + (float)parametro*((float)(numerador*numerador*numerador)/(float)(denominador*denominador*denominador)));
            //(int)((1-(float)(((float)(Global.ALTO_PANTALLA - alturaActual)/(float)(Global.ALTO_PANTALLA - altoFondo))*(float)(100 - porcentajeAnchoFinal)/(float)100))*(float)anchoInicial);


            posiciones[lineaActual][1] = anchoInicial - (int) ((constanteParaIncremento * numerador) / denominador);
            posiciones[lineaActual][2] = Global.ANCHO_PANTALLA - posiciones[lineaActual][0] - posiciones[lineaActual][1];

            //Dormimos nuestro Thread cada X operaciones por un Y tiempo.
            if (lineaActual % OPERACIONES_PARA_DORMIR == 0) {
                try {
                    hiloSegundoPlano.sleep(GeneradorEje.TIEMPO_DORMIDO);
                } catch (InterruptedException ex) {
                    System.out.println("Fui interrumpido");
                    ex.printStackTrace();
                }
            }
        }
        estado = GeneradorEje.TERMINADO;
        System.gc();
    }

    public void generarEje(int parametro) {
        this.parametro = parametro;
        hiloSegundoPlano = new Thread(this);
        estado = GeneradorEje.GENERANDO;
        hiloSegundoPlano.start();
    }

    public int[][] getEje() {
        if (estado == GeneradorEje.TERMINADO) {
            estado = GeneradorEje.PARADO;
            return posiciones;
        } else {
            return null;
        }
    }

    public int getNumeroLineas() {
        return numeroLineas;
    }

    public int getEstado() {
        return estado;
    }

    public void pararGenerador() {
        hiloSegundoPlano.interrupt();
    }
}
