package samurai.juego;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import samurai.almacenamiento.AdministradorData;
import samurai.almacenamiento.AlmacenamientoServidor;
import samurai.menu.Boton;
import samurai.menu.Menu;

/**
 * Clase encargada de desplegar la pantalla de capturar de puntajes altos
 * @author Pablo, Erik, Daniel
 * @version 1.0 Noviembre 2010
 */
public class CapturaPuntajesCanvas extends GameCanvas implements Actualizable {

    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private ManejadorTeclado teclado;
    private Animador animador;
    private Image letras;
    private Sprite indicador;
    private int xTabla, yTabla;
    private final char alfabeto[][] = {
        {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
        {'H', 'I', 'J', 'K', 'L', 'M', 'N'},
        {'O', 'P', 'Q', 'R', 'S', 'T', 'U'},
        {'V', 'W', 'X', 'Y', 'Z'}
    };
    private char[] iniciales;
    private int inicialActual;
    private int puntajeNuevo;
    private final int SUSTRAENDO_LETRA = 65;
    private Sprite letrasDeIniciales;
    private boolean menu = false;
    private Menu menuSubir;
    private String nombre;

    /**
     * Constructor inicializa variables
     * @param samuraiMidlet midlet
     * @param puntajeNuevo nuevo puntaje a colocar
     */
    public CapturaPuntajesCanvas(SamuraiEnterprises samuraiMidlet, int puntajeNuevo) {
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        this.g = this.getGraphics();
        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);

        this.puntajeNuevo = puntajeNuevo;
        try {
            letras = Image.createImage("/samurai/imagenes/letras.png");
            letrasDeIniciales = new Sprite(letras, letras.getWidth() / 7, letras.getHeight() / 4);
            indicador = new Sprite(Image.createImage("/samurai/imagenes/ambiente/arbol.png"));
            indicador.setPosition(Global.ANCHO_PANTALLA / 2 - letras.getWidth() / 2, Global.ALTO_PANTALLA / 2 - letras.getHeight() / 2);
            xTabla = 0;
            yTabla = 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        iniciales = new char[3];
        inicialActual = 0;

        menuSubir = null;
        nombre = "";
        animador = new Animador(this);
        animador.iniciar();
    }

    /**
     * actualiza
     */
    public void actualizar() {
        if (!menu) {
            if (teclado.downPresionado()) {
                if (yTabla < 3) {
                    yTabla++;
                } else {
                    yTabla = 0;
                }
            }

            if (teclado.upPresionado()) {
                if (yTabla > 0) {
                    yTabla--;
                } else {
                    yTabla = 3;
                }
            }

            if (teclado.derPresionado()) {
                if (xTabla < 6) {
                    xTabla++;
                } else {
                    xTabla = 0;
                }
            }

            if (teclado.izqPresionado()) {
                if (xTabla > 0) {
                    xTabla--;
                } else {
                    xTabla = 6;
                }
            }

            if (teclado.firePresionado()) {
                if (xTabla >= 5 && yTabla == 3) {
                    if (xTabla == 5) {
                        borrarLetra();
                    } else if (xTabla == 6) {
                        terminarCaptura();
                    }
                } else if (inicialActual < 3) {
                    iniciales[inicialActual] = alfabeto[yTabla][xTabla];
                    inicialActual++;
                }
                if (inicialActual == 3) {
                    xTabla = 6;
                    yTabla = 3;
                }
            }

            indicador.setPosition(Global.ANCHO_PANTALLA / 2 - letras.getWidth() / 2 + xTabla * letras.getWidth() / 7,
                    Global.ALTO_PANTALLA / 2 - letras.getHeight() / 2 + yTabla * letras.getHeight() / 4);
        } else {
            if (this.teclado.downPresionado()) {
                this.menuSubir.moverOpcion(1);
            }
            if (this.teclado.upPresionado()) {
                this.menuSubir.moverOpcion(-1);
            }
            if (this.teclado.firePresionado()) {
                switch (this.menuSubir.getPosition()) {
                    case 0:
                        try {
                            AlmacenamientoServidor.subirPuntaje(nombre, puntajeNuevo);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        samuraiMidlet.mostrarPuntajes();
                        break;
                    case 1:
                        samuraiMidlet.mostrarPuntajes();
                        return;
                }
            }
        }
    }

    /**
     * dibuja el abcedario y las iniciales
     */
    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        g.drawImage(letras, Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA / 2, g.HCENTER | g.VCENTER);
        indicador.paint(g);

        for (int inicialIndex = 0; inicialIndex < inicialActual; inicialIndex++) {
            letrasDeIniciales.setFrame((int) iniciales[inicialIndex] - SUSTRAENDO_LETRA);
            letrasDeIniciales.setPosition(Global.ANCHO_PANTALLA / 2 + letrasDeIniciales.getWidth() * (inicialIndex - 1), letrasDeIniciales.getHeight() * 3 / 5);
            letrasDeIniciales.paint(g);
        }
        if (this.menu && menuSubir != null) {
            g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
            this.menuSubir.dibujar(g);
        }
        flushGraphics();
    }

    /**
     * regresa el tipo de convas que es
     * @return tipo de canvas
     */
    public String tipoCanvas() {
        return Actualizable.CAPTURA_PUNTAJES;
    }

    /**
     * Destructor: para el animador y vuelve nulo a todos los objetos del objeto
     */
    public void destruir() {
        animador.interrumpir();
        animador = null;

        samuraiMidlet = null;
        g = null;
        teclado = null;
        animador = null;
        letras = null;
        indicador = null;
        iniciales = null;
    }

    /**
     * interrumpe el animador
     */
    public void interrumpir() {
        animador.interrumpir();
    }

    /**
     * inicia el animador
     */
    public void correr() {
        if (!animador.estaCorriendo()) {
            animador.iniciar();
        }
    }

    private void borrarLetra() {
        if (inicialActual > 0 && inicialActual <= 3) {
            iniciales[--inicialActual] = ' ';
        }
    }

    private void terminarCaptura() {
        if (inicialActual == 0) {
            iniciales[0] = 'Y';
            iniciales[1] = 'O';
            iniciales[2] = 'U';
        }
        nombre = "" + iniciales[0] + iniciales[1] + iniciales[2];
        guardarPuntaje(nombre);
    }

    private void guardarPuntaje(String iniciales) {
        //Establecemos el nuevo puntaje como la base de la pirámide.
        AdministradorData puntajeStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + Global.NUMERO_PUNTAJES_ALMACENADOS);
        puntajeStore.cambiarRegistro(puntajeNuevo, AdministradorData.REGISTRO_PUNTAJE);
        puntajeStore.cambiarRegistro(iniciales, AdministradorData.REGISTRO_INICIALES);

        for (int c = Global.NUMERO_PUNTAJES_ALMACENADOS - 1; c > 0; c--) {
            puntajeStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + c);
            if (puntajeNuevo > puntajeStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE)) {
                intercambiarPuntajes(c + 1, iniciales);
            }
        }
        this.subir();
    }

    /**
     * checa si el nuevo puntaje es lo suficientemente alto para estar en los mejores
     * @param puntajeNuevo nuevo puntaje
     * @return si supera algun record
     */
    public static boolean esNuevoPuntajeAlto(int puntajeNuevo) {
        AdministradorData minimoPuntajeMayorStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + Global.NUMERO_PUNTAJES_ALMACENADOS);
        int puntajeASuperar = minimoPuntajeMayorStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE);
        return puntajeNuevo > puntajeASuperar;
    }

    private void intercambiarPuntajes(int puntajeBajo, String iniciales) {
        AdministradorData puntajeAltoStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + (puntajeBajo - 1));
        AdministradorData puntajeBajoStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + puntajeBajo);

        puntajeBajoStore.cambiarRegistro(puntajeAltoStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE), AdministradorData.REGISTRO_PUNTAJE);
        puntajeBajoStore.cambiarRegistro(puntajeAltoStore.regresarDato(AdministradorData.REGISTRO_INICIALES), AdministradorData.REGISTRO_INICIALES);

        puntajeAltoStore.cambiarRegistro(puntajeNuevo, AdministradorData.REGISTRO_PUNTAJE);
        puntajeAltoStore.cambiarRegistro(iniciales, AdministradorData.REGISTRO_INICIALES);
    }

    private void subir() {
        this.crearMenu();
        this.menu = true;
    }

    private void crearMenu() {
        try {
             if (this.menuSubir == null) {
            this.menuSubir = new Menu(2, "/samurai/imagenes/titulos/tituloSubir.png", "/samurai/imagenes/slash.png", 1);
            Boton botonSi = new Boton("/samurai/imagenes/botones/botonSi.png");
            Boton botonNo = new Boton("/samurai/imagenes/botones/botonNo.png");
            this.menuSubir.agregarBoton(botonSi, "/samurai/imagenes/fondosMenu/fondoMenuPrueba2.png");
            this.menuSubir.agregarBoton(botonNo, "/samurai/imagenes/fondosMenu/fondoMenuPrueba2.png");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
