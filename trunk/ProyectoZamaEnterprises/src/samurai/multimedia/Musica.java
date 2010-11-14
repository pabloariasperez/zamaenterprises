package samurai.multimedia;

import java.io.IOException;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.PlayerListener;

/**
 * Clase encargada de la musica del juego
 * @author Erik, pablo, Daniel
 * @version 1.0 Noviembre 2010
 */
public class Musica implements PlayerListener{
    private Player p;

    /**
     * carga el archivo de musica y lo prepara para ser escuchado
     * @param archivo direccion del archivo
     * @param canvas canvas al cual va a ser colocado
     */
    public Musica(String archivo,GameCanvas canvas){
        try {
            p = Manager.createPlayer(getClass().getResourceAsStream(archivo), "audio/midi");
            p.realize();
            p.prefetch();
            p.addPlayerListener(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Reproduce la musica
     */
    public void reproducir(){
        try {
            p.start();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Para la musica
     */
    public void parar(){
        try {
            p.stop();
        } catch (MediaException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Si el player termina vuelve a empezar la musica
     * @param player player a checar estado
     * @param string estado a comparar
     * @param o
     */
    public void playerUpdate(Player player, String string, Object o) {
       if(string.equals(PlayerListener.END_OF_MEDIA)){
            try {
                player.start();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
       }
    }
    public void repetirMusica(){
        this.playerUpdate(this.p, PlayerListener.END_OF_MEDIA, null);
    }
}
