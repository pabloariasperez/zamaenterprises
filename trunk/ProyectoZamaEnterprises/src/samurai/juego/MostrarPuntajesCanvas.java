package samurai.juego;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import samurai.almacenamiento.AdministradorData;

/**
 * Clase encargada de mostrar los puntajes altos
 * @author Pablo, Erik, Daniel
 * @version 1.0 Noviembre 2010
 */
class MostrarPuntajesCanvas extends GameCanvas implements Actualizable {

    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private ManejadorTeclado teclado;
    private Vector iniciales;
    private int[] score;
    private Sprite letras;
    private Sprite titulo;
    private Animador animador;
    private final int INDEX_NUMERO = 28;
    private final int SUSTRAENDO_LETRA = 65;
    private final int X_TEXTO = 20;
    private final int X_NUMEROS =40;
    private final int Y_INICIAL;
    private int y;
    private boolean dibujado = false;

    public MostrarPuntajesCanvas(SamuraiEnterprises samuraiMidlet) {
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        this.g = this.getGraphics();
        teclado = new ManejadorTeclado(this);
        Y_INICIAL=(Global.ALTO_PANTALLA/Global.NUMERO_PUNTAJES_ALMACENADOS);
        y = Y_INICIAL;
        try {
            Image imagen = Image.createImage("/samurai/imagenes/letrasScore.png");
            letras = new Sprite(imagen, imagen.getWidth() / 7, imagen.getHeight() / 6);
            titulo= new Sprite(Image.createImage("/samurai/imagenes/titulos/tituloPuntajes.png"),200, 40);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        titulo.setPosition(20, 20);
        iniciales = new Vector();
        score = new int[6];

        animador = new Animador(this);
        animador.iniciar();
    }
    /**
     * actualiza
     */
    public void actualizar() {
        if (teclado.firePresionado()) {
            samuraiMidlet.mostrarMenu();
        }
    }
    /**
     * dibuja los scores altos
     */
    public void dibujar() {
        if (!dibujado) {
            g.setColor(0x000000);
            g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
            titulo.paint(g);
            for (int i = 1; i <= Global.NUMERO_PUNTAJES_ALMACENADOS; i++) {
                this.nombre(i);
                this.score(i);
                for (int j = 0; j < this.iniciales.size(); j++) {
                    this.letras.setPosition(X_TEXTO + (15 * j), y);
                    this.letras.setFrame(((Integer) (this.iniciales.elementAt(j))).intValue() - SUSTRAENDO_LETRA);
                    this.letras.paint(g);
                }
                this.iniciales.removeAllElements();
                for (int k = 0; k< score.length; k++) {
                    this.letras.setPosition( Global.ANCHO_PANTALLA - X_NUMEROS - (15 *k), y);
                    this.letras.setFrame(this.INDEX_NUMERO + this.score[k]);
                    this.letras.paint(g);
                }
                y += 50;
            }
            flushGraphics();
        }
        dibujado = true;
    }
    /**
     * regresa el tipo de canvas
     * @return tipo de canvas
     */
    public String tipoCanvas() {
        return Actualizable.MUESTRA_PUNTAJES;
    }
    /**
     * Destructor: interrumpe el animador y nulifica los objetos
     */
    public void destruir() {
        animador.interrumpir();
        animador=null;

        samuraiMidlet=null;
        g=null;
        teclado=null;
        iniciales=null;
        score=null;
        letras=null;
        titulo=null;

    }
    /**
     * interrumpe el animador
     */
    public void interrumpir(){
        animador.interrumpir();
    }
    /**
     * inicia el animador
     */
    public void correr() {
        if( !animador.estaCorriendo() ){
            animador.iniciar();
        }
    }

    private void nombre(int num) {
        AdministradorData inicial = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + num);
        String nombre = inicial.regresarDato(AdministradorData.REGISTRO_INICIALES);
        for (int i = 0; i < nombre.length(); i++) {
            this.iniciales.addElement(new Integer(nombre.charAt(i)));
        }
    }

    private void score(int num) {
        AdministradorData inicial = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + num);
        int numero = inicial.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE);
        for (int i = 0; i <= Global.DIGITOS_PUNTAJES-1; i++) {
            this.score[i] = numero % 10;
            numero = numero / 10;
            System.out.println(score[i]);
        }
    }
}
