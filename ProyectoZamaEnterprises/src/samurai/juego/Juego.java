package samurai.juego;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.SpriteEnemigo;
import samurai.animacion.SpriteEspada;
import samurai.animacion.SpriteSekai;
import samurai.escenarios.*;
import samurai.menu.Boton;
import samurai.menu.Menu;
import samurai.multimedia.Musica;
import samurai.multimedia.SFX;

/**
 * Se encarga de manejar la logica del juego
 * @author Pablo, Erik, Daniel
 * @version 2.0
 */
public class Juego extends GameCanvas implements Actualizable {

    private SamuraiEnterprises samuraiMidlet;
    private Animador animador;
    private Graphics g;
    private ManejadorSekai manejadorSekai;
    private ManejadorEnemigos manejadorEnemigos;
    private ManejadorTeclado manejadorTec;
    private Musica musica;
    private SFX sfx;
    private Image imagenPausa;
    private boolean pausado;
    private boolean reproduciendo;
    private TiempoEscenario tiempo;
    private SpriteEnemigo enemigo;
    int escenarioActual;
    private Random random;
    Escenario escenario;
    private static Posicionador posicionador;
    private final int ANCHO_INICIAL = 170;
    private final int PORCENTAJE_ANCHO_FINAL = 20;
    /**
     * tamaño de la linea
     */
    public static final int ALTO_LINEA = 4;
    /**
     * tamaño del fondo
     */
    public static int altoFondo = 0;
    private int parametro;
    private Menu menuPausa;
    private Boton botonSalir;
    private Boton botonContinuar;
    private int score;

    /**
     * Contructor de juego; inicicaliza todo lo necesario
     * @param midlet midlet que maneja a juego
     */
    public Juego(SamuraiEnterprises midlet, int nivel) {
        super(true);
        escenarioActual = nivel;
        this.samuraiMidlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();
        this.pausado = false;
        escenario = new Escenario();

        this.agregarFondos(escenarioActual);
        altoFondo = escenario.getAltoFondos();

        posicionador = new Posicionador(ANCHO_INICIAL, PORCENTAJE_ANCHO_FINAL, ALTO_LINEA, altoFondo);

        parametro = -120;
        posicionador.generarNuevoEje(parametro);
        while (!posicionador.hayNuevoEje()) {
            posicionador.sleep(100);
        }
        escenario.setRazonCambioPiedra(posicionador.posiciones.length);


        try {
            //Relacionado con Nivel
            Nivel.inicializar(escenarioActual, escenario);

            //Creamos los manejadores
            //Manejador de Teclado
            manejadorTec = new ManejadorTeclado(this);

            this.imagenPausa = Image.createImage("/samurai/imagenes/pausa.png");

            //Manejador Sekai
            SpriteSekai sekai = new SpriteSekai("/samurai/imagenes/sekai.png", Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA);
            SpriteEspada efectos = new SpriteEspada("/samurai/imagenes/SpritesEfectos.png", Global.ANCHO_PANTALLA / 2 - sekai.getWidth() / 2, Global.ALTO_PANTALLA - sekai.getHeight());
            this.manejadorSekai = new ManejadorSekai(sekai, efectos, manejadorTec);


            //Manejador Enemigos
            manejadorEnemigos = new ManejadorEnemigos();
            enemigo = null;       //Para no crear mil "BICHOS" enemigo
            
            this.cargarMusica(escenarioActual);
            this.reproduciendo = false;

            this.sfx = new SFX(this);
            Nivel.cargarSFX(escenarioActual, sfx);

            this.score = 0;

            tiempo = new TiempoEscenario();
            


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        posicionador.generarNuevoEje(parametro);
        random = new Random();

        animador = new Animador(this);
        animador.iniciar();
    }

    public void creaBotones() {
        try {
            if (this.menuPausa == null) {
                this.menuPausa = new Menu(2, "/samurai/imagenes/tituloprincipal.png", "/samurai/imagenes/slash.png", 1);
                this.botonContinuar = new Boton("/samurai/imagenes/botonContinuar.png");
                this.botonSalir = new Boton("/samurai/imagenes/botonSalir.png");
                this.menuPausa.agregarBoton(botonContinuar, "/samurai/imagenes/fondosMenu/fondoMenuPrueba2.png");
                this.menuPausa.agregarBoton(botonSalir, "/samurai/imagenes/fondosMenu/fondoMenuPrueba2.png");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * dibuja todos los componentes de juego
     */
    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        escenario.dibujar(g);
        manejadorEnemigos.dibujar(g);
        manejadorSekai.dibujar(g);
        if (this.pausado && menuPausa != null) {
            g.drawImage(imagenPausa, 0, 0, Graphics.LEFT | Graphics.TOP);
            this.menuPausa.dibujar(g);
        }
        flushGraphics();
    }

    /**
     * actualiza todos los coponentes de juego
     */
    public void actualizar() {
        if (!this.pausado) {
            if (this.manejadorTec.downPresionado()) {
                this.pausarJuego();
            }

        } else {
            if (this.manejadorTec.downPresionado()) {
                this.menuPausa.moverOpcion(1);
            }
            if (this.manejadorTec.upPresionado()) {
                this.menuPausa.moverOpcion(-1);
            }

            if (this.manejadorTec.firePresionado()) {
                switch (this.menuPausa.getPosition()) {
                    case 0:
                        this.continuarJuego();
                        break;
                    case 1:
                        this.samuraiMidlet.mostrarMenu();
                        break;
                }
            }
        }




        if (!this.pausado) {
            if (!this.reproduciendo && Global.SONIDO_ACTIVADO) {
                musica.reproducir();
                this.reproduciendo = true;
            }
            if (Global.SONIDO_ACTIVADO) {
                musica.repetirMusica();
            }

            int rnd = random.nextInt(Global.FPS / 2);
            if (rnd == 0 && manejadorEnemigos.getVectorEnemigo().size() < 10) {
                agregarEnemigo(Nivel.generarEnemigo(escenarioActual, random));
            }
            for (int i = 0; i < manejadorEnemigos.getVectorEnemigo().size(); i++) {
                this.enemigo = (SpriteEnemigo) (manejadorEnemigos.getVectorEnemigo().elementAt(i));

                if (manejadorSekai.colisionEspada(this.enemigo)) {
                    if (Global.SONIDO_ACTIVADO) {
                        sfx.reproducir(SFX.ESPADA);
                        this.reproducir(this.enemigo.getTipoEnemigo());
                    }
                    this.aumentarScore(this.enemigo.getTipoEnemigo());
                    System.out.println("score: "+this.score);
                    manejadorEnemigos.kill(this.enemigo);
                }
                if (manejadorSekai.colisionSekai(this.enemigo)
                        || this.enemigo.getY() >= Global.ALTO_PANTALLA - this.enemigo.getHeight() / 2) {
                    if (Global.SONIDO_ACTIVADO) {
                        sfx.reproducir(SFX.GOLPE_SEKAI);
                    }
                    manejadorSekai.reducirVida(this.enemigo.getTipoEnemigo());
                    Display.getDisplay(samuraiMidlet).vibrate(90);
                    manejadorEnemigos.desaparecer(this.enemigo);
                }
            }
            if (manejadorSekai.muerteSekai()) {
                this.samuraiMidlet.mostrarGameOver();
            }
            if (posicionador.hayNuevoEje()) {
                parametro += 1;
                posicionador.generarNuevoEje(parametro);
            }
            escenario.actualizar();
            manejadorEnemigos.actualizar();
            try {
                manejadorSekai.actualizar();
                tiempo.incrementar();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void agregarEnemigo(int enemigo) {
        this.manejadorEnemigos.agregarEnemigo(enemigo);
    }

    private void reproducir(int tipoEnemigo) {
        switch (tipoEnemigo) {
            case SpriteEnemigo.CESAR:
                sfx.reproducir(SFX.MUERTE_CESAR);
                break;
            case SpriteEnemigo.FANTASMA:
                sfx.reproducir(SFX.MUERTE_FANTASMA);
                break;
            case SpriteEnemigo.MURCIELAGO:
                sfx.reproducir(SFX.MUERTE_MURCIELAGO);
                break;
            case SpriteEnemigo.RATA:
                sfx.reproducir(SFX.MUERTE_RATA);
                break;
            case SpriteEnemigo.TOPO:
                sfx.reproducir(SFX.MUERTE_TOPO);
                break;
        }
    }

    private void aumentarScore(int tipoEnemigo) {
        switch (tipoEnemigo) {
            case SpriteEnemigo.MURCIELAGO:
                this.score += 10;
                break;
            case SpriteEnemigo.RATA:
                this.score += 5;
                break;
            case SpriteEnemigo.FANTASMA:
                this.score += 15;
                break;
            case SpriteEnemigo.TOPO:
                this.score += 20;
                break;
            case SpriteEnemigo.CESAR:
                this.score += 100;
                break;
        }
    }

    /**
     * Regresa un posicionador
     * @return posicionador
     */
    public static Posicionador getPosicionador() {
        if (Juego.posicionador != null) {
            return Juego.posicionador;
        } else {
            return null;


        }
    }

    /**
     * pausa el juego
     */
    public void pausarJuego() {
        musica.parar();
        this.reproduciendo = false;
        this.pausado = true;
        this.creaBotones();
    }

    /**
     * continua el juego
     */
    public void continuarJuego() {
        this.pausado = false;
    }

    public String tipoCanvas() {
        return Actualizable.JUEGO;
    }

    public void destruir() {
        musica.parar();
        animador.terminar();
        animador = null;

        samuraiMidlet = null;
        g = null;
        manejadorSekai = null;
        manejadorEnemigos = null;
        manejadorTec = null;
        musica = null;
        sfx = null;
        imagenPausa = null;
        tiempo = null;
        enemigo = null;
        random = null;
        escenario = null;
        menuPausa = null;
        botonSalir = null;
        botonContinuar = null;
    }

    private void agregarFondos(int escenarioActual) {
        switch(escenarioActual){
            case Nivel.NIVEL_1:
                escenario.agregarFondo(new FondoCapa("/samurai/imagenes/fondoLuna.png", 5, 0));
                break;
            case Nivel.NIVEL_2:
                break;
            case Nivel.NIVEL_3:
                break;
            default:
                break;
        }
    }

    private void cargarMusica(int escenarioActual) {
        switch(escenarioActual){
            case Nivel.NIVEL_1:
                this.musica = new Musica("/samurai/sonidos/tema.mid", this);
                break;
            case Nivel.NIVEL_2:
                break;
            case Nivel.NIVEL_3:
                break;
            default:
                break;
        }
    }
}
