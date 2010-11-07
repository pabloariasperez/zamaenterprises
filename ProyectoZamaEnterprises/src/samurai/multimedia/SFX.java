/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.multimedia;

import java.io.IOException;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.midlet.MIDlet;

/**
 *
 * @author zama
 */
public class SFX {
    private Player[] players;
    private GameCanvas canvas;
    public static final int ESPADA=0;
    public static final int GOLPE_SEKAI=1;
    public static final int MUERTE_SEKAI=2;
    public static final int MUERTE_MURCIELAGO=3;
    public static final int MUERTE_TOPO=4;
    public static final int MUERTE_RATA=5;
    public static final int MUERTE_FANTASMA=6;
    public static final int MUERTE_CESAR=7;


    public SFX(GameCanvas canvas){
        this.canvas=canvas;
        this.players= new Player[8];
    }
    public void agregarSFX(int tipo){
        try {
            switch(tipo){
                case SFX.ESPADA:
                    players[tipo]=Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/espada.m4a"), "audio/m4a");
                    break;
                case SFX.GOLPE_SEKAI:
                    players[tipo]= Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/golpe.wav"), "audio/wav");
                    break;
//                case SFX.MUERTE_SEKAI:
//                    players[tipo]= Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/muerte.mp3"), "audio/mp3");
//                    break;
//                case SFX.MUERTE_MURCIELAGO:
//                    players[tipo]= Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/murcielago.mp3"), "audio/mp3");
//                    break;
//                case SFX.MUERTE_TOPO:
//                    players[tipo]= Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/topo.mp3"), "audio/mp3");
//                    break;
//                case SFX.MUERTE_RATA:
//                    players[tipo] = Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/rata.mp3"), "audio/mp3");
//                    break;
//                case SFX.MUERTE_FANTASMA:
//                    players[tipo] = Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/fantasma.mp3"), "audio/mp3");
//                    break;
//                case SFX.MUERTE_CESAR:
//                    players[tipo] = Manager.createPlayer(this.samuraiMidlet.getClass().getResourceAsStream("/samurai/sonidos/cesar.mp3"), "audio/mp3");
//                    break;
                default:
                    players[tipo] = Manager.createPlayer(this.canvas.getClass().getResourceAsStream("/samurai/sonidos/wrong.mp3"), "audio/mp3");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    public void reproducir(int sfx){
        try {
            Player p = players[sfx];
            p.realize();
            p.prefetch();
            p.start();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
}
