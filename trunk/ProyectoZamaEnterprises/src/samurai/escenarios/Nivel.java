/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.io.IOException;
import java.util.Stack;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.midlet.MIDlet;
import samurai.animacion.SpriteEnemigo;
import samurai.multimedia.SFX;

/**
 *
 * @author Pablo
 */
public class Nivel{
    public static final int NIVEL_1=1;
    public static final int NIVEL_2=2;
    public static final int NIVEL_3=3;

    public  static void inicializar(int nivel, Escenario escenario){
       switch(nivel){
           case NIVEL_1:
                //Agregamos nuestros fondos
                escenario.agregarFondo(new FondoCapa("/samurai/imagenes/fondoLuna.png", -1, 0));

                break;
           case NIVEL_2:
               break;
           case NIVEL_3:
               break;
       }
    }
    public static void cargarSFX(int nivel, SFX sfx){
        sfx.agregarSFX(SFX.ESPADA);
        sfx.agregarSFX(SFX.GOLPE_SEKAI);
        sfx.agregarSFX(SFX.MUERTE_SEKAI);
        switch(nivel){
            case NIVEL_1:
                sfx.agregarSFX(SFX.MUERTE_TOPO);
                sfx.agregarSFX(SFX.MUERTE_RATA);
                sfx.agregarSFX(SFX.MUERTE_MURCIELAGO);
                break;
            case NIVEL_2:
                break;
            case NIVEL_3:
                break;
        }
    }
    public static Stack llenarStackEnemigos(int escenario){
        switch(escenario){
            case NIVEL_1:
                Stack aparicionEnemigos = new Stack();  //En qué momento y qué enemigo.
                aparicionEnemigos.push( new int[]{ 64, SpriteEnemigo.TOPO} );
                aparicionEnemigos.push( new int[]{ 60, SpriteEnemigo.TOPO} );
                aparicionEnemigos.push( new int[]{ 56, SpriteEnemigo.TOPO} );
                aparicionEnemigos.push( new int[]{ 53, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 47, SpriteEnemigo.RATA} );
                aparicionEnemigos.push( new int[]{ 45, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 40, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 36, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 32, SpriteEnemigo.RATA} );
                aparicionEnemigos.push( new int[]{ 30, SpriteEnemigo.RATA} );
                aparicionEnemigos.push( new int[]{ 25, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 20, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 18, SpriteEnemigo.TOPO} );
                aparicionEnemigos.push( new int[]{ 15, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 10, SpriteEnemigo.MURCIELAGO} );
                return aparicionEnemigos;
            case NIVEL_2:
                return null;
            case NIVEL_3:
                return null;
            default:
                return null;
        }
        
    }
}
