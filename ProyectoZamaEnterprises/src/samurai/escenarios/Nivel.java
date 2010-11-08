/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.util.Random;
import samurai.multimedia.SFX;

/**
 *
 * @author Pablo
 */
public class Nivel{
    public static final int NIVEL_1 = 1;
    public static final int NIVEL_2 = 2;
    public static final int NIVEL_3 = 3;

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
       // sfx.agregarSFX(SFX.ESPADA);
       // sfx.agregarSFX(SFX.GOLPE_SEKAI);
//        sfx.agregarSFX(SFX.MUERTE_SEKAI);
//        switch(nivel){
//            case NIVEL_1:
//                sfx.agregarSFX(SFX.MUERTE_TOPO);
//                sfx.agregarSFX(SFX.MUERTE_RATA);
//                sfx.agregarSFX(SFX.MUERTE_MURCIELAGO);
//                break;
//            case NIVEL_2:
//                break;
//            case NIVEL_3:
//                break;
//        }
    }
    public static int generarEnemigo(int escenario, Random random){
        switch(escenario){
            case NIVEL_1:
                return random.nextInt(2);
            case NIVEL_2:
                return random.nextInt(3);
            case NIVEL_3:
                return random.nextInt(4);
            default:
                return random.nextInt(2);
        }
    }
}
