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
import javax.microedition.media.PlayerListener;
import javax.microedition.midlet.MIDlet;
import samurai.juego.Juego;

/**
 *
 * @author zama
 */
public class Musica implements PlayerListener{
    private Player p;

    public Musica(String archivo,GameCanvas canvas){
        try {
            p = Manager.createPlayer(getClass().getResourceAsStream(archivo), "audio/midi");
            p.realize();
            p.prefetch();
           // p.addPlayerListener(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    public void reproducir(){
        try {
            p.start();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    public void parar(){
        try {
            p.stop();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    public void playerUpdate(Player player, String string, Object o) {
       if(string.equals(PlayerListener.END_OF_MEDIA)){
            try {
                player.start();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
       }
    }
    public Player getPlayer(){
        return this.p;
    }
}
