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

/**
 *
 * @param midlet
 */
    public Juego(SamuraiEnterprises midlet) {
        super(true);
        try {
            this.samuraiMidlet = midlet;
            this.setFullScreenMode(true);
            g = this.getGraphics();
            //manejadorEnemigos= new ManejadorEnemigos();
            escenarioActual = Nivel.NIVEL_1;
            escenario = new Escenario();
            manejadorTec = new ManejadorTeclado(this);
            SpriteSekai sekai = new SpriteSekai("/samurai/imagenes/sekai.png", Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA);
            SpriteEspada efectos = new SpriteEspada("/samurai/imagenes/SpritesEfectos.png", Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA);
            this.manejadorSekai = new ManejadorSekai(sekai, efectos, manejadorTec);
            manejadorEnemigos=new ManejadorEnemigos();
            Nivel.inicializar(escenarioActual, escenario);
            agregarStackEnemigos(Nivel.llenarStackEnemigos(escenarioActual));
            this.musica=new Musica("/tema.mp3");
            musica.reproducir();
            this.sfx=new SFX(midlet);
            Nivel.cargarSFX(escenarioActual, sfx);
            animador = new Animador(this);
            enemigo=null;
            animador.iniciar();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
        g.setColor(0x00654321);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        escenario.dibujar(g);
        manejadorSekai.dibujar(g);
        manejadorEnemigos.dibujar(g);
        this.dibujar();
        flushGraphics();
    }

/**
 *
 */
    public void actualizar(){
         if( tiempo.actual() == tiempoProxEvento ){
                agregarEnemigo( ((int[])enemigosEnEspera.pop())[1] );
                tiempoProxEvento = ((int[])enemigosEnEspera.peek())[0];
            }
         for(int i=0; i<manejadorEnemigos.getVectorEnemigo().size();i++){
             this.enemigo=(SpriteEnemigo)(manejadorEnemigos.getVectorEnemigo().elementAt(i));
            if( manejadorSekai.colisionEspada(this.enemigo)){
                this.reproducir(enemigo.getTipoEnemigo());
                sfx.reproducir(SFX.ESPADA);
                manejadorEnemigos.kill(this.enemigo);
            }
             if(manejadorSekai.colisionSekai(this.enemigo)){
                 manejadorSekai.reducirVida(this.enemigo.getTipoEnemigo());
             }
         }
        escenario.actualizar();
        tiempo.incrementar();
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
}