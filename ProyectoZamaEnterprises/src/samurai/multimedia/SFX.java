package samurai.multimedia;

import java.io.IOException;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import samurai.juego.Global;

/**
 * Clase encargada de los SFX de cada enemigo y sekai
 * @author Pablo, Erik, Daniel
 * @version 1.0 Noviembre 2010
 */
public class SFX {
    private Player[] players;
    private GameCanvas canvas;
    /**
     * Enum de sfx de espada
     */
    public static final int ESPADA=0;
    /**
     * Enum de sfx de golpe a sekai
     */
    public static final int GOLPE_SEKAI=1;
    /**
     * Enum de sfx de muerte de sekai
     */
    public static final int MUERTE_SEKAI=2;
    /**
     * Enum de sfx de muerte murcielago
     */
    public static final int MUERTE_MURCIELAGO=3;
    /**
     * Enum de sfx de muerte topo
     */
    public static final int MUERTE_TOPO=4;
    /**
     * Enum de sfx de muerte rata
     */
    public static final int MUERTE_RATA=5;
    /**
     * Enum de sfx de muerte fantasma
     */
    public static final int MUERTE_FANTASMA=6;
    /**
     * Enum de sfx de muerte cesar
     */
    public static final int MUERTE_CESAR=7;
    private Player p;
    private final int MAXIMO_PLAYERS = 8;


    /**
     * Constructor SFX iniciliza players y asigna canvas
     * @param canvas Gamecanvas donde se correra el sonido
     */
    public SFX(GameCanvas canvas){
        this.canvas=canvas;
        this.players= new Player[MAXIMO_PLAYERS];
        Player p;
    }
    /**
     * agreaga un player al arreglo players
     * @param tipo player a agregar
     */
    public void agregarSFX(int tipo){
        try {
            switch(tipo){
                case SFX.ESPADA:
                    players[tipo]=Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/espada.m4a"), "audio/m4a");
                    break;
                case SFX.GOLPE_SEKAI:
                    players[tipo]= Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/golpe.wav"), "audio/wav");
                    break;
                case SFX.MUERTE_SEKAI:
                    players[tipo]= Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/golpe.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_MURCIELAGO:
                    players[tipo]= Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/murcielago.wav"), "audio/wav");
                    break;
                case SFX.MUERTE_TOPO:
                    players[tipo]= Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/rata.wav"), "audio/wav");
                    break;
                case SFX.MUERTE_RATA:
                    players[tipo] = Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/rata.wav"), "audio/wav");
                    break;
                case SFX.MUERTE_FANTASMA:
                    players[tipo] = Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/rata.wav"), "audio/wav");
                    break;
                case SFX.MUERTE_CESAR:
                    players[tipo] = Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/rata.wav"), "audio/wav");
                    break;
                default:
                    players[tipo] = Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/rata.wav"), "audio/wav");
            }
            players[tipo].realize();
            players[tipo].prefetch();
            
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * reproduce un sfx determinado
     * @param sfx sfx a reproducir
     */
    public void reproducir(int sfx){
        try {
            p = players[sfx];
            p.start();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
}
