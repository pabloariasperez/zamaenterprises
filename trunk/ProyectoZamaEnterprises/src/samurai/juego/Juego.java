package samurai.juego;

//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import java.io.IOException;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.animacion.SpriteEnemigo;
import samurai.animacion.SpriteEspada;
import samurai.animacion.SpriteSekai;
import samurai.escenarios.*;
import samurai.multimedia.Musica;
import samurai.multimedia.SFX;

/**
 *
 * @author mi16
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
    private TiempoEscenario tiempo;
    private Stack enemigosEnEspera;
    private int tiempoProxEvento;
    private SpriteEnemigo enemigo;
    int escenarioActual;
    Escenario escenario;


    private static Posicionador posicionador;
        private final int ANCHO_INICIAL = 170;
        private final int PORCENTAJE_ANCHO_FINAL = 20;
        public static final int ALTO_LINEA = 4;
        public static int altoFondo = 0;
        private int parametro = -100;

/**
 *
 * @param midlet
 */
    public Juego(SamuraiEnterprises midlet) {
        super(true);
        this.samuraiMidlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();

        escenario = new Escenario();
        escenario.agregarFondo(new FondoCapa("/samurai/imagenes/fondoLuna.png", 5, 0));
        altoFondo = escenario.getAltoFondos();

        posicionador = new Posicionador(ANCHO_INICIAL, PORCENTAJE_ANCHO_FINAL, ALTO_LINEA, altoFondo);

        posicionador.generarNuevoEje(-50);
        while(!posicionador.hayNuevoEje());

        try {
            //Relacionado con Nivel
            escenarioActual = Nivel.NIVEL_1;
            escenario = new Escenario();
            Nivel.inicializar(escenarioActual, escenario);

            //Creamos los manejadores
                //Manejador de Teclado
                manejadorTec = new ManejadorTeclado(this);

                //Manejador Sekai
                SpriteSekai sekai = new SpriteSekai("/samurai/imagenes/sekai.png", Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA);
                SpriteEspada efectos = new SpriteEspada("/samurai/imagenes/SpritesEfectos.png", Global.ANCHO_PANTALLA / 2 - sekai.getWidth()/2, Global.ALTO_PANTALLA - sekai.getHeight());
                this.manejadorSekai = new ManejadorSekai(sekai, efectos, manejadorTec);

                //Manejador Enemigos
                manejadorEnemigos=new ManejadorEnemigos();
                agregarStackEnemigos(Nivel.llenarStackEnemigos(escenarioActual));
                enemigo=null;       //Para no crear mil "BICHOS" enemigo
                
//            this.musica=new Musica("/tema.mp3");
//            musica.reproducir();
//            this.sfx=new SFX(midlet);
//            Nivel.cargarSFX(escenarioActual, sfx);


            tiempo = new TiempoEscenario();
            animador = new Animador(this);
            

            //animador.iniciar();
        } catch (IOException ex) {
            System.out.println("Aquí fue =(");
            ex.printStackTrace();
        }
        posicionador.generarNuevoEje(parametro);
        animador.iniciar();
    }
/**
 *
 * @return
 */
    public Animador getAnimador(){
        return this.animador;
    }

/**
 *
 */
    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        escenario.dibujar(g);
        posicionador.dibujarCamino(g);
        manejadorSekai.dibujar(g);
        manejadorEnemigos.dibujar(g);
        flushGraphics();
    }

/**
 *
 */
    public void actualizar(){
        if( tiempo.actual() == tiempoProxEvento && !enemigosEnEspera.isEmpty() ){
            agregarEnemigo( ((int[])enemigosEnEspera.pop())[1] );
            if(!enemigosEnEspera.isEmpty()){
                tiempoProxEvento = ((int[])enemigosEnEspera.peek())[0];
                System.out.println("PE:"+tiempoProxEvento);
            }
        }
        
        for(int i=0; i<manejadorEnemigos.getVectorEnemigo().size(); i++){
            this.enemigo=(SpriteEnemigo)(manejadorEnemigos.getVectorEnemigo().elementAt(i));
            if( manejadorSekai.colisionEspada(this.enemigo)){
                /*this.reproducir(enemigo.getTipoEnemigo());
                sfx.reproducir(SFX.ESPADA);
                 * 
                 */
                manejadorEnemigos.kill(this.enemigo);
            }

            if(manejadorSekai.colisionSekai(this.enemigo)){
                manejadorSekai.reducirVida(this.enemigo.getTipoEnemigo());
                manejadorEnemigos.desaparecer(this.enemigo);
            }
            if(this.enemigo.getY() >= Global.ALTO_PANTALLA-this.enemigo.getHeight()/2){
                manejadorSekai.reducirVida(this.enemigo.getTipoEnemigo());
                manejadorEnemigos.desaparecer(this.enemigo);
            }
        }
        
        escenario.actualizar();
        manejadorEnemigos.actualizar();
        try {
            manejadorSekai.actualizar();
            tiempo.incrementar();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }


        if(posicionador.hayNuevoEje()){
            parametro+=1;
            posicionador.generarNuevoEje(parametro);
        }
    }



    private void agregarStackEnemigos( Stack enemigosEnEspera ) {
        this.enemigosEnEspera = enemigosEnEspera;
        tiempoProxEvento = ((int[])enemigosEnEspera.peek())[0];
    }

    private void agregarEnemigo(int enemigo){
        this.manejadorEnemigos.agregarEnemigo(enemigo);
    }
    
    private void reproducir(int tipoEnemigo){
        switch(tipoEnemigo){
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

    public static Posicionador getPosicionador(){
        if(Juego.posicionador != null){
            return Juego.posicionador;
        }else{
            return null;
        }
    }
}