/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.util.Stack;
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
                aparicionEnemigos.push( new int[]{ 900, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 800, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 700, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 530, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 470, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 450, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 400, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 360, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 320, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 300, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 250, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 200, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 180, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 150, SpriteEnemigo.MURCIELAGO} );
                aparicionEnemigos.push( new int[]{ 100, SpriteEnemigo.MURCIELAGO} );
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
