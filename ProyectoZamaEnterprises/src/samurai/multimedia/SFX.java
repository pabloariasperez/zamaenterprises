/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.multimedia;

import java.io.IOException;
import java.util.Vector;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;

/**
 *
 * @author zama
 */
public class SFX {
    private Vector players;
    private Vector posiciones;

    public static final int ESPADA=0;
    public static final int GOLPE_SEKAI=1;
    public static final int MUERTE_SEKAI=2;
    public static final int MUERTE_MURCIELAGO=3;
    public static final int MUERTE_TOPO=4;
    public static final int MUERTE_RATA=5;
    public static final int MUERTE_FANTASMA=6;
    public static final int MUERTE_CESAR=7;


    public SFX(){
        this.players= new Vector();
    }
    public void agregarSFX(int tipo){
        Player p = null;
        try {
            switch(tipo){
                case SFX.ESPADA:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/espada.mp3"), "audio/mp3");
                    break;
                case SFX.GOLPE_SEKAI:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/golpe.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_SEKAI:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/muerte.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_MURCIELAGO:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/murcielago.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_TOPO:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/topo.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_RATA:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/rata.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_FANTASMA:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/fantasma.mp3"), "audio/mp3");
                    break;
                case SFX.MUERTE_CESAR:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/cesar.mp3"), "audio/mp3");
                    break;
                default:
                    p = Manager.createPlayer(getClass().getResourceAsStream("/wrong.mp3"), "audio/mp3");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
        if( p != null){
            this.players.addElement(p);
        }
        this.posiciones.addElement(new Integer(tipo));
    }
    public void reproducir(int sfx){
        int numeroPlayers = players.size();
        for( int posicionP = 0; posicionP < numeroPlayers; posicionP++ ){
            if( ((Integer)posiciones.elementAt(posicionP)).intValue() == sfx){
                try {
                    Player p = ((Player) players.elementAt(posicionP));
                    p.realize();
                    p.prefetch();
                    p.start();
                } catch (MediaException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
