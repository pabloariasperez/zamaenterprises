package samurai.juego;

//Proyecto zama enterprises
// Autores Pablo, Erik y Daniel

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.escenarios.*;

/**
 *
 * @author mi16
 */
public class Juego extends GameCanvas implements Actualizable {
    
    private SamuraiEnterprises samuraiMidlet;
    private Animador animador;
    private Graphics g;

    private ManejadorTeclado manejadorTec;

    int escenarioActual;
    Escenario escenario;

/**
 *
 * @param midlet
 */
    public Juego(SamuraiEnterprises midlet) {
        super(true);

        this.samuraiMidlet = midlet;
        this.setFullScreenMode(true);
        g = this.getGraphics();

        //manejadorEnemigos= new ManejadorEnemigos();

        escenarioActual=Nivel.NIVEL_1;
        escenario= new Escenario();
        manejadorTec = new ManejadorTeclado(this);
        Nivel.inicializar(manejadorTec, escenarioActual, escenario);
        escenario.agregarStackEnemigos(Nivel.llenarStackEnemigos(escenarioActual));
        animador = new Animador(this);
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
        g.setColor(0x00654321);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        escenario.dibujar(g);
        this.dibujar();
        flushGraphics();
    }

/**
 *
 */
    public void actualizar(){
        escenario.actualizar();
    }
}