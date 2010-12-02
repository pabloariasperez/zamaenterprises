package samurai.juego;

import java.util.Stack;
import java.util.Vector;
import samurai.presentacion.Timor;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.presentacion.Diapositiva;

/**
 * Maneja las diapositivas
 * @author Pablo, Erik, Daniel
 * @version 1.1
 */
public class PresentacionCanvas extends GameCanvas implements Actualizable {
    private SamuraiEnterprises samuraiMidlet;
    private Stack diapositivas;
    private Graphics g;
    private Animador animador;
    private Timor timer;
    private Diapositiva diapositivaActual;
    private boolean pare;
    private boolean camine;
    private int tipo;
    private final int C_PABLO = 0;
    private final int C_ERIK = 1;
    private final int C_DANIEL = 2;

    private final int S_GAMEOVER = 3;
    private final int S_PROLOGO_1 = 4;
    
    private final int T_Izquierda = 5;
    private final int T_Derecha = 6;
    private final int T_Pausa = 7;
    private final int T_Adelante = 8;

    private final int TEXTO_NIVEL_1 = 9;
    private final int TEXTO_NIVEL_2 = 10;
    private final int TEXTO_NIVEL_3 = 11;

    private boolean mostrandome;
    private ManejadorTeclado teclado;
    /**
     * Enum de credito
     */
    public static final int CREDITO = 0;
    /**
     * Enum de tutorial
     */
    public static final int TUTORIAL = 1;
    /**
     * Enum de prologo
     */
    public static final int PROLOGO = 2;
    /**
     * Enum de epilogo
     */
    public static final int EPILOGO = 3;
    /**
     *
     */
    public static final int GAMEOVER = 4;
    
    public static final int NIVEL_1 = 5;
    public static final int NIVEL_2 = 6;
    public static final int NIVEL_3 = 7;

    /**
     * Constructor inicializa variables
     * @param samuraiMidlet midlet que usa
     * @param tipo
     */
    public PresentacionCanvas(SamuraiEnterprises samuraiMidlet, int tipo) {

        super(true);
        this.mostrandome = true;
        diapositivas = new Stack();
        g = this.getGraphics();
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        timer = new Timor(2);
        this.pare = false;
        this.camine = true;
        this.tipo = tipo;
        this.teclado = new ManejadorTeclado(this);


        this.agregarDiapositivas();
        diapositivaActual = (Diapositiva) this.diapositivas.pop();

        animador = new Animador(this);
        animador.iniciar();
    }

    private void agregarDiapositivas() {
        switch (this.tipo) {
            case PresentacionCanvas.CREDITO:
                Diapositiva creditoDaniel = new Diapositiva("/samurai/imagenes/creditos/Daniel.png", "/samurai/imagenes/creditos/feliz.png", agregarString(C_DANIEL));
                Diapositiva creditoErik = new Diapositiva("/samurai/imagenes/creditos/Erik.png", "/samurai/imagenes/creditos/rosa_negra.png", agregarString(C_ERIK));
                Diapositiva creditoPablo = new Diapositiva("/samurai/imagenes/creditos/Pablo.png", "/samurai/imagenes/creditos/pruebin.png", agregarString(C_PABLO));
                this.diapositivas.push(creditoDaniel);
                this.diapositivas.push(creditoErik);
                this.diapositivas.push(creditoPablo);
                break;
            case PresentacionCanvas.TUTORIAL:
                Diapositiva controlIzquierda = new Diapositiva("/samurai/imagenes/titulos/tituloTutorial.png", "/samurai/imagenes/tutorial/izquierda.png", agregarString(T_Izquierda));
                Diapositiva controlDerecha = new Diapositiva("/samurai/imagenes/titulos/tituloTutorial.png", "/samurai/imagenes/tutorial/derecha.png", agregarString(T_Derecha));
                Diapositiva controlAdelante = new Diapositiva("/samurai/imagenes/titulos/tituloTutorial.png", "/samurai/imagenes/tutorial/adelante.png", agregarString(T_Adelante));
                Diapositiva controlAbajo = new Diapositiva("/samurai/imagenes/titulos/tituloTutorial.png", "/samurai/imagenes/tutorial/pausa.png", agregarString(T_Pausa));
                this.diapositivas.push(controlIzquierda);
                this.diapositivas.push(controlDerecha);
                this.diapositivas.push(controlAdelante);
                this.diapositivas.push(controlAbajo);
                break;
            case PresentacionCanvas.PROLOGO:
                Diapositiva prologo = new Diapositiva("/samurai/imagenes/titulos/tituloPrologo.png", "/samurai/imagenes/imagenPrologo.png", agregarString(S_PROLOGO_1));
                this.diapositivas.push(prologo);
                break;
            case PresentacionCanvas.EPILOGO:
                Diapositiva epilogo = new Diapositiva("/samurai/imagenes/titulos/tituloEpilogo.png", "/samurai/imagenes/imagenEpilogo.png", agregarString(S_PROLOGO_1));
                this.diapositivas.push(epilogo);
                break;
            case PresentacionCanvas.GAMEOVER:
                Diapositiva gameOver = new Diapositiva("/samurai/imagenes/titulos/tituloGameOver.png", "/samurai/imagenes/gameOver.png", agregarString(S_GAMEOVER));
                this.diapositivas.push(gameOver);
                break;
            case PresentacionCanvas.NIVEL_1:
                Diapositiva nivel1 = new Diapositiva("/samurai/imagenes/titulos/tituloNivel1.png", "/samurai/imagenes/gameOver.png", agregarString(TEXTO_NIVEL_1));
                this.diapositivas.push(nivel1);
                break;
            case PresentacionCanvas.NIVEL_2:
                Diapositiva nivel2 = new Diapositiva("/samurai/imagenes/titulos/tituloNivel2.png", "/samurai/imagenes/trans2.png", agregarString(TEXTO_NIVEL_2));
                this.diapositivas.push(nivel2);
                break;
            case PresentacionCanvas.NIVEL_3:
                Diapositiva nivel3 = new Diapositiva("/samurai/imagenes/titulos/tituloNivel3.png", "/samurai/imagenes/gameOver.png", agregarString(TEXTO_NIVEL_3));
                this.diapositivas.push(nivel3);
                break;
            default:
                break;
        }
    }

    private Vector agregarString(int diapositiva) {
        Vector texto = new Vector();
        switch (diapositiva) {
            case C_PABLO:
                texto.addElement("     Tengo Chinitos");
                texto.addElement("     Meloooooon!!!");
                texto.addElement("     Osssshhhaaaa");

                return texto;
            case C_ERIK:
                texto.addElement("");
                texto.addElement("                 ...");
                texto.addElement("");
                return texto;
            case C_DANIEL:
                texto.addElement("Arriba el Veracruz");
                texto.addElement("Un ornitorrinco...!perry");
                texto.addElement("el ornitorrinco¡");
                return texto;
            case S_GAMEOVER:
                texto.addElement("Moriste");
                texto.addElement("  ...  ");
                return texto;
            case S_PROLOGO_1:
                texto.addElement("Ayuda a Sekai a");
                texto.addElement("acabar con los");
                texto.addElement("enemigos.");
                return texto;
            case T_Adelante:
                texto.addElement("Utilice el boton 2 para");
                texto.addElement("realizar un ataque");
                texto.addElement("frontal.");
                return texto;
            case T_Derecha:
                texto.addElement("Utilice el boton 6 para");
                texto.addElement("realizar un ataque por");
                texto.addElement("la derecha.");
                return texto;
            case T_Izquierda:
                texto.addElement("Utilice el boton 4 para");
                texto.addElement("realizar un ataque por");
                texto.addElement("la izquierda.");
                return texto;
            case T_Pausa:
                texto.addElement("Utilice el boton 8 para");
                texto.addElement("pausar el juego.");
                return texto;
            case TEXTO_NIVEL_1:
                texto.addElement("Ratas y murciélagos");
                texto.addElement("te embisten para");
                texto.addElement("detenerte.");
                return texto;
            case TEXTO_NIVEL_2:
                texto.addElement("De las tierras emergen");
                texto.addElement("topos, y en los aires");
                texto.addElement("aparecen fantasmas.");
                return texto;
            case TEXTO_NIVEL_3:
                texto.addElement("Sientes una mirada");
                texto.addElement("fría sobre tí, ¿será");
                texto.addElement("la muerte acechándote?");
                return texto;
            default:
                return texto;
        }
    }

    public void actualizar() {
        if (!diapositivaCentrada()) {
            this.diapositivaActual.actualizar();
            if (teclado.firePresionado()) {
                if (!this.diapositivas.isEmpty()) {
                    this.diapositivaActual = (Diapositiva) this.diapositivas.pop();
                    this.pare = false;
                    this.camine = true;
                } else {
                    cambiarPantalla(tipo);
                    return;
                }
            }
        } else {
            diapositivaActual.centrar();
            timer.tik();
            if (timer.activarIteracion()) {
                this.camine = false;
                this.pare = !this.pare;
            }
        }

        if (!this.diapositivaActual.estoyMostrandome()) {
            if (!this.diapositivas.isEmpty()) {
                this.diapositivaActual = (Diapositiva) this.diapositivas.pop();
                this.pare = false;
                this.camine = true;
            } else {
                cambiarPantalla(tipo);
            }
        }
    }

    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        this.diapositivaActual.dibujar(g);

        this.flushGraphics();
    }

    /**
     * regresa si se esta mostrando
     * @return mostrandome
     */
    public boolean estoyMostrandome() {
        return this.mostrandome;
    }

    private boolean diapositivaCentrada() {
        if (this.camine && (this.diapositivaActual.posicionXImagen() <= (Global.ANCHO_PANTALLA / 2)) && !(this.pare)) {
            this.pare = true;
            return true;
        } else if (this.pare) {
            return true;
        } else {
            return false;
        }

    }

    /**
     *
     * @return
     */
    public String tipoCanvas() {
        return Actualizable.PRESENTACION;
    }

    /**
     *
     */
    public void destruir() {
        animador.interrumpir();
        animador = null;

        samuraiMidlet = null;
        diapositivas = null;
        g = null;
        timer = null;
        diapositivaActual = null;
    }

    private void cambiarPantalla(int tipo) {
        switch (tipo) {
            case PresentacionCanvas.CREDITO:
                samuraiMidlet.mostrarMenu();
                break;
            case PresentacionCanvas.EPILOGO:
                samuraiMidlet.mostrarMenu();
                break;
            case PresentacionCanvas.GAMEOVER:
                samuraiMidlet.verificarNuevoPuntaje();
                break;
            case PresentacionCanvas.PROLOGO:
                samuraiMidlet.correrNivelUno();
                break;
            case PresentacionCanvas.TUTORIAL:
                samuraiMidlet.mostrarMenu();
                break;
            case PresentacionCanvas.NIVEL_1:
                samuraiMidlet.correrNivelUno();
                break;
            case PresentacionCanvas.NIVEL_2:
                samuraiMidlet.correrNivelDos();
                break;
            case PresentacionCanvas.NIVEL_3:
                samuraiMidlet.correrNivelTres();
                break;
            default:
                break;
        }
    }

    /**
     *
     */
    public void interrumpir() {
        animador.interrumpir();
    }

    /**
     *
     */
    public void correr() {
        if (!animador.estaCorriendo()) {
            animador.iniciar();
        }
    }
}
