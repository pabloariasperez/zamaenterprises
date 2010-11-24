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
    /**
     *Valor de estado del GeneradorEje cuando no está trabajando.
     */
    public static int PARADO = 0;
    /**
     *Valor de estado del GeneradorEje cuando está generando un nuevo eje.
     */
    public static int GENERANDO = 1;
    /**
     *Valor de estado del GeneradorEje cuando se ha terminado de calcular un nuevo eje.
     */
    public static int TERMINADO = 2;
    /**
     *Tiempo que indica cuánto tiempo debe dormirse el thread para no dejar que sature el procesador consigo mismo.
     */
    public static int TIEMPO_DORMIDO = 1;
    /**
     *Indica el número de bloques de operaciones que debe de hacer para que se mande dormir el thread.
     */
    public static int OPERACIONES_PARA_DORMIR = 30;
    private int anchoInicial;
    private int porcentajeAnchoFinal;
    private int altoLinea;
    private int altoFondo;
    private int parametro;
    private int margenIzquierdo;
    private int numeroLineas;
    private int[][] posiciones;

    /**
     *
     * @param anchoInicial
     * @param porcentajeAnchoFinal
     * @param altoLinea
     * @param altoFondo
     */
    public GeneradorEje(int anchoInicial, int porcentajeAnchoFinal, int altoLinea, int altoFondo) {
        this.anchoInicial = anchoInicial;
        this.porcentajeAnchoFinal = porcentajeAnchoFinal;
        this.altoLinea = altoLinea;
        this.altoFondo = altoFondo;

        margenIzquierdo = (Global.ANCHO_PANTALLA - anchoInicial) / 2;

        estado = GeneradorEje.PARADO;
    }

    /**
     *Comenzará a generar el nuevo eje. Es mandado llamar por el Thread.
     */
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

    /**
     *Recibe la petición de crear un nuevo eje. Es quien arranca el thread.
     * @param parametro Indica el grado de curvatura del nuevo eje. 0 implica una recta, valores negativos indican curvatura hacia la izquierda, y valores positivos indican una curvatura a la derecha.
     */
    public void generarEje(int parametro) {
        this.parametro = parametro;
        hiloSegundoPlano = new Thread(this);
        estado = GeneradorEje.GENERANDO;
        hiloSegundoPlano.start();
    }

    /**
     *Devuelve el nuevo eje generado solamente si ya se ha terminado de generar. Devolverlo sino está terminado podría implicar un arreglo de enteros incompletos.
     * @return Devuelve el nuevo arreglo de posiciones si ya fue generado correctamente o se devuelve null si aún no se ha terminado de generar.
     */
    public int[][] getEje() {
        if (estado == GeneradorEje.TERMINADO) {
            estado = GeneradorEje.PARADO;
            return posiciones;
        } else {
            return null;
        }
    }

    /**
     *Devuelve el número de líneas que contiene el arreglo de posiciones. Este varía por los parámetros de construcción: alto pantalla, alto fondo, alto línea.
     * @return Devuelve un entero con el número de líneas en el arreglo.
     */
    public int getNumeroLineas() {
        return numeroLineas;
    }

    /**
     *Devuelve el estado actual del Thread GeneradorEje.
     * @return Devuelve alguno de los estado actual del GeneradorEje.
     */
    public int getEstado() {
        return estado;
    }

    /**
     *Si se desea terminar con el Thread, se llama a esta función para interrumpir.
     */
    public void pararGenerador() {
        hiloSegundoPlano.interrupt();
        estado = GeneradorEje.PARADO;
    }

    public void sleep(int tiempoADormir) {
        try {
            hiloSegundoPlano.sleep((long) tiempoADormir);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
