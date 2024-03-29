package samurai.juego;

import java.io.IOException;
import java.util.Random;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.almacenamiento.AdministradorData;
import samurai.animacion.Score;
import samurai.animacion.SpriteEnemigo;
import samurai.animacion.SpriteItem;
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
    private ManejadorItems manejadorItems;
    private ManejadorTeclado manejadorTec;
    private Musica musica;
    private SFX sfx;
    private Image imagenPausa;
    private boolean pausado;
    private boolean reproduciendo;
    private SpriteEnemigo enemigo;
    private SpriteItem item;
    int escenarioActual;
    private Random random;
    Escenario escenario;
    private static Posicionador posicionador;
    private final int ANCHO_INICIAL = 190;
    private final int PORCENTAJE_ANCHO_FINAL = 15;
    /**
     * tamaño de la linea
     */
    public static final int ALTO_LINEA = 4;
    public static final int SCORE_INICIAL = 0;
    public static final int TIEMPO_INICIAL = 0;
    /**
     * tamaño del fondo
     */
    public static int altoFondo = 0;
    private int parametro;
    private Menu menuPausa;
    private Boton botonSalir;
    private Boton botonContinuar;
    private int score;
    private Score scoreSprite;

    /**
     * Contructor de juego; inicicaliza todo lo necesario
     * @param samuraiMidlet midlet que maneja a juego
     * @param nivel
     * @param vida
     * @param score
     * @param tiempo
     */
    public Juego(SamuraiEnterprises samuraiMidlet, int nivel, int score, int vida, int tiempo) {
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        g = this.getGraphics();

        Cargador cargador = new Cargador("Cargando juego nuevo");
        cargador.iniciar();
        Display.getDisplay(samuraiMidlet).setCurrent((Displayable) cargador);

        cargador.cambiarMensaje("Cargando escenario");
        this.pausado = false;
        escenarioActual = nivel;

        escenario = new Escenario(escenarioActual, tiempo);

        //Relacionado con Nivel
        Nivel.inicializar(escenarioActual, escenario);
        altoFondo = escenario.getAltoFondos();

        cargador.cambiarMensaje("Cargando posicionador");
        posicionador = new Posicionador(ANCHO_INICIAL, PORCENTAJE_ANCHO_FINAL, ALTO_LINEA, altoFondo);

        parametro = escenario.obtenerParametro();
        posicionador.generarNuevoEje(parametro);
        while (!posicionador.hayNuevoEje()) {
            posicionador.sleep(100);
        }

        cargador.cambiarMensaje("Cargando manejadores");
        try {
            //Creamos los manejadores
            //Manejador de Teclado
            manejadorTec = new ManejadorTeclado(this);

            this.imagenPausa = Image.createImage("/samurai/imagenes/pausa.png");

            //Manejador Sekai
            this.manejadorSekai = new ManejadorSekai(manejadorTec, vida);

            //Manejador Enemigos
            manejadorEnemigos = new ManejadorEnemigos();
            enemigo = null;       //Para no crear mil "BICHOS" enemigo

            manejadorItems = new ManejadorItems();
            item = null;

            cargador.cambiarMensaje("Cargando música");
            this.cargarMusica(escenarioActual);
            this.reproduciendo = false;

            this.sfx = new SFX(this);
            Nivel.cargarSFX(escenarioActual, sfx);

            this.score = score;
            this.scoreSprite = new Score(10, 10);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        cargador.cambiarMensaje("Cargando ambiente");
        escenario.cargarAmbiente();


        if (tiempo > 0) {
            cargador.cambiarMensaje("Cargando enemigos");
            this.manejadorEnemigos.cargarDatos();
            this.pausarJuego();
        }
        random = new Random();

        cargador.terminar();
        cargador = null;

        animador = new Animador(this);
        animador.iniciar();
    }

    /**
     *
     */
    public void creaBotones() {
        try {
            if (this.menuPausa == null) {
                this.menuPausa = new Menu(2, "/samurai/imagenes/titulos/tituloPausa.png", "/samurai/imagenes/slash.png", 1);
                this.botonContinuar = new Boton("/samurai/imagenes/botones/botonContinuar.png");
                this.botonSalir = new Boton("/samurai/imagenes/botones/botonSalir.png");
                this.menuPausa.agregarBoton(botonContinuar, "/samurai/imagenes/fondosMenu/continuar.png");
                this.menuPausa.agregarBoton(botonSalir, "/samurai/imagenes/fondosMenu/puertas.png");
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
        manejadorItems.dibujar(g);
        manejadorEnemigos.dibujar(g);
        manejadorSekai.dibujar(g);
        scoreSprite.dibujar(g);
        if (this.pausado && menuPausa != null) {
            g.drawImage(imagenPausa, 0, 0, Graphics.LEFT | Graphics.TOP);
            this.menuPausa.dibujar(g);
        }
        flushGraphics();
    }

    /**
     * actualiza todos los coponentes de juego
     */
    public void actualizar() throws NullPointerException {
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
                        this.guardarDatos();
                        this.samuraiMidlet.mostrarMenu();
                        return;
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


            int rndEnemigo = random.nextInt(Global.dificultad);
            int rndItem = random.nextInt(50);

            if (rndEnemigo == 0 && manejadorEnemigos.getVectorEnemigo().size() < 10 && !escenario.esFinEscenario()) {
                agregarEnemigo(Nivel.generarEnemigo(escenarioActual, random));
            }
            if (rndItem == 0 && manejadorItems.getVectorItem().size() < 1 && !escenario.esFinEscenario()) {
                agregarItem(this.random);
            }

            if (escenario.esFinEscenario()) {
                if (manejadorEnemigos.getVectorEnemigo().isEmpty() && manejadorItems.getVectorItem().isEmpty()) {
                    switch (escenarioActual) {
                        case Nivel.NIVEL_1:
                            samuraiMidlet.transicionNivelDos(score);
                            break;
                        case Nivel.NIVEL_2:
                            samuraiMidlet.transicionNivelTres(score);
                            break;
                    }

                    return;
                }
            }

            for (int i = 0; i < manejadorEnemigos.getVectorEnemigo().size(); i++) {
                this.enemigo = (SpriteEnemigo) (manejadorEnemigos.getVectorEnemigo().elementAt(i));

                if (manejadorSekai.colisionEspada(this.enemigo)) {
                    if (Global.SONIDO_ACTIVADO) {
                        sfx.reproducir(SFX.ESPADA);
                        this.reproducirEnemigo(this.enemigo.getTipoEnemigo());
                    }
                    this.aumentarScore(this.enemigo.getTipoEnemigo());
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
            for (int i = 0; i < manejadorItems.getVectorItem().size(); i++) {
                this.item = (SpriteItem) (manejadorItems.getVectorItem().elementAt(i));

                if (manejadorSekai.colisionEspada(this.item)) {
                    if (Global.SONIDO_ACTIVADO) {
                        sfx.reproducir(SFX.ESPADA);
                        this.reproducirItem(item.getTipoItem());
                    }
                    this.activarItem(this.item.getTipoItem());
                    manejadorItems.desaparecer(item);
                }
            }
            if (manejadorSekai.muerteSekai()) {
                if (Global.SONIDO_ACTIVADO) {
                    sfx.reproducir(SFX.MUERTE_SEKAI);
                }
                this.samuraiMidlet.mostrarGameOver();
                AdministradorData data = new AdministradorData(AdministradorData.STORE_AVANCE);
                data.borrarTodo();
                return;
            }

            escenario.actualizar();
            manejadorEnemigos.actualizar();
            manejadorItems.actualizar();
            scoreSprite.actualizar(score);
            try {
                manejadorSekai.actualizar();
            } catch (InterruptedException ex) {

                ex.printStackTrace();
            }
        }
    }

    private void agregarEnemigo(int enemigo) {
        this.manejadorEnemigos.agregarEnemigo(enemigo);
    }

    private void agregarItem(Random rnd) {
        this.manejadorItems.agregarItem(rnd);
    }

    private void reproducirEnemigo(int tipoEnemigo) {
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
    private void reproducirItem(int tipoItem){
        switch(tipoItem){
            case SpriteItem.ITEM_CORAZON:
                sfx.reproducir(SFX.CORAZON);
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
    public final void pausarJuego() {
        if (Global.SONIDO_ACTIVADO) {
            musica.parar();
        }
        this.reproduciendo = false;
        this.creaBotones();
        this.pausado = true;
    }

    /**
     * continua el juego
     */
    public void continuarJuego() {
        this.pausado = false;
    }

    /**
     *
     * @return
     */
    public String tipoCanvas() {
        return Actualizable.JUEGO;
    }

    /**
     *
     */
    public void destruir() {
        if (Global.SONIDO_ACTIVADO) {
            musica.parar();
        }
        animador.interrumpir();
        animador = null;

        samuraiMidlet = null;
        g = null;
        manejadorSekai = null;
        manejadorEnemigos = null;
        manejadorTec = null;
        musica = null;
        sfx = null;
        imagenPausa = null;
        enemigo = null;
        random = null;
        escenario = null;
        menuPausa = null;
        botonSalir = null;
        botonContinuar = null;
    }

    private void cargarMusica(int escenarioActual) {
        switch (escenarioActual) {
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

    /**
     *
     * @return
     */
    public int getEscenarioActual() {
        return escenarioActual;
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

    /**
     *
     */
    protected void hideNotify() {
        super.hideNotify();
        this.pausarJuego();
    }

    private void guardarDatos() {
        Cargador cargador = new Cargador("Guardando juego");
        cargador.iniciar();
        Display.getDisplay(samuraiMidlet).setCurrent((Displayable) cargador);

        AdministradorData data = new AdministradorData(AdministradorData.STORE_AVANCE);
        cargador.cambiarMensaje("Borrando registro anterior");
        data.borrarTodo();
        cargador.cambiarMensaje("Guardando puntaje");
        data.agregarRegistro(score);
        cargador.cambiarMensaje("Guardando estado de partida");
        data.agregarRegistro(manejadorSekai.getVida());
        data.agregarRegistro(escenarioActual);
        data.agregarRegistro(escenario.tiempoActual());
        cargador.cambiarMensaje("Guardando enemigos");
        this.manejadorEnemigos.guardarDatos();

        cargador.terminar();
        cargador = null;
    }

    private void activarItem(int tipoItem) {
        switch (tipoItem) {
            case SpriteItem.ITEM_CORAZON:
                manejadorSekai.aumentarVida((int) (ManejadorSekai.VIDA_TOTAL * .1));
                break;
            default:
                break;
        }
    }

    /**
     *
     * @return
     */
    public int getPuntaje() {
        return score;
    }
}
