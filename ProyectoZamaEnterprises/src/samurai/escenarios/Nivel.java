package samurai.escenarios;

import java.util.Random;
import java.util.Stack;
import samurai.multimedia.SFX;

/**
 * Se encarga de los enemigos,  los fondos y los sfx de cada nivel
 * @author Pablo, Erik, Daniel
 * @version 1.3
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
                escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/cielogrande.png", 6));
                escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/montanascafes.png", 16));
                break;
           case NIVEL_2:
               escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/atardecergrande.png", 6));
               escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/montanascafes.png", 16));
               break;
           case NIVEL_3:
               escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/fondoLuna.png", 6));
               escenario.agregarFondo(new FondoCapa("/samurai/imagenes/ambiente/edificios.png", 16));
               break;
       }
    }
    /**
     * Carga los sfx deacuerdo al nivel que se juegue
     * @param nivel : nivel donde se cargaran los sfx
     * @param sfx objeto donde se precargaran todos los sfx
     */
    public static void cargarSFX(int nivel, SFX sfx){
        sfx.agregarSFX(SFX.ESPADA);
        sfx.agregarSFX(SFX.GOLPE_SEKAI);
//        sfx.agregarSFX(SFX.MUERTE_SEKAI);
        switch(nivel){
            case NIVEL_1:
                sfx.agregarSFX(SFX.MUERTE_RATA);
                sfx.agregarSFX(SFX.MUERTE_MURCIELAGO);
                break;
            case NIVEL_2:
                sfx.agregarSFX(SFX.MUERTE_MURCIELAGO);
                sfx.agregarSFX(SFX.MUERTE_TOPO);
                break;
//            case NIVEL_3:
                //sfx.agregarSFX(SFX.MUERTE_TOPO);
                //sfx.agregarSFX(SFX.MUERTE_FANTASMA);
//                break;
        }
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
                return random.nextInt(3)+2;
            case NIVEL_3:
                return random.nextInt(2);
            default:
                return random.nextInt(2);
        }
    }

    /**
     * regresa el stack del parametro dependiendo del nivel
     * @param escenario nivel al que se le agrega
     * @return stack de parametros
     */
    public static Stack llenarStackParametro(int escenario){
        Stack parametros = new Stack();  //En qué momento y qué parámetro.
        switch(escenario){
            case NIVEL_1:
                for(int c=200; c>=0; c-=10){
                    parametros.push( new int[]{ c, (int)(120*Math.sin(c*Math.PI/500))} );
                }
                return parametros;
            case NIVEL_2:
                int asintotaLineal = 10;
                int factorDeArgumentoTrigonometrico = 0;
                for(int c=1000; c>=0; c-=10){
                    parametros.push( new int[]{ c, (int)(asintotaLineal*Math.sin( (factorDeArgumentoTrigonometrico++*Math.PI)/10))} );
                    if( factorDeArgumentoTrigonometrico == 21 ){
                        factorDeArgumentoTrigonometrico = 0;
                        asintotaLineal += 20;
                    }
                }
                return parametros;
            case NIVEL_3:
                return null;
            default:
                return null;
        }
    }
}
