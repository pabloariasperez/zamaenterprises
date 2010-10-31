/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.multimedia;

import java.io.IOException;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

/**
 *
 * @author zama
 */
public class Musica implements PlayerListener{
    private Player p;

    public Musica(String archivo){
        try {
            p = Manager.createPlayer(getClass().getResourceAsStream(archivo), "audio/mp3");
            p.realize();
            p.prefetch();
            p.addPlayerListener(this);
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
}
