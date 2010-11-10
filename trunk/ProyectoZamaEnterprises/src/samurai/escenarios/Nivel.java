/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.util.Random;
import samurai.multimedia.SFX;

/**
 * Se encarga de los enemigos,  los fondos y los sfx de cada nivel
 * @author Pablo, Erik, Daniel
 * @version 1.2
 */
public class Nivel{
    /**
     * Enum del nivel 1
     */
    public static final int NIVEL_1 = 1;
    /**
     * Enum del nivel 2
     */
    public static final int NIVEL_2 = 2;
    /**
     * Enum del nivel 3
     */
    public static final int NIVEL_3 = 3;

    /**
     * agrega los fondos al escenario deseado
     * @param nivel : nivel a donde se agregara el fondo
     * @param escenario : escenario donde se colocaran los fondos
     */
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
    /**
     * Carga los sfx deacuerdo al nivel que se juegue
     * @param nivel : nivel donde se cargaran los sfx
     * @param sfx objeto donde se precargaran todos los sfx
     */
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
    /**
     * Genera enemigos aleatoriamente
     * @param nivel nivel que se esta jugando
     * @param random objeto para generar numeros aleatorios
     * @return entero representativo del enemigo a agregar
     */
    public static int generarEnemigo(int nivel, Random random){
        switch(nivel){
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
